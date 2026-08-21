class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;

        long left = 1;
        long right = (long) Arrays.stream(coins).min().getAsInt() * k;

        while (left < right) {
            long mid = left + (right - left) / 2;
            if (count(mid, coins) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private long count(long x, int[] coins) {
        int n = coins.length;
        long ans = 0;

        for (int mask = 1; mask < (1 << n); mask++) {
            long lcm = 1;
            boolean valid = true;
            int bits = Integer.bitCount(mask);

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    lcm = lcm(lcm, coins[i]);
                    if (lcm > x) {
                        valid = false;
                        break;
                    }
                }
            }

            if (!valid) continue;

            if ((bits & 1) == 1) {
                ans += x / lcm;
            } else {
                ans -= x / lcm;
            }
        }

        return ans;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
}