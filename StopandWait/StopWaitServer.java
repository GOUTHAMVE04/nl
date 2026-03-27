import java.io.*;
import java.net.*;

public class StopWaitServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(9999);
        System.out.println("Receiver waiting...");
        Socket s = ss.accept();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);

        int n = Integer.parseInt(in.readLine());

        for (int i = 0; i < n; i++) {
            String frame = in.readLine();
            System.out.println("Frame " + frame + " received.");
            Thread.sleep(1000); // Simulate processing
            
            out.println("ACK");
            System.out.println("ACK sent for frame " + frame + "\n");
        }
        System.out.println("All frames received.");
        s.close(); ss.close();
    }
}