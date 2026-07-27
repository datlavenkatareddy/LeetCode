class Solution {
    public int maxOperations(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int operations = 0;

        for (int x : nums){
            int complement = k - x;

            if (map.getOrDefault(complement, 0) > 0){
                operations++;
                map.put(complement, map.get(complement) - 1);
            } else {
                map.put(x, map.getOrDefault(x, 0) + 1);
            }
        }

        return operations;
    }
}