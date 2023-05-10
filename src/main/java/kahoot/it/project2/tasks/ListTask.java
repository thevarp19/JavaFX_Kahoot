package kahoot.it.project2.tasks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.PriorityQueue;

public class ListTask {
    public static void main(String[] args) throws Exception {
        File file = new File("exerciseIO.txt");
        PriorityQueue<String> pQueue = new PriorityQueue<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String s;
            while ((s = in.readLine()) != null) {
                String[] sort =s.split(" ");
                pQueue.addAll(Arrays.asList(sort));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (!pQueue.isEmpty()) {
            System.out.println(pQueue.poll());
        }
    }
}
