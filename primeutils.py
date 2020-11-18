from secrets import randbits

DEFAULT_BITS = 1024
def randprime(bits = DEFAULT_BITS):
    n = randbits(bits)
    while not isprime(n):
        n = randbits(bits)

    return n

SMALL_PRIMES = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97]
def isprime(number):
    #test small primes
    for p in SMALL_PRIMES:
        if number % p == 0:
            return p == number
        
    #do the miller rabin tests
    pass

