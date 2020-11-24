from utils.rsa import genrsa, verify_fdh, sign_fdh

class Administrator:
    # List of registered voter ID numbers
    
    #registered_voters: list
    registered_voters = []

    # List of signed votes that passed certain checks
    # signed_votes is a list of tuples of the same form as returned by Voter.cast()
    
    #signed_votes: list
    signed_votes = []

    """
    Creates an Administrator instance and sets up RSA keys
    """
    def __init__(self):
    	N, publicExponent, privateExponent = genrsa()
    	self.N = N
    	self.publicExponent = publicExponent
    	self.privateExponent = privateExponent

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
        # TODO: get Voter's public keys
        try:
	        voter_id = vote[0]
	        encrypted_vote = vote[1]
	        signature = vote[2]
	    except IndexError as err:
	    	print("The vote input is missing some elements".format(err))
	    	raise err

	    # TODO: check whether the input vote will be the same format as returned value of Voter.cast()
	    if voter_id is in Administrator.registered_voters \
	    	and vote in not in signed_votes \
	    	and verify_fdh(encrypted_vote, signature, voter_exponent, voter_N):

	    	return sign_fdh(encrypted_vote, self.privateExponent, self.N)

	    else:
	    	return False



