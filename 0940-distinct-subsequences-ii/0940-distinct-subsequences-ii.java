class Solution {
    public int distinctSubseqII(String s) {

        final int MOD = 1000000007;

        // dp[i] = number of distinct subsequences
        // ending with character 'a' + i
        int[] dp = new int[26];

        for (char c : s.toCharArray()) {

            int index = c - 'a';

            int total = 0;

            // Count all existing distinct subsequences
            for (int j = 0; j < 26; j++) {
                total = (total + dp[j]) % MOD;
            }

            // Add current character
            dp[index] = (total + 1) % MOD;
        }

        // Add all subsequences ending with each character
        int answer = 0;

        for (int x : dp) {
            answer = (answer + x) % MOD;
        }

        return answer;
    }
}