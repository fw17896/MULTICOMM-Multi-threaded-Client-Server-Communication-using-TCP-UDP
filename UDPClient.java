import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 5001; 
        byte[] buffer = new byte[1024];

        try (DatagramSocket socket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to UDP Server. Type a message:");

            while (true) {
                String message = scanner.nextLine();
                byte[] messageData = message.getBytes();
                DatagramPacket packet = new DatagramPacket(
                        messageData, messageData.length, InetAddress.getByName(serverAddress), port);
                socket.send(packet);

                DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(responsePacket);
                String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
                System.out.println("Server Response: " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}