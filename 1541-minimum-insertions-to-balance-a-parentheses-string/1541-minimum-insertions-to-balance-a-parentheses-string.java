class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int openCount = 0; // Tracks needed ')' characters (2 for each '(')
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                // If openCount is odd, we have an unmatched single ')' from before.
                // We must insert a ')' to complete the pair.
                if (openCount % 2 != 0) {
                    insertions++;
                    openCount--; // Balance out the single ')'
                }
                openCount += 2; // Each '(' needs two ')'
            } else { // c == ')'
                openCount--;
                
                // If openCount becomes negative, we encountered a ')' without a preceding '('
                if (openCount < 0) {
                    insertions++; // Insert '('
                    openCount += 2; // The newly inserted '(' requires two ')' (one is current char, one is still needed)
                }
            }
        }
        
        // Add any remaining required ')' closing parentheses
        return insertions + openCount;
    }
}