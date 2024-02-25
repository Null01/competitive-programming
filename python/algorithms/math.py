"""

Get the firsts N (limit) integers that are prime numbers.

Args:
   limit: Firsts N numbers that required return

Returns:
   Return a list of the first n numbers primes.

Raises:
   None
"""


def generate_primes(limit: int):
    primes = []
    is_prime = [True] * (limit * 10)  # Assuming a larger range to ensure enough primes

    for num in range(2, int(limit * 10 ** 0.5) + 1):
        if is_prime[num]:
            primes.append(num)
            for multiple in range(num * num, limit * 10, num):
                is_prime[multiple] = False

    # Extend the list if needed
    while len(primes) < limit:
        primes.append(is_prime[len(primes)] * len(primes))

    return primes[:limit]
