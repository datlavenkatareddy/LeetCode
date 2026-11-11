class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        // We can cover both requirements simultaneously using type 3 items.
        // A type 3 item costs `costBoth` and contributes 1 to both need1 and need2.
        // However, if buying separately using type 1 and type 2 is cheaper, we can cap costBoth.
        costBoth = Math.min(costBoth, cost1 + cost2);
        
        long totalCost = 0;
        
        // Match the requirements that can be fulfilled together using type 3 items
        long bothCount = Math.min(need1, need2);
        totalCost += bothCount * costBoth;
        
        need1 -= bothCount;
        need2 -= bothCount;
        
        // For any remaining requirement for type 1, use the cheaper option between type 1 and type 3
        if (need1 > 0) {
            totalCost += (long) need1 * Math.min(cost1, costBoth);
        }
        
        // For any remaining requirement for type 2, use the cheaper option between type 2 and type 3
        if (need2 > 0) {
            totalCost += (long) need2 * Math.min(cost2, costBoth);
        }
        
        return totalCost;
    }
}