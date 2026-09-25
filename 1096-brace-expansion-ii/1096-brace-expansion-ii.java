import java.util.*;

class Solution {
    public List braceExpansionII(String expression) {
        Set resultSet = dfs(expression);
        List result = new ArrayList<>(resultSet);
        Collections.sort(result);
        return result;
    }

    private Set dfs(String s) {
        Set res = new HashSet<>();
        
        // Step 1: Find the first inner-most balanced brace pair
        int right = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '}') {
                right = i;
                break;
            }
        }

        // Base case: No braces left, resolve comma-separated letters
        if (right == -1) {
            for (String str : s.split(",")) {
                if (!str.isEmpty()) {
                    res.add(str);
                }
            }
            return res;
        }

        // Find the matching left brace for the first closing brace
        int left = right;
        while (s.charAt(left) != '{') {
            left--;
        }

        // Extract parts: before '{', inside '{...}', and after '}'
        String before = s.substring(0, left);
        String inside = s.substring(left + 1, right);
        String after = s.substring(right + 1);

        // Expand the inner comma-separated choices and recurse
        String[] options = inside.split(",");
        for (String option : options) {
            // Build reconstructed string and collect results from sub-problems
            res.addAll(dfs(before + option + after));
        }

        return res;
    }
}