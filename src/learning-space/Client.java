import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5555);
        System.out.println("Connected to server.");

        // Thread for reading messages from server
        new Thread(() -> {
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()))) {
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Server: " + message);
                }
            } catch (IOException e) {
                System.out.println("Server disconnected.");
            }
        }).start();

        // Thread for sending messages to server
        try (BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            String userInput;
            while ((userInput = consoleIn.readLine()) != null) {
                out.println(userInput);
            }
        }
    }
}