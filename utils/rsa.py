import math
from secrets import randbits

from utils.primes import randprime, rand_relative_prime
from utils.math import powermod, gcd
from utils.sha256 import sha256_fdh_below, sha256_fdh

def to_bytes(n: int):
    byteSize = math.ceil(n.bit_length() / 8)
    return n.to_bytes(byteSize, 'big') 

def xor(a: bytes, b: bytes):
    if len(a) != len(b):
        raise ValueError("length mismatch in xor")

    aint = int.from_bytes(a, 'big')
    bint = int.from_bytes(b, 'big')

    xorint = aint ^ bint
    return xorint.to_bytes(len(a), 'big')


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


OAEP_SEED_BYTES = 32
def encrypt_oaep(m: bytes, publicExponent: int, N:int):
    """
    Encrypts a message m, but adds some fancy padding first.  Prevents chosen plaintext attacks from working
    We don't need this, but it seemed fun and could replace the xor commitment signing
    see https://tools.ietf.org/html/rfc3447#section-7.1
    """
    
    cipherSize = N.bit_length() // 8 - OAEP_SEED_BYTES - 1
    paddingSize = cipherSize - len(m) - 1
    if paddingSize < 0:
        #would reasonably turn this into blocks instead
        raise ValueError("Message too long")

    seed = randbits(OAEP_SEED_BYTES * 8).to_bytes(OAEP_SEED_BYTES, 'big')
    padded = (b"\x00" * paddingSize) + b"\x01" + m
    
    hashedSeed = sha256_fdh(seed, target_length=cipherSize)
    
    cipher = xor(padded, hashedSeed)

    hashedCipher = sha256_fdh(cipher, target_length=OAEP_SEED_BYTES)
    maskedSeed = xor(hashedCipher, seed)

    msg_oaep = int.from_bytes(b"\x00" + maskedSeed + cipher, 'big')
    return to_bytes(powermod(msg_oaep, publicExponent, N))
    

def decrypt_oaep(c: bytes, privateExponent: int, N:int):
    """ 
    Decrypts a message c, and removes the oaep padding to find the actual message
    We don't need this, but it seemed fun and could replace the xor commitment signing
    see https://tools.ietf.org/html/rfc3447#section-7.1
    """

    totalSize = N.bit_length() // 8
    cipherSize = totalSize - OAEP_SEED_BYTES - 1

    cipherTextInt = int.from_bytes(c, 'big')
    decrypted = powermod(cipherTextInt, privateExponent, N).to_bytes(totalSize, 'big')

    maskedSeed = decrypted[1:OAEP_SEED_BYTES + 1]
    cipher = decrypted[OAEP_SEED_BYTES + 1:]

    hashedCipher = sha256_fdh(cipher, target_length=OAEP_SEED_BYTES)

    seed = xor(hashedCipher, maskedSeed)

    hashedSeed = sha256_fdh(seed, target_length=cipherSize)

    paddedMsg = xor(cipher, hashedSeed)

    return paddedMsg[paddedMsg.find(b"\x01") + 1:]

    

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

    enc = encrypt_oaep(mymessage, mypub, mymod)
    print(enc)

    dec = decrypt_oaep(enc, mypriv, mymod)
    print(dec)
