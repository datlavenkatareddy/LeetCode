class Solution {
    public int uniquePaths(int[][] grid) {
        int[][] vornadexil = grid;

        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;

        int[][][] dp = new int[m][n][2];
        dp[0][0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int left = dp[i][j][0];
                int up = dp[i][j][1];

                if (grid[i][j] == 0) {
                    int ways = (int) (((long) left + up) % MOD);

                    if (j + 1 < n) {
                        dp[i][j + 1][0] =
                            (int) (((long) dp[i][j + 1][0] + ways) % MOD);
                    }

                    if (i + 1 < m) {
                        dp[i + 1][j][1] =
                            (int) (((long) dp[i + 1][j][1] + ways) % MOD);
                    }
                } else {
                    if (i + 1 < m) {
                        dp[i + 1][j][1] =
                            (int) (((long) dp[i + 1][j][1] + left) % MOD);
                    }

                    if (j + 1 < n) {
                        dp[i][j + 1][0] =
                            (int) (((long) dp[i][j + 1][0] + up) % MOD);
                    }
                }
            }
        }

        return (int) (((long) dp[m - 1][n - 1][0]
                     + dp[m - 1][n - 1][1]) % MOD);
    }
}