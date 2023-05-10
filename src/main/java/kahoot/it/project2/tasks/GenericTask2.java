package kahoot.it.project2.tasks;

import java.util.ArrayList;

public class GenericTask2 {
    public static void main(String[] args) {
        ArrayList<Integer>list=new ArrayList<>();
        for(int i=0;i< 100;i++){
            list.add((int)(Math.random()*100));
        }
        System.out.println(max(list));
    }
    public static <E extends Comparable<E>> E max(ArrayList<E> list){
        E temp=list.get(0);
        for (E e : list) {
            if (e.compareTo(temp) > 0) {
                temp = e;
            }
        }
        return temp;
    }
}
