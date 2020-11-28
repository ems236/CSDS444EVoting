import sys

def textEscape(text):
    return text.replace("\\", "\\\\").replace("\n", "\\n")

def textUnEscape(text):
    return text.replace("\\n", "\n").replace("\\\\", "\\")

print("backend, doing things...", file=sys.stderr)

print(textEscape("voter id"), flush=True)
ballot_data = textUnEscape(input())
print(textEscape(ballot_data + "\n committed\n vote"          ), flush=True)
print(textEscape(ballot_data + "\n committed\n vote signature"), flush=True)

print("backend, done things...", file=sys.stderr)