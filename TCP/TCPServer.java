import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(5555);
        System.out.println("Waiting for client...");
        Socket client = server.accept();
        System.out.println("Client connected!");
        
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            // 1. Wait for Client's message and print it
            String clientMsg = in.readLine();
            System.out.println("From Client: " + clientMsg);
            
            // 2. Send automatic Acknowledgement to Client
            out.println("Server ACK: I received your message!");
            
            // 3. Server user types a message to send to Client
            System.out.print("Enter message for Client: ");
            String serverMsg = console.readLine();
            out.println(serverMsg);
            
            // 4. Wait for Client's Acknowledgement and print it
            System.out.println(in.readLine());
            System.out.println("--------------------------------");
        }
    }
}