import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5555);
        System.out.println("Server started. Waiting for client...");

        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected: " + clientSocket.getInetAddress());

        // Thread for reading messages from client
        new Thread(() -> {
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()))) {
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Client: " + message);
                }
            } catch (IOException e) {
                System.out.println("Client disconnected.");
            }
        }).start();

        // Thread for sending messages to client
        try (BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            String userInput;
            while ((userInput = consoleIn.readLine()) != null) {
                out.println(userInput);
            }
        }
    }
}