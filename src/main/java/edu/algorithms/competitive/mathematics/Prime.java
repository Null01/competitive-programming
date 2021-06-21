package edu.algorithms.competitive.mathematics;

import java.util.ArrayList;
import java.util.List;

public class Prime {

    public List<Integer> find_prime_until_n(long n) {
        int MAX_COUNT_PRIME = (int) n;
        boolean prime[] = new boolean[MAX_COUNT_PRIME + 1];
        List<Integer> list = new ArrayList<>();
        list.add(2);
        for (int i = 3; (i * i) <= MAX_COUNT_PRIME; i += 2) {
            if (!prime[i]) {
                for (int k = (i * i); k <= MAX_COUNT_PRIME; k += (2 * i)) {
                    prime[k] = true;
                }
            }
        }
        for (int i = 3; i <= n; i += 2) {
            if (!prime[i]) {
                list.add(i);
            }
        }
        return list;

    }

    public List<Integer> find_factors_prime(Long n, List<Integer> primes) {
        List<Integer> factors = new ArrayList<Integer>();
        Integer temp = 1, index = 0;
        while (n != 1 && index < primes.size()) {
            if (n % primes.get(index) == 0) {
                n /= primes.get(index);
                temp *= primes.get(index);
            } else {
                factors.add((temp == 1) ? 0 : temp);
                temp = 1;
                ++index;
            }
        }
        factors.add((temp == 1) ? 0 : temp);
        return factors;
    }

}
