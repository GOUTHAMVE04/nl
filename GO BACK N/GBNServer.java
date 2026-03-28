import java.io.*;
import java.net.*;

public class GBNServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5555);
        Socket s = ss.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);

        int total = Integer.parseInt(in.readLine());
        int expected = 0;

        while (expected < total) {
            int frame = Integer.parseInt(in.readLine());
            if (frame == expected) {
                System.out.println("Received Frame " + frame + " (In Order)");
                expected++;
            } else {
                System.out.println("Received Frame " + frame + " (Out of Order) -> Discarded");
            }
            out.println(expected - 1); // Send cumulative ACK
        }
        s.close(); ss.close();
    }
}