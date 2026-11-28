import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        long remBudget = budget;
        long totalItems = 0;

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

        int[] factorCount = new int[maxFactor + 1];
        for (int[] item : items) {
            factorCount[item[0]]++;
        }

        int[] gain = new int[n];
        for (int i = 0; i < n; i++) {
            int f = items[i][0];
            int multiples = 0;
            for (int m = f; m <= maxFactor; m += f) {
                multiples += factorCount[m];
            }
            gain[i] = Math.max(0, multiples - 1);
        }

        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }
        Arrays.sort(order, (a, b) -> Integer.compare(items[a][1], items[b][1]));

        long boostLimit = 2L * minPrice;
        for (int idx : order) {
            long price = items[idx][1];
            if (price >= boostLimit || price > remBudget) {
                break;
            }
            long boosted = gain[idx];
            if (boosted > 0) {
                long canBuy = Math.min(boosted, remBudget / price);
                totalItems += canBuy * 2;
                remBudget -= canBuy * price;
            }
        }

        if (remBudget >= minPrice) {
            totalItems += remBudget / minPrice;
        }

        return (int) totalItems;
    }
}