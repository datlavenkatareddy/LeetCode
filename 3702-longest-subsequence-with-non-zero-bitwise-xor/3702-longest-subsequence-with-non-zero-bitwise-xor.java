class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int xorAll = 0;
        boolean hasNonZero = false;

        for (int num : nums){
            xorAll ^= num;
            if (num != 0){
                hasNonZero = true;
            }
        }

        if (xorAll != 0){
            return n;
        }

        if(!hasNonZero){
            return 0;
        }

        return n -1;
    }
}