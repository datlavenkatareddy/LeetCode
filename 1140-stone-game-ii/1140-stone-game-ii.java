class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;

        // suffix[i] = sum of piles from i to n-1
        int[] suffix = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        // dp[i][m] = maximum stones the current player
        // can obtain starting at index i with M = m
        int[][] dp = new int[n + 1][n + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int m = 1; m <= n; m++) {

                // Can take all remaining piles
                if (i + 2 * m >= n) {
                    dp[i][m] = suffix[i];
                    continue;
                }

                int best = 0;

                // Take x piles, where 1 <= x <= 2*m
                for (int x = 1; x <= 2 * m; x++) {
                    best = Math.max(
                        best,
                        suffix[i] - dp[i + x][Math.max(m, x)]
                    );
                }

                dp[i][m] = best;
            }
        }

        return dp[0][1];
    }
}