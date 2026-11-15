class Solution {
    public int numOfSubarrays(int[] arr) {
        long oddCount = 0;
        long evenCount = 1; // Base case for prefix sum 0 (which is even)
        long currentPrefixSum = 0;
        long totalOddSubarrays = 0;
        int MOD = 1_000_000_007;

        for (int num : arr) {
            currentPrefixSum += num;

            if (currentPrefixSum % 2 == 1) {
                // If current prefix sum is odd, add the count of previous even prefix sums
                totalOddSubarrays = (totalOddSubarrays + evenCount) % MOD;
                oddCount++;
            } else {
                // If current prefix sum is even, add the count of previous odd prefix sums
                totalOddSubarrays = (totalOddSubarrays + oddCount) % MOD;
                evenCount++;
            }
        }

        return (int) totalOddSubarrays;
    }
}