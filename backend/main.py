import json
import random
import secrets
from sys import stderr, stdin, stdout

from utils import rsa

from backend.admin import Administrator
from backend.counter import Counter
from backend.voter import Voter

# Number of voters to simulate
NUM_VOTERS = 30

BALLOT = "sample_ballot.json"

eprint = lambda m: print(m, file=stderr, flush=True)

# Custom encoder for JSON class to convert bytes to hex
class BytesEncoder(json.JSONEncoder):
    def default(self, o):
        if isinstance(o, bytes):
            return o.hex()
        return json.JSONEncoder.default(self, o)

def main():
    eprint("Setting up PKI...")
    # Set up public key infrastructure
    admin_key = rsa.genrsa()

    # Dictionary mapping voter ID number to voter objects
    random_voters = {}
    all_voters = {}
    
    # Generate random voters & administrator, counter
    eprint("Generating random voters...")
    for _ in range(NUM_VOTERS):
        voter_id = secrets.randbits(16)
        random_voters[voter_id] = Voter(voter_id, rsa.genrsa(), admin_key)
        all_voters[voter_id] = random_voters[voter_id]
        
    # Generate the last voter's information
    last_voter_id = secrets.randbits(16)
    all_voters[last_voter_id] = Voter(last_voter_id, rsa.genrsa(), admin_key)
    
    # Dictionary mapping voter ID to voter's public keypair
    voters_list = {ident: (voter.N, voter.publicExp) for ident, voter in all_voters.items()}
    administrator = Administrator(admin_key, voters_list)
    counter = Counter(voters_list, (admin_key[0], admin_key[1]))
    
    # Read in ballot
    ballot: dict
    with open("sample_ballot.json") as f:
        ballot = json.load(f)
    
    # Generate random votes for each voter and cast each vote
    cast_votes = []
    for _, v in random_voters.items():
        vote = { "vote": {} }
        for measure_name, measure in ballot["measures"].items():
            vote["vote"][measure_name] = secrets.choice(measure["responses"])
        # Cast vote
        cast_votes.append(v.cast(vote))
    
    # Send each blinded cast vote to the administrator, who will return a signature
    # Then send the unblinded vote + signature to the counter
    for vote in cast_votes:
        admin_blind_signature = administrator.certify_vote(vote)
        voter_id = vote[0]
        voter = random_voters[voter_id]
        committed_vote = voter.check_admin(admin_blind_signature)
        counter.recv_vote(committed_vote)
    
    # At this point, the current voter (voting from the fronted) is the last one that needs to vote
    # Here, we start to exchange data with the backend
    eprint("Ready.")
    
    # Quick lambda to print JSON to stdout
    printjson = lambda o: print(json.dumps(o, cls=BytesEncoder), flush=True)
    # Transform a sequence to a dictionary given a list of names
    seq_to_dict = lambda s, n: {n[i]: s[i] for i in range(len(s))}
    
    # 1. Send last voter's ID to the frontend
    stdin.readline()
    printjson(last_voter_id)
    
    # 2. Get last voter's ballot from frontend and cast it
    last_voter = all_voters[last_voter_id]
    last_vote = json.loads(stdin.readline())
    cast_vote = last_voter.cast(last_vote)
    # Send cast vote to the frontend
    printjson(seq_to_dict(cast_vote, ["ident", "committed_vote", "signature"]))
    
    # 3. Send admin's blind signature to the frontend
    admin_blindsign = administrator.certify_vote(cast_vote)
    printjson(admin_blindsign)
    # At this point, administrator has all the votes, so it can publish its list of votes
    
    # 4. Unblind admin signature and send it to the frontend
    vote_sign_pair = last_voter.check_admin(admin_blindsign)
    printjson(seq_to_dict(vote_sign_pair, ["committed_vote", "admin_signature"]))
    
    # 5. Send the counter's last ten committed votes to the frontend for the counter tab
    # Each entry in counter.received_votes is a vote/admin-signature pair
    printjson({ "votes": counter.received_votes[-10:] })
    
    # 6. Send the last voter's committed vote to the counter, don't send anything to frontend here
    counter.recv_vote(vote_sign_pair)
    
    # 7. Send the voter's commitment key to the frontend
    last_index_commitkey_pair = last_voter.send_commitment_key(
        administrator.signed_list,
        counter.received_votes
    )
    # The other voters need to do this too
    for voter in random_voters.values():
        index_commitkey_pair = voter.send_commitment_key(
            administrator.signed_list,
            counter.received_votes
        )
        counter.unlock_vote(index_commitkey_pair)
    
    # 8. Unlock the last voter's ballot and send it to the frontend
    last_vote = counter.unlock_vote(last_index_commitkey_pair)
    printjson(last_vote)
    
    # 9. Count the vote and send results
    results = counter.count()
    printjson(results)

if __name__ == "__main__":
    main()
