class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < nums.length; right++){
            frequencyMap.put(nums[right], frequencyMap.getOrDefault(nums[right], 0) + 1);

            while (frequencyMap.get(nums[right]) > k){
                frequencyMap.put(nums[left], frequencyMap.get(nums[left]) - 1);
                left++;
            }

            maxLength = Math.max(maxLength, right - left +1);
        }

        return maxLength;
    }
}