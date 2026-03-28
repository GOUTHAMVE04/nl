import java.io.*;
import java.net.*;

public class GBNClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5555);
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        int totalFrames = 6, windowSize = 3, sent = 0;
        out.println(totalFrames);

        while (sent < totalFrames) {
            // Send a window of frames
            for (int i = 0; i < windowSize && (sent + i) < totalFrames; i++) {
                System.out.println("Sending Frame " + (sent + i));
                out.println(sent + i);
            }

            // Receive cumulative ACK (The last successfully received frame)
            int ack = Integer.parseInt(in.readLine());
            if (ack >= sent) {
                System.out.println("ACK received for frame " + ack);
                sent = ack + 1; // Slide window to next unacknowledged frame
            } else {
                System.out.println("Timeout/Error! Retransmitting window from " + sent);
            }
            System.out.println("--------------------------------");
        }
        s.close();
    }
}