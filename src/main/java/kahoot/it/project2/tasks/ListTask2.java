package kahoot.it.project2.tasks;


import java.util.Iterator;
import java.util.LinkedList;

public class ListTask2 {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 5000000; i++) {
            list.add(i);
        }
        long before=System.currentTimeMillis();
        Iterator<Integer> iterator = list.iterator();
        System.out.println("Start iterator: ");
        while (iterator.hasNext()) {
            iterator.next();
        }

        long after=System.currentTimeMillis();
        System.out.println("Iterator: "+(after-before));


        long before2=System.currentTimeMillis();
        for(int i=0;i < 50000;i++){
            list.get(i);
        }
        long after2=System.currentTimeMillis();
        System.out.println("get(index) method: "+(after2-before2));

    }
}
