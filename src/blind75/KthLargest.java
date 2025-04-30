package blind75;

import java.util.PriorityQueue;

public class KthLargest {

        public static int findKthLargest(int[] nums, int k) {
            PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);

            for (int num : nums) {
                minHeap.offer(num);
                if (minHeap.size() > k) {
                    minHeap.poll();
                }
            }

            return minHeap.peek();
        }

        public static void main(String[] args) {
            int[] nums = {3, 2, 1, 5, 6, 4};
            int k = 3;
            System.out.println("The " + k + "-th largest element is " + findKthLargest(nums, k));
        }

}
