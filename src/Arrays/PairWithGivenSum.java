package Arrays;

import java.io.*;
import java.util.*;
public class PairWithGivenSum {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine().trim());
        while (tc-- > 0) {
            String[] inputLine;
            inputLine = br.readLine().trim().split(" ");
            // int n = Integer.parseInt(inputLine[0]);
            int x = Integer.parseInt(inputLine[0]);

            String line = br.readLine();
            String[] tokens = line.split(" ");
            int[] arr = new int[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                arr[i] = Integer.parseInt(tokens[i]);
            }

            boolean ans = new PairWithGivenSumSolution().twoSum(arr, x);
            System.out.println(ans ? "true" : "false");
            // System.out.println("~");

            System.out.println("~");
        }
    }
}
// } Driver Code Ends


// User function Template for Java

class PairWithGivenSumSolution {
    boolean twoSum(int arr[], int target) {
        HashSet<Integer> set = new HashSet();
        for(int i =0 ; i < arr.length; i++){
            if(set.contains(target - arr[i])){
                return true;
            }
            set.add(arr[i]);
        }
        return false;
    }

}