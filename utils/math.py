#a^b mod n
def powermod(a, b, n):
    if b < 0:
        inv = inverse(a, n)
        if inv == 0:
            raise ValueError
        return powermod(inv, -1 * b, n)

    
    res = 1
    currentSquare = a 
    while b > 0:
        if b % 2 == 1:
            res = (res * currentSquare) % n
            b -= 1 
        
        currentSquare = (currentSquare**2) % n
        b //= 2

    return res


# returns (gcd, x, y) such that ax + by = gcd
def extendedgcd(a, b):
    if a > b:
        g, x, y = extendedgcd(b, a)
        return (g, y, x)

    x0 = 0
    x1 = 1

    y0 = 1
    y1 = 0

    while True:
        (q, b) = divmod(b, a)
        x0 -= x1*q
        y0 -= y1*q
        
        if b == 0:
            return (a, x1, y1)

        (q, a) = divmod(a, b)
        x1 -= x0*q
        y1 -= y0*q

        if a == 0:
            return (b, x0, y0)
    
def gcd(a,b):
    g, _, _ = extendedgcd(a, b)
    return g

#returns 0 if a is not invertable on n
def inverse(a,n):
    g, x, _ = extendedgcd(a, n)
    if g != 1:
        return 0
    
    return x