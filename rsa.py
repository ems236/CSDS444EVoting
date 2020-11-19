from utils.primes import randprime
from utils.math import gcd, powermod

DEFAULT_BITS = 1024
DEFAULT_PUBLIC_EXPONENT = 65537
def genrsa(bits=DEFAULT_BITS):
    p = randprime(bits // 2)
    q = randprime(bits // 2)

    N = p*q

    phiN = (p-1)*(q-1)
    if gcd(phiN, DEFAULT_PUBLIC_EXPONENT) > 1:
        #try again so you don't have to pick a good public exponent
        return genrsa(bits)

    privateExponent = powermod(DEFAULT_PUBLIC_EXPONENT, -1 , phiN)
    return (N, DEFAULT_PUBLIC_EXPONENT, privateExponent)


def encrypt(m, publicExponent, N):
    return powermod(m, publicExponent, N)

def decrypt(c, privateExponent, N):
    return powermod(c, privateExponent, N)

