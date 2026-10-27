class Solution {
    public String[] createGrid(int m, int n) {
        StringBuilder[] grid = new StringBuilder[m];
        
        // Initialize all rows with obstacles '#'
        for (int i = 0; i < m; i++) {
            char[] rowChars = new char[n];
            Arrays.fill(rowChars, '#');
            grid[i] = new StringBuilder(new String(rowChars));
        }
        
        // Create the single valid path along the first column and the last row
        // 1. First column: from row 0 to m - 1
        for (int i = 0; i < m; i++) {
            grid[i].setCharAt(0, '.');
        }
        
        // 2. Last row: from column 0 to n - 1
        for (int j = 0; j < n; j++) {
            grid[m - 1].setCharAt(j, '.');
        }
        
        // Convert StringBuilders to a String array
        String[] result = new String[m];
        for (int i = 0; i < m; i++) {
            result[i] = grid[i].toString();
        }
        
        return result;
    }
}