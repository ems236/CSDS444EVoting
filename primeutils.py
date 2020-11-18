from secrets import randbits, randbelow

def randinrange(min, max):
    return min + randbelow(max - min)

DEFAULT_BITS = 1024
#this is a legitimate algorithm I swear
def randprime(bits = DEFAULT_BITS):
    n = randbits(bits)
    while not isprime(n):
        n = randbits(bits)

    return n

SMALL_PRIMES = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97]
MILLER_RABIN_TEST_COUNT = 6
def isprime(number, tests=MILLER_RABIN_TEST_COUNT):
    #test small primes because it's faster this way 
    for p in SMALL_PRIMES:
        if number % p == 0:
            return p == number
        
    #miller rabin tests, 6 tests gives super 
    (u, v) = odd_powerof2_decomposition(number - 1)
    for _ in range(0, tests):
        if miller_rabin_is_composite(number, u, v):
            return False

    return True

def miller_rabin_is_composite(n, u, v):
    b = randinrange(2, n - 2)
    current = pow(b, v, n)
    #all square roots of 1 are +- 1
    if current == 1 or current == n - 1:
        return False

    for _ in range(1, u):
        current = pow(current, 2, n)
        #square root of 1 is not +- 1
        if current == 1:
            return True
        
        #all square roots of 1 will be +- 1 
        if current == n - 1:
            return False
    
    #b**n-1 != 1, fermats little theorem is false
    return True

#returns (u,v) such that n = (2**u)v and v is odd
def odd_powerof2_decomposition(n):
    v = n
    u = 0
    while v % 2 == 0:
        v //= 2
        u += 1

    return (u,v)
