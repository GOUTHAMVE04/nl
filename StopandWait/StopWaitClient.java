import java.io.*;
import java.net.*;

public class StopWaitClient {
    public static void main(String[] args) throws Exception {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter number of frames: ");
        int n = Integer.parseInt(console.readLine());

        Socket s = new Socket("localhost", 9999);
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        out.println(n); // Send total frame count

        for (int i = 0; i < n; i++) {
            System.out.println("Sending Frame: " + i);
            out.println(i);
            
            String ack = in.readLine(); // Wait for ACK
            System.out.println("Server Response: " + ack + " for frame " + i + "\n");
        }
        System.out.println("All frames sent successfully.");
        s.close();
    }
}