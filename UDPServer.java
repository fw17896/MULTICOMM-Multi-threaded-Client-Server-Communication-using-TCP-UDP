import java.net.*;

public class UDPServer {
    private static final int PORT = 5001;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            System.out.println("Multi-Threaded UDP Server is running on port " + PORT);
            System.out.println("Waiting for Clients.....");

            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                
                socket.receive(packet);
                
                new Thread(new UDPClientHandler(socket, packet)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class UDPClientHandler implements Runnable {
    private DatagramSocket socket;
    private DatagramPacket packet;

    public UDPClientHandler(DatagramSocket socket, DatagramPacket packet) {
        this.socket = socket;
        this.packet = packet;
    }

    @Override
    public void run() {
        try {
            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Received: " + received);

            String response = "Server Response: " + received.toUpperCase();
            byte[] responseData = response.getBytes();

            DatagramPacket responsePacket = new DatagramPacket(
                    responseData, responseData.length, packet.getAddress(), packet.getPort());
            socket.send(responsePacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
