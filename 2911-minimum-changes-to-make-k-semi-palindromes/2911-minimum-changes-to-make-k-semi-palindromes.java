import java.util.*;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int[][] cost = new int[n][n];
        int INF = 1000000;

        // Precompute the cost for every substring
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = j - i + 1;
                cost[i][j] = INF;

                // Try every valid divisor
                for (int d = 1; d < len; d++) {
                    if (len % d != 0) continue;

                    int changes = 0;

                    // Check each group for palindrome property
                    for (int offset = 0; offset < d; offset++) {
                        int l = i + offset;
                        int r = j - d + 1 + offset;

                        while (l < r) {
                            if (s.charAt(l) != s.charAt(r)) {
                                changes++;
                            }
                            l += d;
                            r -= d;
                        }
                    }

                    cost[i][j] = Math.min(cost[i][j], changes);
                }
            }
        }

        // dp[i][p] = minimum changes to divide s[i...n-1]
        // into exactly p semi-palindromes
        int[][] dp = new int[n + 1][k + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[n][0] = 0;

        for (int i = n - 1; i >= 0; i--) {
            for (int p = 1; p <= k; p++) {
                for (int j = i + 1; j < n; j++) {
                    if (dp[j + 1][p - 1] == INF) continue;

                    dp[i][p] = Math.min(
                        dp[i][p],
                        cost[i][j] + dp[j + 1][p - 1]
                    );
                }
            }
        }

        return dp[0][k];
    }
}