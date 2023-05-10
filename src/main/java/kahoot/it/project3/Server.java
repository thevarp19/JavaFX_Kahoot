package kahoot.it.project3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Server {
    private static int generatePin(){
        int n = 1000000 + new Random().nextInt(9999999);
        return n;
    }

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket=new ServerSocket(7006);
        System.out.println("Waiting for connection!");
        int clientCount=1;
        int pin=generatePin();
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println(clientCount+ " Client is connected!");
            new Thread(() -> {
                try {
                    DataInputStream fromClient = new DataInputStream(socket.getInputStream());
                    DataOutputStream toClient=new DataOutputStream(socket.getOutputStream());
                while (true) {
                    try {
                        System.out.println(pin);
                        int clientPin=fromClient.readInt();
                        System.out.println(clientPin);
                        if(clientPin!=pin){
                            toClient.writeUTF("Wrong PIN!");
                        }else{
                            toClient.writeUTF("Success!");
                        }
                        System.out.println(fromClient.readUTF());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            clientCount++;

        }
    }
}
