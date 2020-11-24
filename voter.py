from ballot import Ballot, Vote
from utils import rsa

import json
import secrets

class Voter:

    def __init__(self, ident: int, keypair: tuple, admin_key: tuple):
        self.ident = ident
        self.N, self.publicExp, self.privateExp = keypair
        self.admin_key = admin_key # (N, public, private)

    def cast(self, vote_str: str):
        """Commit and sign the voter's vote, returning a tuple of (identity, committed vote, signature).
        This gets sent to the administrator for authorization in the form of a blind signature.
        """
        # Read in vote JSON and attach random identifier to vote so we can identify it later
        vote = json.loads(vote_str)
        vote["uuid"] = secrets.randbits(64)
        vote_str = json.dumps(vote)
        vote_bytes = bytes(vote_str.encode('utf-8'))

        # Create random commitment key the same length as the vote
        commitment_key_int = secrets.randbits(8 * len(vote_bytes))
        self.commitment_key = commitment_key_int.to_bytes(len(vote_bytes), 'big')
        committed_vote = rsa.xor(vote_bytes, self.commitment_key)
        blinded_vote = rsa.blind_fdh(committed_vote, self.admin_key[1], self.admin_key[0])
        signature = rsa.sign_fdh(committed_vote, self.privateExp, self.N)
        return (self.ident, blinded_vote, signature)

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
