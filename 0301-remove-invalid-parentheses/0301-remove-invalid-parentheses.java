import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();

        int leftRemove = 0;
        int rightRemove = 0;

        // Find minimum removals needed
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftRemove++;
            } else if (c == ')') {
                if (leftRemove > 0) {
                    leftRemove--;
                } else {
                    rightRemove++;
                }
            }
        }

        dfs(s, 0, leftRemove, rightRemove, result);

        return result;
    }

    private void dfs(String s, int start,
                     int leftRemove, int rightRemove,
                     List<String> result) {

        // All required removals are completed
        if (leftRemove == 0 && rightRemove == 0) {
            if (isValid(s)) {
                result.add(s);
            }
            return;
        }

        for (int i = start; i < s.length(); i++) {

            // Skip duplicate removals at the same level
            if (i > start && s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }

            // Remove '('
            if (leftRemove > 0 && s.charAt(i) == '(') {
                String next = s.substring(0, i) + s.substring(i + 1);

                dfs(next, i, leftRemove - 1,
                    rightRemove, result);
            }

            // Remove ')'
            if (rightRemove > 0 && s.charAt(i) == ')') {
                String next = s.substring(0, i) + s.substring(i + 1);

                dfs(next, i, leftRemove,
                    rightRemove - 1, result);
            }
        }
    }

    // Check whether the parentheses are valid
    private boolean isValid(String s) {
        int balance = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                balance--;

                if (balance < 0) {
                    return false;
                }
            }
        }

        return balance == 0;
    }
}