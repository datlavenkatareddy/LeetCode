class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int half = n / 2;
        int[] count = new int[26];
        
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Validate if a palindromic permutation is possible
        int oddCount = 0;
        int midChar = -1;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                midChar = i;
            }
        }
        if (oddCount > 1) {
            return "";
        }

        // We only build the first half (frequency divided by 2)
        int[] freq = new int[26];
        for (int i = 0; i < 26; i++) {
            freq[i] = count[i] / 2;
        }

        char[] res = new char[n];

        // Helper method to fill the palindrome once prefix is set
        autoFill(res, freq, midChar, half);

        // Match longest common prefix of half with target
        int pos = 0;
        while (pos < half) {
            int tChar = target.charAt(pos) - 'a';
            if (freq[tChar] == 0) {
                break;
            }
            res[pos] = target.charAt(pos);
            freq[tChar]--;
            pos++;
        }

        // Case 1: Prefix matches half completely
        if (pos == half) {
            buildFullPalindrome(res, midChar, half);
            if (new String(res).compareTo(target) > 0) {
                return new String(res);
            }
        }

        // Backtrack to find the first character to increment
        while (pos >= 0) {
            if (pos < half) {
                int tChar = target.charAt(pos) - 'a';
                // Try to find smallest character > target[pos]
                for (int c = tChar + 1; c < 26; c++) {
                    if (freq[c] > 0) {
                        res[pos] = (char) ('a' + c);
                        freq[c]--;
                        
                        // Fill remaining positions in half with smallest available chars
                        int dst = pos + 1;
                        for (int i = 0; i < 26; i++) {
                            while (freq[i] > 0) {
                                res[dst++] = (char) ('a' + i);
                                freq[i]--;
                            }
                        }

                        buildFullPalindrome(res, midChar, half);
                        return new String(res);
                    }
                }
            }

            if (pos == 0) {
                return "";
            }

            // Un-match previous position
            pos--;
            freq[target.charAt(pos) - 'a']++;
        }

        return "";
    }

    private void autoFill(char[] res, int[] freq, int midChar, int half) {
        if (midChar != -1) {
            res[half] = (char) ('a' + midChar);
        }
    }

    private void buildFullPalindrome(char[] res, int midChar, int half) {
        if (midChar != -1) {
            res[half] = (char) ('a' + midChar);
        }
        int len = res.length;
        for (int i = 0; i < half; i++) {
            res[len - 1 - i] = res[i];
        }
    }
}