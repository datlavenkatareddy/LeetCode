class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;

        // Convert stones array into prefix sums
        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }

        // dp represents the maximum score difference
        int dp = stones[n - 1];

        // Try taking prefixes from right to left
        for (int i = n - 2; i >= 1; i--) {
            dp = Math.max(dp, stones[i] - dp);
        }

        return dp;
    }
}