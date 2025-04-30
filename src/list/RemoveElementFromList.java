package list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class RemoveElementFromList {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>(Arrays.asList(1,2,45,34,24));
//        integerList = integerList.stream().filter(c -> c % 2 ==0).collect(Collectors.toList());
//        integerList.removeIf(c -> c %2 == 0);
        for (Iterator<Integer> it = integerList.iterator(); it.hasNext(); ) {
            int num = it.next();
            if(num % 2 == 0){
                it.remove();
            }
        }
        System.out.println(integerList);
    }

}
