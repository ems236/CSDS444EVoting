from utils.rsa import verify_fdh, blind_sign
from sys import stderr

class Administrator:

    """
    Creates an Administrator instance with list of voters and sets up RSA keys

    voters_list: dictionary of registered voters with voter_id as a key and public key pair as a value
    """
    def __init__(self, rsa_keypair, voters_list):
        self.N, self.publicExponent, self.privateExponent = rsa_keypair
        self.voters_list = voters_list
        self.signed_list = []

    """
    Returns tuple of public keys, N and public Exponent
    """
    def get_public_keys(self):
        return self.N, self.publicExponent


    """
    Given encrypted vote with signature, verify the vote.

    Returns blinded ballot if the voter is registered, hasn't submitted ballot, and signature is valid
    returns None otherwise.

    input: vote is a tuple consisting of (voter_id, encrypted vote, signature)
    """
    def certify_vote(self, vote: tuple):
        """Certify a given vote by running it through multiple checks.
        """

        # checking the input format
        try:
            voter_id = vote[0]
            encrypted_vote = vote[1]
            signature = vote[2]
            voter_N = self.voters_list[voter_id][0]
            voter_exponent = self.voters_list[voter_id][1]
        except IndexError as err1:
            print("The vote input is missing some elements", file=stderr)
            raise err1
        except ValueError as err2:
            print("No public keys found for given voter", file=stderr)
            raise err2

        # TODO: check whether the input vote will be the same format as returned value of Voter.cast()
        if voter_id in self.voters_list \
            and vote not in self.signed_list \
            and verify_fdh(encrypted_vote, signature, voter_exponent, voter_N):

            self.signed_list.append(vote)

            return blind_sign(encrypted_vote, self.privateExponent, self.N)

        else:
            return None
