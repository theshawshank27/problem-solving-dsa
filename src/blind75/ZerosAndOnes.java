package blind75;

import java.util.Arrays;

public class ZerosAndOnes {
    public static void main(String[] args) {
        // Create an integer array containing 0's and 1's
// Optimized approach - Dutch National Flag algorithm (one-pass)
        int[] arr = new int[]{0, 1, 1, 0, 1, 1, 0};

        // Using two pointers approach
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            // If left element is 0, we need to move it to right
            if (arr[left] == 0) {
                arr[left] = arr[right];
                arr[right] = 0;
                right--;
            } else {
                // If left element is already 1, just move forward
                left++;
            }
        }

// Print the result
        System.out.println(Arrays.toString(arr));
    }

}
