from ballot import Ballot, Vote

import random

class Voter:
    ident: int
    vote: Vote

    def __init__(self, ident: int):
        self.ident = ident

    def fill(self, ballot: Ballot) -> Vote:
        """Fill out a ballot and return a vote with specific responses.
        """
        pass

    def cast(self, vote: Vote):
        """Commit and sign the voter's vote, returning a tuple of (identity, committed vote, signature).
        """
        pass

    def check_admin(self, admin_signature):
        """Prepare to send the vote to the counter given the administrator's blind signature.
        """
        # TODO unblind admin's signature and check against original committed vote
        # Return (committed vote, signature) to send to counter
        pass

    def send_commitment_key(self, vote_list: list):
        """Find the index voter's vote in the given list of votes (published by the counter),
        then return the key paired with that index, to be send anonymously.
        """
        pass
