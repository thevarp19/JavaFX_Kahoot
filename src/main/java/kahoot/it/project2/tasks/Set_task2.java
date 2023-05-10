package kahoot.it.project2.tasks;

import java.util.*;

public class Set_task2 {
    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);
        Map<Integer,Integer> map=new HashMap<>();
        int num;
        while((num=in.nextInt())!=0){
            if(!map.containsKey(num)){
                map.put(num,1);
            }else {
                int a=map.get(num);
                a++;
                map.put(num,a);
            }
        }

        int max = Collections.max(map.values());

        System.out.print("The most occurrences integers are: ");
        for (Map.Entry<Integer, Integer> maps : map.entrySet()) {
            if (maps.getValue() == max) {
                System.out.print(maps.getKey() + " ");
            }
        }
    }
}
