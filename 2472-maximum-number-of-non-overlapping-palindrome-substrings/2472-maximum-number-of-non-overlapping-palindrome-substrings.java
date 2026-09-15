class Solution {
    public int maxPalindromes(String s, int k) {
        int count = 0;
        int n = s.length();
        int lastEnd = -1; // Tracks the end index of the last chosen palindrome

        // Expand around center for both odd and even lengths
        for (int i = 0; i < n; i++) {
            // Check for palindromes of length k or k+1 starting around center i
            for (int len : new int[]{k, k + 1}) {
                int left = i - len / 2;
                int right = i + (len - 1) / 2;

                // Ensure the candidate substring starts after the last chosen palindrome
                if (left > lastEnd && isPalindrome(s, left, right)) {
                    count++;
                    lastEnd = right; // Update last end index
                    break;           // Move to search past this valid palindrome
                }
            }
        }

        return count;
    }

    private boolean isPalindrome(String s, int left, int right) {
        if (left < 0 || right >= s.length()) {
            return false;
        }
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}