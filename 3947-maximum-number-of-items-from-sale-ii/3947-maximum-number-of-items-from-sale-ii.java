import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        if (n == 0 || budget <= 0) {
            return 0;
        }

        int maxFactor = 0;
        long minPrice = Long.MAX_VALUE;
        for (int[] item : items) {
            if (item[0] > maxFactor) {
                maxFactor = item[0];
            }
            if (item[1] < minPrice) {
                minPrice = item[1];
            }
        }

        // 1. Frequency of each factor value
        int[] freq = new int[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

        // 2. Sieve-style harmonic pass: multiplesOf[d] = count of items with factor a multiple of d
        int[] multiplesOf = new int[maxFactor + 1];
        for (int d = 1; d <= maxFactor; d++) {
            int count = 0;
            for (int m = d; m <= maxFactor; m += d) {
                count += freq[m];
            }
            multiplesOf[d] = count;
        }

        // gain[i] = number of other items j (j != i) where factor[i] divides factor[j]
        int[] gain = new int[n];
        for (int i = 0; i < n; i++) {
            gain[i] = Math.max(0, multiplesOf[items[i][0]] - 1);
        }

        // 3. Sort item indices by price ascending
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }
        Arrays.sort(order, (a, b) -> Integer.compare(items[a][1], items[b][1]));

        long remBudget = budget;
        long totalItems = 0;
        long boostLimit = 2L * minPrice;

        // 4. Buy boosted (+2 items) batches only when strictly cheaper per item than minPrice
        for (int idx : order) {
            long price = items[idx][1];
            if (price >= boostLimit || price > remBudget) {
                break;
            }
            int g = gain[idx];
            if (g > 0) {
                long canBuy = Math.min((long) g, remBudget / price);
                totalItems += canBuy * 2;
                remBudget -= canBuy * price;
            }
        }

        // 5. Fill remaining budget with single (+1 item) copies of the cheapest item
        if (remBudget >= minPrice) {
            totalItems += remBudget / minPrice;
        }

        return (int) totalItems;
    }
}