class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLenTill = new int[n];
        Arrays.fill(minLenTill, Integer.MAX_VALUE);
        
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        
        int currentSum = 0;
        int minLen = Integer.MAX_VALUE;
        int result = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            currentSum += arr[i];
            
            // If a subarray ending at i with sum = target exists
            if (map.containsKey(currentSum - target)) {
                int startIndex = map.get(currentSum - target);
                int currLen = i - startIndex;
                
                // If there's a valid subarray before startIndex, combine them
                if (startIndex >= 0 && minLenTill[startIndex] != Integer.MAX_VALUE) {
                    result = Math.min(result, currLen + minLenTill[startIndex]);
                }
                
                minLen = Math.min(minLen, currLen);
            }
            
            // Maintain the minimum length seen up to index i
            minLenTill[i] = minLen;
            map.put(currentSum, i);
        }
        
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}