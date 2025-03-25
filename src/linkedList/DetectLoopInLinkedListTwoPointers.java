package linkedList;

public class DetectLoopInLinkedListTwoPointers {
    // Java program to detect loop in a linked list
// using Floyd's Cycle-Finding Algorithm
        static boolean detectLoop(ListNode head) {

            // Fast and slow pointers initially points to the head
            ListNode slow = head, fast = head;

            // Loop that runs while fast and slow pointer are not
            // null and not equal
            while (slow != null && fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;

                // If fast and slow pointer points to the same node,
                // then the cycle is detected
                if (slow == fast) {
                    return true;
                }
            }
            return false;
        }

        public static void main(String[] args) {

            // Create a hard-coded linked list:
            // 1 -> 3 -> 4
            ListNode head = new ListNode(1);
            head.next = new ListNode(3);
            head.next.next = new ListNode(4);

            // Create a loop
            head.next.next.next = head.next;

            if (detectLoop(head))
                System.out.println("true");
            else
                System.out.println("false");
        }
    }

class ListNode {
    int data;
    ListNode next;

    public ListNode(int x) {
        this.data = x;
        this.next = null;
    }
}