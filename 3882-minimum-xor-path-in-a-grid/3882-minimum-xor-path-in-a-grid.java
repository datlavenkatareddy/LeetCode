import java.util.HashSet;
import java.util.Set;

class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // dp[j] will store the set of possible XOR values at column j for the current row
        Set<Integer>[] dp = new HashSet[n];
        for (int j = 0; j < n; j++) {
            dp[j] = new HashSet<>();
        }
        
        // Base case: starting cell (0, 0)
        dp[0].add(grid[0][0]);
        
        // Fill first row (can only come from left)
        for (int j = 1; j < n; j++) {
            for (int val : dp[j - 1]) {
                dp[j].add(val ^ grid[0][j]);
            }
        }
        
        // Process remaining rows
        for (int i = 1; i < m; i++) {
            // First cell of the row (can only come from above)
            Set<Integer> nextFirstRow = new HashSet<>();
            for (int val : dp[0]) {
                nextFirstRow.add(val ^ grid[i][0]);
            }
            dp[0] = nextFirstRow;
            
            // Remaining cells in the row (can come from above or left)
            for (int j = 1; j < n; j++) {
                Set<Integer> newSet = new HashSet<>();
                // From above (dp[j])
                for (int val : dp[j]) {
                    newSet.add(val ^ grid[i][j]);
                }
                // From left (dp[j-1])
                for (int val : dp[j - 1]) {
                    newSet.add(val ^ grid[i][j]);
                }
                dp[j] = newSet;
            }
        }
        
        // Find the minimum XOR value at the bottom-right cell (m - 1, n - 1)
        int minXor = Integer.MAX_VALUE;
        for (int val : dp[n - 1]) {
            minXor = Math.min(minXor, val);
        }
        
        return minXor;
    }
}