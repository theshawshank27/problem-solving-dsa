package blind75;
import java.io.*;
import java.lang.*;
import java.util.*;
public class TwoSum {

        public static void main(String args[]) throws IOException {
            BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
            int t = Integer.parseInt(read.readLine());

            while (t-- > 0) {
                String line = read.readLine().trim();
                String[] numsStr = line.split(" ");
                int[] nums = new int[numsStr.length];
                for (int i = 0; i < numsStr.length; i++) {
                    nums[i] = Integer.parseInt(numsStr[i]);
                }

                int target = Integer.parseInt(read.readLine());

                Solution obj = new Solution();

                List<Integer> ans = obj.twoSum(nums, target);
                if (ans.size() == 0) {
                    System.out.println("[]");
                } else {
                    Collections.sort(ans);
                    for (int i = 0; i < ans.size(); i++) {
                        System.out.print(ans.get(i) + " ");
                    }
                    System.out.println();
                }
                System.out.println("~");
            }
        }
    }

class Solution {
    public List<Integer> twoSum(int arr[], int target) {
        Set set = new HashSet();
        int diff = 0;
        List pair = new ArrayList();
        for (int i =0; i<arr.length; i++){
            diff = target - arr[i];
            if(set.contains(diff)){
                pair.add(arr[i]);
                pair.add(diff);
                return pair;
            }
            set.add(arr[i]);
        }
        return pair;
    }
}