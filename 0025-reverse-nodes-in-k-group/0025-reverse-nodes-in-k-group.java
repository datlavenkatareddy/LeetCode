/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prevGroupTail = dummy;

        while (true) {
            // 1. Check if there are at least k nodes remaining
            ListNode kThNode = getKthNode(prevGroupTail, k);
            if (kThNode == null) {
                break; // Fewer than k nodes remain, leave them as-is
            }

            ListNode nextGroupHead = kThNode.next;
            ListNode groupHead = prevGroupTail.next;

            // 2. Reverse current k-group
            ListNode prev = nextGroupHead;
            ListNode curr = groupHead;

            while (curr != nextGroupHead) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            // 3. Connect previous group's tail to current group's new head
            prevGroupTail.next = kThNode;
            prevGroupTail = groupHead;
        }

        return dummy.next;
    }

    private ListNode getKthNode(ListNode curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }
        return curr;
    }
}