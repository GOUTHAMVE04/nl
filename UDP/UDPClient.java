import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket client = new DatagramSocket(); // [cite: 755]
        InetAddress serverIP = InetAddress.getByName("localhost"); // [cite: 756]
        int serverPort = 5555;
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in)); // [cite: 757]

        while (true) {
            // 1. Client user types a message to send to Server
            System.out.print("Enter message for Server: ");
            String clientMsg = console.readLine(); // [cite: 786]
            byte[] sendData = clientMsg.getBytes(); // [cite: 787]
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIP, serverPort); // [cite: 788]
            client.send(sendPacket); // [cite: 789]

            // 2. Wait for Server's Acknowledgement and print it
            byte[] recBuffer1 = new byte[1024];
            DatagramPacket receivePacket1 = new DatagramPacket(recBuffer1, recBuffer1.length); // [cite: 772]
            client.receive(receivePacket1); // [cite: 773]
            
            String serverAck = new String(receivePacket1.getData(), 0, receivePacket1.getLength()); // [cite: 774]
            System.out.println(serverAck);

            // 3. Wait for Server's new message and print it
            byte[] recBuffer2 = new byte[1024];
            DatagramPacket receivePacket2 = new DatagramPacket(recBuffer2, recBuffer2.length);
            client.receive(receivePacket2);
            
            String serverMsg = new String(receivePacket2.getData(), 0, receivePacket2.getLength());
            System.out.println("From Server: " + serverMsg);

            // 4. Send automatic Acknowledgement to Server
            String ackMsg = "Client ACK: I received your message!";
            byte[] ackData = ackMsg.getBytes();
            DatagramPacket ackPacket = new DatagramPacket(ackData, ackData.length, serverIP, serverPort);
            client.send(ackPacket);
            System.out.println("--------------------------------");
        }
    }
}