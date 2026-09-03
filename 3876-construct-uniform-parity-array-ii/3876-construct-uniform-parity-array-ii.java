class Solution {
    public boolean uniformArray(int[] nums1) {

        // Find the smallest odd number
        int minOdd = Integer.MAX_VALUE;

        for (int num : nums1) {
            if (num % 2 == 1) {
                minOdd = Math.min(minOdd, num);
            }
        }

        // If there is no odd number, all are even
        if (minOdd == Integer.MAX_VALUE) {
            return true;
        }

        // Every even number must be greater than
        // the smallest odd number
        for (int num : nums1) {
            if (num % 2 == 0 && num < minOdd) {
                return false;
            }
        }

        return true;
    }
}