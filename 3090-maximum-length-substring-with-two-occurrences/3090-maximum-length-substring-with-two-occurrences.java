class Solution {
    public int maximumLengthSubstring(String s) {
        int[] count = new int[26];
        int left = 0;
        int maxLength = 0;
        int n = s.length();

        for (int right = 0; right < n; right++){
            char c = s.charAt(right);
            count[c - 'a']++;

            while (count[c - 'a'] > 2){
                count[s.charAt(left) - 'a']--;
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}