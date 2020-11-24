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
        self.vote_uuid = secrets.randbits(64)
        vote["uuid"] = self.vote_uuid
        vote_str = json.dumps(vote)
        vote_bytes = bytes(vote_str.encode('utf-8'))

        # Create random commitment key the same length as the vote
        commitment_key_int = secrets.randbits(8 * len(vote_bytes))
        self.commitment_key = commitment_key_int.to_bytes(len(vote_bytes), 'big')
        self.committed_vote = rsa.xor(vote_bytes, self.commitment_key)
        blinded_vote = rsa.blind_fdh(self.committed_vote, self.admin_key[1], self.admin_key[0])
        signature = rsa.sign_fdh(blinded_vote, self.privateExp, self.N)
        return (self.ident, blinded_vote, signature)

    def check_admin(self, admin_blind_signature, blind_nonce):
        """Prepare to send the vote to the counter given the administrator's blind signature.
        """
        # Unblind admin's signature and check against original committed vote
        # Exception will be thrown if signature was invalid
        admin_signature = rsa.unblind(
            admin_blind_signature,
            blind_nonce,
            self.admin_key[0],
            self.committed_vote,
            self.admin_key[1]
        )

        # Return (committed vote, signature) to send to counter
        return (self.committed_vote, admin_signature)

    def send_commitment_key(self, admin_vote_list: list, counter_vote_list: list):
        """Find the index voter's vote in the given list of votes (published by the counter),
        then return the key paired with that index, to be send anonymously.
        """
        # Check that lists have the same length
        if len(admin_vote_list) != len(counter_vote_list):
            raise ValueError("Administrator and counter lists have different lengths")

        # Find index of voter's ballot in counter's list
        voter_ballot_candidates = [
            # Filter counter list to check if committed vote and UUID are equal
            idx for idx, vote in enumerate(counter_vote_list) if
            vote[0] == self.committed_vote and json.loads(vote[0])["uuid"] == self.vote_uuid
        ]
        if not voter_ballot_candidates:
            raise ValueError(f"Voter {self.ident}'s ballot not found in counter's list")

        # Return (index, commitment key) for sending to counter
        return (voter_ballot_candidates[0], self.commitment_key)
