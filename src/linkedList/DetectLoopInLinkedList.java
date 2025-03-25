package linkedList;
import java.io.*;
import java.lang.*;
import java.util.*;

public class DetectLoopInLinkedList {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int t = Integer.parseInt(br.readLine().trim());

            while (t-- > 0) {
                List<Integer> arr = new ArrayList<>();
                String input = br.readLine().trim();
                if (!input.isEmpty()) {
                    String[] numbers = input.split("\\s+");
                    for (String num : numbers) {
                        if (!num.isEmpty()) {
                            arr.add(Integer.parseInt(num));
                        }
                    }
                }

                int pos = Integer.parseInt(br.readLine().trim());

                ListNode head = null;
                if (!arr.isEmpty()) {
                    head = new ListNode(arr.get(0));
                    ListNode tail = head;
                    for (int i = 1; i < arr.size(); ++i) {
                        tail.next = new ListNode(arr.get(i));
                        tail = tail.next;
                    }
                    makeLoop(head, tail, pos);
                }

                DetectLoopInLinkedListSolution x = new DetectLoopInLinkedListSolution();
                if (x.detectLoop(head))
                    System.out.println("true");
                else
                    System.out.println("false");

                System.out.println("~");
            }
        }

    public static void makeLoop(ListNode head, ListNode tail, int x) {
        if (x == 0) return;

        ListNode curr = head;
        for (int i = 1; i < x; i++) curr = curr.next;

        tail.next = curr;
    }
}
class DetectLoopInLinkedListSolution {
    // Function to check if the linked list has a loop.
    public static boolean detectLoop(ListNode head) {
        ListNode node = head;
        HashSet set = new HashSet();
        while(node.next != null){
            if(set.contains(node)){
                return true;
            }
            set.add(node);
            node = node.next;
        }
        return false;
    }
}

class Node {
    int data;
    ListNode next;

    Node(int x) {
        data = x;
        next = null;
    }
}
