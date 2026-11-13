class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;

        long ans = 0;
        long cost = 0;

        Deque<long[]> dq = new ArrayDeque<>();

        int j = n - 1;

        for (int i = n - 1; i >= 0; i--) {
            int num = nums[i];
            long count = 1;

            // Merge smaller values into the current value
            while (!dq.isEmpty() && dq.peekLast()[0] < num) {
                long[] last = dq.pollLast();

                count += last[1];
                cost += (long) (num - last[0]) * last[1];
            }

            dq.offerLast(new long[]{num, count});

            // Shrink the window until the cost is valid
            while (cost > k) {
                long[] first = dq.pollFirst();

                long value = first[0];
                long cnt = first[1];

                cost -= value - nums[j];
                j--;

                if (cnt > 1) {
                    dq.offerFirst(new long[]{value, cnt - 1});
                }
            }

            // Count valid subarrays ending at j
            ans += j - i + 1;
        }

        return ans;
    }
}