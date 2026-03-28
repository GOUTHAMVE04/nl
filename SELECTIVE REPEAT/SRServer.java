import java.io.*;
import java.net.*;

public class SRServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5555);
        Socket s = ss.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);

        int total = Integer.parseInt(in.readLine());
        boolean[] received = new boolean[total];
        int count = 0;

        // Simulate Frame 1 being lost
        for (int i = 0; i < total; i++) {
            int frame = Integer.parseInt(in.readLine());
            if (frame == 1 && !received[1]) {
                System.out.println("Frame 1 lost/corrupted. Requesting NACK.");
                out.println("NACK 1");
            } else {
                System.out.println("Received and Buffered Frame " + frame);
                if(!received[frame]) { received[frame] = true; count++; }
            }
        }

        // Receive the specific retransmitted frame
        int resend = Integer.parseInt(in.readLine());
        System.out.println("Received retransmitted Frame " + resend);
        out.println("END");

        System.out.println("All frames received successfully.");
        s.close(); ss.close();
    }
}