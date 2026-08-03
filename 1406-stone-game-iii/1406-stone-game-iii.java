class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;

        int[] dp = new int[4];

        for (int i = n - 1; i >= 0; i--){
            int takeSum = 0;
            int maxDiff = Integer.MIN_VALUE;

            for (int k = 1; k <= 3 && i + k -1 < n; k++){
                takeSum += stoneValue[i + k -1];
                maxDiff = Math.max(maxDiff, takeSum - dp[(i + k) % 4]);
            }
            dp[i % 4] = maxDiff;
        }

        int result = dp[0];
        if (result > 0){
            return "Alice";
        } else if (result < 0){
            return "Bob";
        }else {
            return "Tie";
        }
    }
}
