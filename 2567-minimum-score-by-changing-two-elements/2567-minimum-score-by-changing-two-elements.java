class Solution {
    public int minimizeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        
        int dropTwoLargest = nums[n - 3] - nums[0];
        int dropTwoSmallest = nums[n - 1] - nums[2];
        int dropBothEnds = nums[n - 2] - nums[1];

        return Math.min(dropTwoLargest, Math.min(dropTwoSmallest, dropBothEnds));
    }
}