package Arrays;
//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

public class Anagram {

        public static void main(String[] args) throws IOException {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("No of parameters");
            int t = Integer.parseInt(br.readLine());
            while (t-- > 0) {
                System.out.println("First parameter");
                String s1 = br.readLine(); // first string
                System.out.println("Second parameter");
                String s2 = br.readLine(); // second string

                AnagramSolution obj = new AnagramSolution();

                if (obj.areAnagrams(s1, s2)) {
                    System.out.println("true");
                } else {
                    System.out.println("false");
                }
                System.out.println("~");
            }
        }
    }
// } Driver Code Ends


    class AnagramSolution {
        // Function is to check whether two strings are anagram of each other or not.
        public static boolean areAnagrams(String s1, String s2) {
            char[] as1 = s1.toCharArray();
            char[] as2 = s2.toCharArray();
            Arrays.sort(as1);
            Arrays.sort(as2);
            if(Arrays.equals(as1,as2))
                return true;

            return false;
        }
    }
