package kahoot.it.project2.tasks;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket {
    public static void main(String[] args) throws Exception {
        Socket socket=new Socket("127.0.0.1",9955);
        PrintWriter pr=new PrintWriter(socket.getOutputStream());
        Scanner in=new Scanner(socket.getInputStream());

        pr.println("Good");
        pr.print("Day!");
        pr.println("Ne boley!");
        pr.close();
        socket.close();
    }
}
