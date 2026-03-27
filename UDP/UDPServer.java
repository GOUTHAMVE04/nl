import java.io.*;
import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(5555);
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        byte[] buf = new byte[1024];
        System.out.println("Server waiting for packets...");

        while (true) {
            // 1. Wait for Client's message and print it
            DatagramPacket clientPkt = new DatagramPacket(buf, buf.length);
            socket.receive(clientPkt);
            String clientMsg = new String(clientPkt.getData(), 0, clientPkt.getLength());
            System.out.println("From Client: " + clientMsg);

            // 2. Send automatic Acknowledgement to Client
            InetAddress clientIp = clientPkt.getAddress();
            int clientPort = clientPkt.getPort();
            byte[] ackData = "Server ACK: I received your message!".getBytes();
            socket.send(new DatagramPacket(ackData, ackData.length, clientIp, clientPort));

            // 3. Server user types a message to send to Client
            System.out.print("Enter message for Client: ");
            String serverMsg = console.readLine();
            byte[] serverData = serverMsg.getBytes();
            socket.send(new DatagramPacket(serverData, serverData.length, clientIp, clientPort));

            // 4. Wait for Client's Acknowledgement and print it
            DatagramPacket clientAck = new DatagramPacket(buf, buf.length);
            socket.receive(clientAck);
            System.out.println("System: " + new String(clientAck.getData(), 0, clientAck.getLength()));
            System.out.println("--------------------------------");
        }
    }
}