package kahoot.it.project2.tasks;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket=new ServerSocket(9955);
        Socket input=serverSocket.accept();
        PrintWriter out=new PrintWriter(input.getOutputStream());
        Scanner sc=new Scanner(input.getInputStream());
        Scanner pr=new Scanner(System.in);
        while (sc.hasNext()){
            System.out.println(sc.nextLine());
            out.println(pr.nextLine());
            out.flush();
        }
        sc.close();
        input.close();
        serverSocket.close();

    }
}
