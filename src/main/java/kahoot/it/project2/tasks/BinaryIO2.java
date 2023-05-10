package kahoot.it.project2.tasks;

import java.io.*;

public class BinaryIO2 {
    public static void main(String[] args) throws FileNotFoundException {
        File file =new File("exerciseIO.dat");

        try(DataOutputStream date=new DataOutputStream(new FileOutputStream(file,file.exists()));){
            for(int i=0;i<100;i++) {
                date.writeInt((int)(Math.random() * 100));
            }
        }catch (Exception ex1){

        }
    }

}
