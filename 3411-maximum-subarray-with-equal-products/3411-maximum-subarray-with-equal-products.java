class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxVal = 0;
        long overallLcm = 1L;

        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
            overallLcm = lcm(overallLcm, x);
        }

        long maxP = overallLcm * maxVal;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            long p = 1L;
            long g = 0L;
            long l = 1L;

            for (int j = i; j < n; j++) {
                long val = nums[j];
                p *= val;
                g = gcd(g, val);
                l = lcm(l, val);

                if (p == g * l) {
                    ans = Math.max(ans, j - i + 1);
                }
                if (p > maxP) {
                    break;
                }
            }
        }

        return ans;
    }

    private long gcd(long a, long b) {
        while (b != 0L) {
            long tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        if (a == 0L || b == 0L) {
            return 0L;
        }
        return (a / gcd(a, b)) * b;
    }
}