from bitarray import bitarray, util
from operator import add
# Initialize hash values:
# (first 32 bits of the fractional parts of the square roots of the first 8 primes 2..19):
initial_hash = [
    0x6a09e667,
    0xbb67ae85,
    0x3c6ef372,
    0xa54ff53a,
    0x510e527f,
    0x9b05688c,
    0x1f83d9ab,
    0x5be0cd19
]

# Initialize array of round constants:
# (first 32 bits of the fractional parts of the cube roots of the first 64 primes 2..311):
k = [0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5, 0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5,\
   0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3, 0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174,\
   0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc, 0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,\
   0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7, 0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967,\
   0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13, 0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85,\
   0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3, 0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070,\
   0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5, 0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3,\
   0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208, 0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2]

pow2 = pow(2,32)

def rightrotate(a: bitarray, n: int):
    # rotate a by n bits to the right
    return a[-n:] + a[:-n]

def rightshift(a: bitarray, n: int):
    # shift a by n bits to the right, fill with 0s
    return bitarray('0')*n + a[:-n]

def int2ba32(a: int):
    return util.int2ba(a, length=32, endian='big')

def sha256(message: str):
    # Taken from Wikipedia pseudocode
    # https://en.wikipedia.org/wiki/SHA-2#Pseudocode
    # convert message to ascii, append into bit array
    bit_msg = bitarray(''.join(format(ord(c), '08b') for c in message))
    L = len(bit_msg)

    # append 1 followed by K 0s where K is the minimum number >= 0 such that 
    # len(bit_msg) + 1 + K + 64 is a multiple of 512
    bit_msg = bit_msg + bitarray('1') + (bitarray('0') * ((-L-65) % 512))
    # append len(bit_msg) as a 64-bit int to bit_msg
    bit_msg = bit_msg + util.int2ba(L, length=64, endian='big')

    # initialize hash to predefined values
    current_hash = [h for h in initial_hash]

    # operate on each 512-bit chunk
    for chunk_index in range(len(bit_msg)//512):
        chunk = bit_msg[chunk_index * 512 : (chunk_index+1) * 512]
        # w is array of 64 32-bit words with first 16 equal to chunk
        w = [chunk[i*32 : (i+1)*32] for i in range(16)]
        w.extend([bitarray(32) for _ in range(48)])
        # create last 48 words in w from first 16
        for i in range(16, 64):
            s0 = rightrotate(w[i-15], 7) ^ rightrotate(w[i-15], 18) ^ rightshift(w[i-15], 3)
            s1 = rightrotate(w[i-2], 17) ^ rightrotate(w[i-2], 19) ^ rightshift(w[i-2], 10)
            w[i] = int2ba32(sum(map(util.ba2int, [w[i-16], s0, w[i-7], s1])) % pow2)

        # copy current hash (stored in hex) into working list v as bitarrays
        v = list(map(int2ba32, current_hash))
        # compression
        for i in range(64):
            S1 = rightrotate(v[4], 6) ^ rightrotate(v[4], 11) ^ rightrotate(v[4], 25)
            ch = (v[4] & v[5]) ^ ((~v[4]) & v[6])
            temp1 = (k[i] + sum(map(util.ba2int, [v[7], S1, ch, w[i]]))) % pow2
            S0 = rightrotate(v[0], 2) ^ rightrotate(v[0], 13) ^ rightrotate(v[0], 22)
            maj = (v[0] & v[1]) ^ (v[0] & v[2]) ^ (v[1] & v[2])
            temp2 = (util.ba2int(S0) + util.ba2int(maj)) % pow2

            # shift elements of v by 1
            for j in reversed(range(1, len(v))):
                v[j] = v[j-1]
            v[0] = int2ba32((temp1 + temp2) % pow2)
            v[4] = int2ba32((util.ba2int(v[4]) + temp1) % pow2)

        # add compressed values (which are bitarrays) to current_hash (which are ints)
        current_hash = list(map(lambda a,b: (a + util.ba2int(b)) % pow2, current_hash, v))

    # each entry of current_hash is a 32-bit integer so convert to 4 bytes 
    # adding bytes appends them
    print(b''.join(x.to_bytes(4, 'big') for x in current_hash).hex())

def main():
    sha256("What the fuck did you just fucking say about me, you little bitch? I'll have you know I graduated top of my class in the Navy Seals, and I've been involved in numerous secret raids on Al-Quaeda, and I have over 300 confirmed kills. I am trained in gorilla warfare and I'm the top sniper in the entire US armed forces. You are nothing to me but just another target. I will wipe you the fuck out with precision the likes of which has never been seen before on this Earth, mark my fucking words. You think you can get away with saying that shit to me over the Internet? Think again, fucker. As we speak I am contacting my secret network of spies across the USA and your IP is being traced right now so you better prepare for the storm, maggot. The storm that wipes out the pathetic little thing you call your life. You're fucking dead, kid. I can be anywhere, anytime, and I can kill you in over seven hundred ways, and that's just with my bare hands. Not only am I extensively trained in unarmed combat, but I have access to the entire arsenal of the United States Marine Corps and I will use it to its full extent to wipe your miserable ass off the face of the continent, you little shit. If only you could have known what unholy retribution your little clever comment was about to bring down upon you, maybe you would have held your fucking tongue. But you couldn't, you didn't, and now you're paying the price, you goddamn idiot. I will shit fury all over you and you will drown in it. You're fucking dead, kiddo.")

if __name__ == "__main__":
    main()
