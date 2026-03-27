import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverIp = InetAddress.getByName("localhost");
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        byte[] buf = new byte[1024];

        while (true) {
            // 1. Client user types a message to send to Server
            System.out.print("Enter message for Server: ");
            String msg = console.readLine();
            byte[] data = msg.getBytes();
            socket.send(new DatagramPacket(data, data.length, serverIp, 5555));

            // 2. Wait for Server's Acknowledgement and print it
            DatagramPacket ackPkt = new DatagramPacket(buf, buf.length);
            socket.receive(ackPkt);
            System.out.println("System: " + new String(ackPkt.getData(), 0, ackPkt.getLength()));

            // 3. Wait for Server's new message and print it
            DatagramPacket msgPkt = new DatagramPacket(buf, buf.length);
            socket.receive(msgPkt);
            System.out.println("From Server: " + new String(msgPkt.getData(), 0, msgPkt.getLength()));

            // 4. Send automatic Acknowledgement to Server
            byte[] ackMsg = "Client ACK: I received your message!".getBytes();
            socket.send(new DatagramPacket(ackMsg, ackMsg.length, serverIp, 5555));
            System.out.println("--------------------------------");
        }
    }
}