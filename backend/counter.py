import json
from utils.rsa import verify_fdh, xor
from io import StringIO
from sys import stderr

class Counter:

    """
    Creates a Counter instance given:
        voters_list: dictionary of voter_id to public keys
        admin_public_keys: tuple containing admin's public keys (N, exponent)
        received_votes: list of votes already received
        unlocked_votes: dictionary of index to unlocked votes TODO: should we use array of byte arrays? or call this good enough
    """
    def __init__(self, voters_list, admin_public_keys):
        self.voters_list = voters_list
        self.admin_public_keys = admin_public_keys
        self.received_votes = []
        self.unlocked_votes = {}


    """
    Adds the received vote if given signature is valid
    Returns True if the vote is accepted. Returns False otherwise.
    """
    def recv_vote(self, vote_sign_pair: tuple):
        """Check an incoming voter's vote/signature by checking the signature,
        then adding it to the list of votes
        """

        try:
            vote = vote_sign_pair[0]
            signature = vote_sign_pair[1]
            signer_N = self.admin_public_keys[0]
            signer_exponent = self.admin_public_keys[1]
        except IndexError as err:
            print("vote_sign_pair or signer_public_keys not containing enough values", file=stderr)
            raise err

        if verify_fdh(vote, signature, signer_exponent, signer_N):
            self.received_votes.append(vote_sign_pair)
            return True
        else:
            return False



    def unlock_vote(self, index_key_pair: tuple):
        """Unlock a voter's vote with the given key at the given index,
        then add it to the list of unlocked votes.
        """
        try:
            index = index_key_pair[0]
            key = index_key_pair[1]
        except IndexError as err:
            print("missing either index or key in the input", file=stderr)
            raise err

        if index >= len(self.received_votes):
            print("vote not received previously", file=stderr)
            raise IndexError()

        vote = self.received_votes[index]

        #unlocking the vote
        revealed_vote = xor(vote, key)

        """
        check the unlocked vote's validity 
        """
        f = StringIO(str(revealed_vote, "utf-8"))
        try:
        	json.load(f)
        except JSONDecoderError as err:
        	print("unlocked vote is not in a valid json format", file=stderr)
        	raise err


        #adding revealed vote to the list
        self.unlocked_votes[index] = revealed_vote

        return revealed_vote



    def count(self):
        """Count all unlocked votes and return the results of the election.
        """

        #Initializing return format using ballot json
        ballot = json.load(open("sample_ballot.json"))
        result = {}
        for measure in ballot["measures"]:
        	result[measure] = {}
        	for response in ballot["measures"][measure]["responses"]:
        		result[measure][response] = 0



        #Counting
        for v in self.unlocked_votes.values():
        	f = StringIO(str(v, "utf-8"))
        	vote = json.load(f)

        	for measure in vote["vote"]:
        		result[measure][vote["vote"][measure]] += 1



        return result
