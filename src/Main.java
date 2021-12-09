import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            // Create a socket and connect to the server
            Socket clientSocket = new Socket("localhost", 9876);

            // Create input and output streams for communication with the server
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Start sender thread
            SenderThread senderThread = new SenderThread(clientSocket);
            senderThread.start();


            // We store the user input in inData
            String inData;
            do {
                // Get data from server BLOCKING
                String message = input.readLine();
                // and print it
                System.out.println(message);

                // If we wrote exit, in sender thread that thread will die, and we can exit
            } while(senderThread.isAlive());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
