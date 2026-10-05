class Solution {
    public int scoreOfParentheses(String s) {
        int score = 0;
        int depth = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                depth++;
            } else {
                depth--;
                // Check if this closing parenthesis forms a core "()"
                if (s.charAt(i - 1) == '(') {
                    score += 1 << depth; // Add 2^depth
                }
            }
        }

        return score;
    }
}