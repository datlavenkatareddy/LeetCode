class Solution {
    public int maxProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        return (nums[n-2]-1)*(nums[n-1]-1);
    }
}