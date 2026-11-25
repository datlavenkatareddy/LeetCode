import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final long BASE = 1000003L;
    private static final long MOD1 = 1000000007L;
    private static final long MOD2 = 1000000009L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        long[] p1 = new long[n + 1];
        long[] p2 = new long[n + 1];
        long[] h1 = new long[n + 1];
        long[] h2 = new long[n + 1];

        p1[0] = 1L;
        p2[0] = 1L;

        for (int i = 0; i < n; i++) {
            long val = nums[i];
            p1[i + 1] = (p1[i] * BASE) % MOD1;
            p2[i + 1] = (p2[i] * BASE) % MOD2;

            h1[i + 1] = (h1[i] * BASE + val) % MOD1;
            h2[i + 1] = (h2[i] * BASE + val) % MOD2;
        }

        int low = 1;
        int high = n;
        int ans = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (hasUniqueSubarray(mid, n, p1, p2, h1, h2)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean hasUniqueSubarray(
            int len, int n, long[] p1, long[] p2, long[] h1, long[] h2) {
        Map<Long, Integer> freq = new HashMap<>();
        long pow1 = p1[len];
        long pow2 = p2[len];

        for (int start = 0; start + len <= n; start++) {
            int end = start + len;

            long hash1 = (h1[end] - (h1[start] * pow1) % MOD1 + MOD1) % MOD1;
            long hash2 = (h2[end] - (h2[start] * pow2) % MOD2 + MOD2) % MOD2;

            long combined = (hash1 << 32) | (hash2 & 0xFFFFFFFFL);
            freq.put(combined, freq.getOrDefault(combined, 0) + 1);
        }

        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }
        return false;
    }
}