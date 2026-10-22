class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zero = 0;

        // Find the index of 0
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }

        // Check whether values increase cyclically
        // starting from index zero, in the given direction
        int[] steps = {1, -1};
        int ans = Integer.MAX_VALUE;

        for (int step : steps) {
            boolean valid = true;

            for (int i = 1; i < n; i++) {
                int prev = (zero + (i - 1) * step + n) % n;
                int curr = (zero + i * step + n) % n;

                if (nums[prev] > nums[curr]) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                if (step == 1) {
                    ans = Math.min(ans, zero);
                    ans = Math.min(ans, n - zero + 2);
                } else {
                    ans = Math.min(ans, zero + 2);
                    ans = Math.min(ans, n - zero);
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}