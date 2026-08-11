class Solution {
    public int missingInteger(int[] nums) {
        int n = nums.length;
        int sum = nums[0];

        for( int j = 1; j <= n-1; j++){
            if(nums[j] == nums[j - 1] + 1){
                sum += nums[j];
            } else {
                break;
            }
        }

        HashSet<Integer> set = new HashSet<>();
        for (int num : nums){
            set.add(num);
        }

        int x = sum;
        while (set.contains(x)){
            x++;
        }
        return x;
    }
    
}