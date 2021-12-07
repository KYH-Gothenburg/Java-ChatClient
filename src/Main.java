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
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
            // We need a scanner so we can get some user input
            Scanner in = new Scanner(System.in);
            // We store the user input in inData
            String inData;
            do {
                // Get user input BLOCKING
                // TODO: Lägg i en egen tråd
                inData = in.next();
                // Send the suer input to the server
                output.println(inData);

                // We will only receive data from the server if we have sent something other than exit
                if(!inData.equals("exit")) {
                    // Get data from server BLOCKING
                    // TODO: Lägg i en egen tråd
                    String message = input.readLine();
                    // and print it
                    System.out.println(message);
                }

                // If we wrote exit, we will now exit the loop and exit the application
            } while(!inData.equals("exit"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
