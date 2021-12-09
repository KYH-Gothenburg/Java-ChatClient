import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SenderThread extends Thread{
    Socket clientSocket;

    public SenderThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        // We need a scanner so we can get some user input
        PrintWriter output = null;
        try {
            output = new PrintWriter(clientSocket.getOutputStream(), true);
            Scanner in = new Scanner(System.in);
            String inData;
            do {
                // Get user input BLOCKING
                 inData = in.nextLine();
                System.out.println("Sending message: " + inData);
                // Send the suer input to the server
                output.println(inData);
                System.out.println("Message Sent");
            } while(!inData.equals("exit"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
