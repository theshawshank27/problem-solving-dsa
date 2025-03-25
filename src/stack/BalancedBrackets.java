package stack;

import java.util.Scanner;
public class BalancedBrackets {
//{ Driver Code Starts


        public static void main(String args[]) {
            Scanner sc = new Scanner(System.in);

            // Reading total number of testcases
            int t = sc.nextInt();

            while (t-- > 0) {
                // reading the string
                String st = sc.next();

                // calling ispar method of Paranthesis class
                // and printing "balanced" if it returns true
                // else printing "not balanced"
                if (isBalanced(st) == true)
                    System.out.println("true");
                else
                    System.out.println("false");

                System.out.println("~");
            }
        }

        static boolean isBalanced(String s) {
            java.util.Stack<Character> stack = new java.util.Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char current = s.charAt(i);
                if (current == '{' || current == '(' || current == '[') {
                    stack.push(current);
                } else if (current == '}' || current == ')' || current == ']') {
                    if (stack.isEmpty()) {
                        return false;
                    }
                    char last = stack.pop();
                    if (!isMatchingPair(last, current)) {
                        return false;
                    }
                }
            }
            return stack.isEmpty();
        }

        static boolean isMatchingPair(char open, char close) {
            return (open == '{' && close == '}') ||
                    (open == '(' && close == ')') ||
                    (open == '[' && close == ']');
        }
    }

