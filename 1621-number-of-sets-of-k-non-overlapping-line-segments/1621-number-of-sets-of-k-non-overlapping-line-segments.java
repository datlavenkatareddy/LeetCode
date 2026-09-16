class Solution {
    public int numberOfSets(int n, int k) {
        long mod = 1_000_000_007;
        int totalN = n + k - 1;
        int totalK = 2 * k;

        if (totalN < totalK) {
            return 0;
        }

        // Compute C(totalN, totalK) % mod
        long numerator = 1;
        long denominator = 1;

        for (int i = 1; i <= totalK; i++) {
            numerator = (numerator * (totalN - i + 1)) % mod;
            denominator = (denominator * i) % mod;
        }

        // Return (numerator * denominator^(mod - 2)) % mod using Fermat's Little Theorem
        return (int) ((numerator * modInverse(denominator, mod)) % mod);
    }

    private long modInverse(long base, long mod) {
        return power(base, mod - 2, mod);
    }

    private long power(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}