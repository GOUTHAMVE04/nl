import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 5555);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            // 1. Client user types a message to send to Server
            System.out.print("Enter message for Server: ");
            String clientMsg = console.readLine();
            out.println(clientMsg);
            
            // 2. Wait for Server's Acknowledgement and print it
            System.out.println(in.readLine());
            
            // 3. Wait for Server's new message and print it
            String serverMsg = in.readLine();
            System.out.println("From Server: " + serverMsg);
            
            // 4. Send automatic Acknowledgement to Server
            out.println("Client ACK: I received your message!");
            System.out.println("--------------------------------");
        }
    }
}