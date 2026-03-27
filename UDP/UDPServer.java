import java.io.*;
import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket server = new DatagramSocket(5555); // [cite: 684]
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in)); // [cite: 685]
        System.out.println("UDP Server waiting for packets...");

        while (true) {
            // 1. Wait for Client's message and print it
            byte[] recBuffer1 = new byte[1024];
            DatagramPacket receivePacket1 = new DatagramPacket(recBuffer1, recBuffer1.length); // [cite: 702]
            server.receive(receivePacket1); // [cite: 703]
            
            String clientMsg = new String(receivePacket1.getData(), 0, receivePacket1.getLength()); // [cite: 706]
            System.out.println("From Client: " + clientMsg);

            // CRITICAL: Save the Client's IP and Port from their packet so we can reply!
            InetAddress clientIP = receivePacket1.getAddress(); // [cite: 704]
            int clientPort = receivePacket1.getPort(); // [cite: 705]

            // 2. Send automatic Acknowledgement to Client
            String ackMsg = "Server ACK: I received your message!";
            byte[] ackData = ackMsg.getBytes();
            DatagramPacket ackPacket = new DatagramPacket(ackData, ackData.length, clientIP, clientPort);
            server.send(ackPacket); // [cite: 720]

            // 3. Server user types a message to send to Client
            System.out.print("Enter message for Client: ");
            String serverMsg = console.readLine(); // [cite: 716]
            byte[] sendData = serverMsg.getBytes(); // [cite: 717]
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIP, clientPort); // [cite: 718, 719]
            server.send(sendPacket);

            // 4. Wait for Client's Acknowledgement and print it
            byte[] recBuffer2 = new byte[1024];
            DatagramPacket receivePacket2 = new DatagramPacket(recBuffer2, recBuffer2.length);
            server.receive(receivePacket2);
            
            String clientAck = new String(receivePacket2.getData(), 0, receivePacket2.getLength());
            System.out.println(clientAck);
            System.out.println("--------------------------------");
        }
    }
}