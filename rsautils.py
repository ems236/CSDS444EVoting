from primeutils import randprime

from math import gcd

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

    privateExponent = pow(DEFAULT_PUBLIC_EXPONENT, -1 , phiN)
    return (N, DEFAULT_PUBLIC_EXPONENT, privateExponent)


def encrypt(m, publicExponent, N):
    return pow(m, publicExponent, N)

def decrypt(c, privateExponent, N):
    return pow(c, privateExponent, N)

