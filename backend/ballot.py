class Ballot:
    # Questions should be formatted as a list of tuples containing
    # (The measure in question, a list of valid responses)
    questions: list

class Vote:
    # Responses are simply a list of integers
    # responses[n] = k means the vote for the question in ballot.questions[n] is for the
    # answer in ballot.questions[n][1][k].
    responses: list
    ballot: Ballot
