from ballot import Vote

class Counter:
    # List of all committed votes received from voters
    votes: list

    # List of unlocked votes received from voters
    unlocked_votes: list

    def recv_vote(self, vote_sign_pair: tuple):
        """Check an incoming voter's vote/signature by checking the signature,
        then adding it to the list of votes
        """
        # TODO Check vote_sign_pair[1] against vote_sign_pair[0],
        # then add to list of votes
        pass

    def unlock_vote(self, index_key_pair: tuple):
        """Unlock a voter's vote with the given key at the given index,
        then add it to the list of unlocked votes.
        """
        pass

    def count(self):
        """Count all unlocked votes and return the results of the election.
        """
        pass
