import java.io.*;
import java.net.*;

public class SRClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5555);
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        int total = 5;
        out.println(total);

        // Send all frames initially
        for (int i = 0; i < total; i++) {
            System.out.println("Sending Frame " + i);
            out.println(i);
        }

        // Listen for specific NACKs or Success
        String msg;
        while ((msg = in.readLine()) != null) {
            if (msg.startsWith("NACK")) {
                int resend = Integer.parseInt(msg.split(" ")[1]);
                System.out.println("NACK received. Retransmitting ONLY frame " + resend);
                out.println(resend);
            } else if (msg.equals("END")) break;
        }
        s.close();
    }
}