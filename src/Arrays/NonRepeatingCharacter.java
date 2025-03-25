package Arrays;

import java.lang.*;
import java.util.*;
public class NonRepeatingCharacter {
        public static void main(String args[]) {
            Scanner sc = new Scanner(System.in);
            int t = sc.nextInt();

            while (t-- > 0) {
                String st = sc.next();

                char ans = new NonRepeatingCharacterSolution().nonRepeatingChar(st);

                if (ans != '$')
                    System.out.println(ans);
                else
                    System.out.println(-1);

                System.out.println("~");
            }
        }
}

class NonRepeatingCharacterSolution {
    static char nonRepeatingChar(String s) {
        HashMap<Character,Integer> map = new LinkedHashMap();
        char[] arr = s.toCharArray();
        for(char ch : arr){
            if(!map.containsKey(ch)){
                map.put(ch,1);
            }else{
                map.put(ch,map.get(ch) + 1);
            }
        }

        for(Map.Entry<Character,Integer> entry : map.entrySet()){
            if (entry.getValue() == 1)
                return entry.getKey();

        }
        return '$';
    }
}

