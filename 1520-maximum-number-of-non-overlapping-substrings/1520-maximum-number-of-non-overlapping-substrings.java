import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        // Step 1: Track the first and last occurrence of each character
        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';
            if (first[ch] == -1) {
                first[ch] = i;
            }
            last[ch] = i;
        }

        List<String> result = new ArrayList<>();
        int prevEnd = -1;

        // Step 2 & 3: Find valid intervals and greedily pick non-overlapping ones
        for (int i = 0; i < n; i++) {
            // Only attempt expansion from the first occurrence of a character
            int ch = s.charAt(i) - 'a';
            if (i != first[ch]) continue;

            int rightBound = getValidRightBound(s, i, first, last);

            if (rightBound != -1) {
                // If this valid range ends before or at the end of the previous range,
                // replace the previous substring to keep the shorter/earlier one
                if (i > prevEnd) {
                    result.add(s.substring(i, rightBound + 1));
                    prevEnd = rightBound;
                } else {
                    result.set(result.size() - 1, s.substring(i, rightBound + 1));
                    prevEnd = rightBound;
                }
            }
        }

        return result;
    }

    private int getValidRightBound(String s, int left, int[] first, int[] last) {
        int right = last[s.charAt(left) - 'a'];

        for (int j = left; j <= right; j++) {
            int ch = s.charAt(j) - 'a';
            // If a character inside starts before left, invalid interval starting at left
            if (first[ch] < left) {
                return -1;
            }
            // Expand right bound if necessary
            right = Math.max(right, last[ch]);
        }

        return right;
    }
}