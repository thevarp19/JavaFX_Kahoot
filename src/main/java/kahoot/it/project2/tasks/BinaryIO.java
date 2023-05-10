package kahoot.it.project2.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class BinaryIO {
    public static void main(String[] args) throws FileNotFoundException {
       File file =new File("exerciseIO.txt");

        try(PrintWriter printWriter=new PrintWriter(new FileOutputStream(file,file.exists()));){
            for(int i=0;i<100;i++) {

                printWriter.print((int)(Math.random() * 100)+"\n");
            }
        }catch (Exception ex1){

        }
        }

}
