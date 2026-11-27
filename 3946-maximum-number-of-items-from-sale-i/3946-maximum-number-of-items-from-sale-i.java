class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int maxFactor = 0;
        for (int[] item : items) {
            if (item[0] > maxFactor) {
                maxFactor = item[0];
            }
        }

        // freq[x]: number of items with factor == x
        int[] freq = new int[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

        // multCount[d]: number of items whose factor is a multiple of d (including d itself)
        int[] multCount = new int[maxFactor + 1];
        for (int d = 1; d <= maxFactor; d++) {
            for (int m = d; m <= maxFactor; m += d) {
                multCount[d] += freq[m];
            }
        }

        // dp[b]: max total item copies (bought + free) using at most budget b
        int[] dp = new int[budget + 1];

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            if (price > budget) {
                continue;
            }

            // 1) First copy of this item: gives 1 bought copy + (multCount[factor] - 1) free copies
            int firstCopyGain = multCount[factor];
            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + firstCopyGain);
            }

            // 2) Additional copies of this item: gives 1 bought copy per purchase, no extra freebies
            for (int b = price; b <= budget; b++) {
                dp[b] = Math.max(dp[b], dp[b - price] + 1);
            }
        }

        int ans = 0;
        for (int b = 0; b <= budget; b++) {
            ans = Math.max(ans, dp[b]);
        }
        return ans;
    }
}