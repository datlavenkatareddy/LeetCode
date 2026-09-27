import java.util.*;

class Solution {
    public String reverseParentheses(String s) {
        Stack<StringBuilder> stack = new Stack<>();
        StringBuilder current = new StringBuilder();

        for (char c : s.toCharArray()) {

            if (c == '(') {
                // Save the current string
                stack.push(current);

                // Start a new string inside parentheses
                current = new StringBuilder();

            } else if (c == ')') {
                // Reverse the content inside parentheses
                current.reverse();

                // Get the string before '('
                StringBuilder previous = stack.pop();

                // Append reversed content
                previous.append(current);

                current = previous;

            } else {
                // Normal character
                current.append(c);
            }
        }

        return current.toString();
    }
}