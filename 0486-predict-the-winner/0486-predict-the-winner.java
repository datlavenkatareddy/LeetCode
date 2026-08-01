class Solution {
    private Integer[][] memo;
    
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        memo = new Integer[n][n];

        return maxDiff(nums, 0, n -1) >= 0;
    }

    private int maxDiff(int[] nums, int left, int right){
    if (left == right){
        return nums[left];
    }

    if (memo[left][right] != null){
        return memo[left][right];
    }

    int pickLeft = nums[left] - maxDiff(nums, left + 1, right);

    int pickRight = nums[right] - maxDiff(nums, left, right - 1);

    memo[left][right] = Math.max(pickLeft, pickRight);
    return memo[left][right];
    }
}