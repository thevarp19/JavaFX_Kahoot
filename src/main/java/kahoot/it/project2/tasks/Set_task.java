package kahoot.it.project2.tasks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Set_task {
    public static void main(String[] args) {
        LinkedHashSet<String> set1 = new LinkedHashSet<>(Arrays.asList("George", "Jim", "John", "Blake", "Kevin", "Michael"));
        LinkedHashSet<String> set2 = new LinkedHashSet<>(Arrays.asList("George", "Katie", "Kevin", "Michelle", "Ryan"));
        // Union
        LinkedHashSet<String> unionSet = (LinkedHashSet<String>)set1.clone();
        unionSet.addAll(set2);
        System.out.println("Union: " + unionSet + "\n");

        // Difference
        HashSet<String> difference = (LinkedHashSet<String>) set1.clone();
        HashSet<String> dif2 = (LinkedHashSet<String>) set2.clone();
        difference.removeAll(set2);
        dif2.removeAll(set1);
        difference.addAll(dif2);
        System.out.println("Difference: " + difference + "\n");
        //Intersection
        LinkedHashSet<String> intersection = (LinkedHashSet<String>) set1.clone();
        intersection.retainAll(set2);
        System.out.println("Intersection: " + intersection + "\n");


    }
}
