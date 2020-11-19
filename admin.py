class Administrator:
    # List of registered voter ID numbers
    registered_voters: list

    # List of signed votes that passed certain checks
    # signed_votes is a list of tuples of the same form as returned by Voter.cast()
    signed_votes: list

    def certify_vote(self, vote: tuple):
        """Certify a given vote by running it through multiple checks.
        """
        # TODO Check voter registration
        # TODO Check if voter already sent ballot
        # TODO Verify blind signature

        # TODO Once checks pass, sign blinded vote and return signature
        pass
