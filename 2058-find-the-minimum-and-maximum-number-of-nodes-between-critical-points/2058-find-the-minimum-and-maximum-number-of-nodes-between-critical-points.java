class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int[] ans = {-1, -1};

        ListNode prev = head;
        ListNode curr = head.next;

        int index = 1;

        int first = -1;
        int last = -1;
        int minDistance = Integer.MAX_VALUE;

        while (curr != null && curr.next != null) {

            // Check if curr is a critical point
            if ((curr.val > prev.val && curr.val > curr.next.val) ||
                (curr.val < prev.val && curr.val < curr.next.val)) {

                // First critical point
                if (first == -1) {
                    first = index;
                }

                // Distance from previous critical point
                if (last != -1) {
                    minDistance = Math.min(minDistance, index - last);
                }

                last = index;
            }

            prev = curr;
            curr = curr.next;
            index++;
        }

        // Need at least two critical points
        if (first == -1 || first == last) {
            return ans;
        }

        int maxDistance = last - first;

        ans[0] = minDistance;
        ans[1] = maxDistance;

        return ans;
    }
}