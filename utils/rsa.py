import math

from utils.primes import randprime, rand_relative_prime
from utils.math import powermod, gcd
from utils.sha256 import sha256_fdh_below

def to_bytes(n: int):
    byteSize = math.ceil(n.bit_length() / 8)
    return n.to_bytes(byteSize, 'big') 

DEFAULT_BITS = 1024
#DEFAULT_PUBLIC_EXPONENT = 65537

def genrsa(bits=DEFAULT_BITS):
    """returns (N=pq,  public exponent,  private exponent) such that pub*priv = 1 mod phi(N)"""
    p = randprime(bits // 2)
    q = randprime(bits // 2)

    N = p*q

    phiN = (p-1)*(q-1)
    publicExponent = rand_relative_prime(bits // 2, phiN) 

    privateExponent = powermod(publicExponent, -1 , phiN)
    return (N, publicExponent, privateExponent)
 
def encrypt_simple(m: int, publicExponent: int, N: int):
    """textbook rsa encryption, don't use outside of testing"""
    return powermod(m, publicExponent, N)

def decrypt_simple(c: int, privateExponent: int, N: int):
    """textbook rsa decrpytion, don't use outside of testing"""
    return powermod(c, privateExponent, N)

def encrypt_oaep(m: bytes, publicExponent: int, N:int):
    """Unimplemented. We don't need it, but it seems cool and it could replace the xor commitment signing"""
    pass

def decrypt_oaep(c: bytes, publicExponent: int, N:int):
    """Unimplemented. We don't need it, but it seems cool and it could replace the xor commitment signing"""
    pass

def sign_fdh(m: bytes, privateExponent: int, N: int):
    """ returns signature as bytes
    Uses sha256 fda hash to pad a message and sign it. sig = fdh(m)^privateexp mod N"""

    padded = sha256_fdh_below(m, N)
    signature_int = powermod(int.from_bytes(padded, 'big'), privateExponent, N)
    return to_bytes(signature_int)

def verify_fdh(m: bytes, signature: bytes, publicExponent: int, N: int):
    """ returns true if signature matches message
    Uses sha256 fda hash to pad a message and verify a signature. 
    fdh(m) == sig^pubexp mod N"""

    signature_digest = powermod(int.from_bytes(signature, 'big'), publicExponent, N)
    digest = sha256_fdh_below(m, N)

    return signature_digest == int.from_bytes(digest, 'big')

def blind_fdh(m: bytes, signerPublicExponent: int, signerN: int):
    """returns a blinded fdh-padded message and the nonce r used for blinding
        blinded = fdh(m) * r^pubexp mod n
        where r is a nonce and relatively prime to n"""
    digest = sha256_fdh_below(m, signerN)
    #TODO are there any size constraints on this value?
    nonce = rand_relative_prime(signerN.bit_length(), signerN)

    digestInt = int.from_bytes(digest, 'big')
    blinded = (digestInt * powermod(nonce, signerPublicExponent, signerN)) % signerN
    
    return (to_bytes(blinded), nonce)

def unblind(blindSignature: bytes, nonce: int, signerN: int, m: bytes = None, signerPublicExponent: int = None):
    """returns an unblinded signature.  if m and signerPubExponent are set, it will also verify that the signature is valid
        unblinded = blinded * r^-1 mod n
    """
    inverse = powermod(nonce, -1, signerN)
    blindedInt = int.from_bytes(blindSignature, 'big')
    unblindedInt = (blindedInt * inverse) % signerN
    unblindedSignature = to_bytes(unblindedInt)

    #verify sig
    if m is not None and not verify_fdh(m, unblindedSignature, signerPublicExponent, signerN):
        raise ValueError("Blind signature was invalid. Unblinding failed")

    return unblindedSignature


def blind_sign(m: bytes, privateExponent: int, N: int):
    """Blinded message has already beed padded.  Pretty much just textbook rsa at this step
    sig = msg^privateexp mod n
    Pretty sure this opens some attacks against the signer
    most places say don't reuse this key pair anywhere else https://www.npmjs.com/package/rsa-fdh"""
    messageInt = int.from_bytes(m, 'big')
    return to_bytes(powermod(messageInt, privateExponent, N)) 


def test():
    (mymod, mypub, mypriv) = genrsa()
    mymessage = "hello world!".encode("utf-8")

    sig = sign_fdh(mymessage, mypriv, mymod)
    print(sig)
    print(verify_fdh(mymessage, sig, mypub, mymod))

    (blinded, nonce) = blind_fdh(mymessage, mypub, mymod)
    print(blinded)

    blind_sig = blind_sign(blinded, mypriv, mymod)
    print(blind_sig)

    unblinded = unblind(blind_sig, nonce, mymod, mymessage, mypub)
    print(unblinded)

    print(verify_fdh(mymessage, unblinded, mypub, mymod))
