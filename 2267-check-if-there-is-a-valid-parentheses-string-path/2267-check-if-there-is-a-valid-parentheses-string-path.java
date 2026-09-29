class Solution {
    private Boolean[][][] memo;

    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Path length must be even for valid parentheses
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // Must start with '(' and end with ')'
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        // Allocate (m + n) / 2 + 1 size so index up to (m + n) / 2 is valid
        memo = new Boolean[m][n][(m + n) / 2 + 1];

        return dfs(grid, 0, 0, 0, m, n);
    }

    private boolean dfs(char[][] grid, int r, int c, int open, int m, int n) {
        if (grid[r][c] == '(') {
            open++;
        } else {
            open--;
        }

        if (open < 0) {
            return false;
        }

        // Prune if open count exceeds remaining steps to reach the end
        int remainingSteps = (m - 1 - r) + (n - 1 - c);
        if (open > remainingSteps) {
            return false;
        }

        if (r == m - 1 && c == n - 1) {
            return open == 0;
        }

        if (memo[r][c][open] != null) {
            return memo[r][c][open];
        }

        boolean result = false;

        if (r + 1 < m) {
            result = result || dfs(grid, r + 1, c, open, m, n);
        }

        if (c + 1 < n) {
            result = result || dfs(grid, r, c + 1, open, m, n);
        }

        return memo[r][c][open] = result;
    }
}