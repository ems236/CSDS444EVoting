import json
import secrets
from sys import stderr

from utils import rsa

from backend.admin import Administrator
from backend.counter import Counter
from backend.voter import Voter

# Number of voters to simulate
NUM_VOTERS = 5

BALLOT = "sample_ballot.json"

eprint = lambda m: print(m, file=stderr)

def main():
    eprint("Setting up PKI...")
    # Set up public key infrastructure
    admin_key = rsa.genrsa()

    # Dictionary mapping voter ID number to voter objects
    voters = {}
    
    # Generate random voters & administrator, counter
    eprint("Generating random voters...")
    for _ in range(NUM_VOTERS):
        voter_id = secrets.randbits(16)
        voters[voter_id] = Voter(voter_id, rsa.genrsa(), admin_key)
    
    # Dictionary mapping voter ID to voter's public keypair
    voters_list = {ident: (voter.N, voter.publicExp) for ident, voter in voters.items()}
    administrator = Administrator(admin_key, voters_list)
    counter = Counter(voters_list, (admin_key[0], admin_key[1]))
    
    # Read in ballot
    ballot: dict
    with open("sample_ballot.json") as f:
        ballot = json.load(f)
    
    # Generate random votes for each voter and cast each vote
    cast_votes = []
    for _, v in voters.items():
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
        voter = voters[voter_id]
        committed_vote = voter.check_admin(admin_blind_signature)
        counter.recv_vote(committed_vote)
    
    # At this point, the current voter (voting from the fronted) is the last one that needs to vote
    
    # TODO Loop to listen to STDIN forever and pass data to backend classes
    pass

if __name__ == "__main__":
    main()
