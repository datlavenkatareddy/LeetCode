class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        
        // Precompute suffix minimums
        int[] minSuffix = new int[n];
        minSuffix[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minSuffix[i] = Math.min(minSuffix[i + 1], nums[i]);
        }
        
        // Iterate while maintaining prefix maximum
        int maxPrefix = nums[0];
        for (int i = 0; i < n; i++) {
            maxPrefix = Math.max(maxPrefix, nums[i]);
            
            // Instability score: max(nums[0..i]) - min(nums[i..n-1])
            int instabilityScore = maxPrefix - minSuffix[i];
            
            if (instabilityScore <= k) {
                return i;
            }
        }
        
        return -1;
    }
}