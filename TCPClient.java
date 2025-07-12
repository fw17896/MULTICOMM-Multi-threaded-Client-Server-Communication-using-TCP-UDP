import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 5000;

        try (
            Socket socket = new Socket(serverAddress, port);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            System.out.println("Connected to TCP Server at " + serverAddress + ":" + port);
            System.out.println("Type a message (type 'exit' to disconnect):");

            String message;
            while ((message = userInput.readLine()) != null) {
                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Disconnecting from server...");
                    break;
                }

                out.println(message);
                String response = in.readLine();
                System.out.println("Server Response: " + response);
            }
        } catch (IOException e) {
            System.err.println("Client Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}



// import java.io.*;
// import java.net.*;

// public class TCPClient {
//     public static void main(String[] args) {
//         String serverAddress = "localhost";
//         int port = 5000;

//         try (Socket socket = new Socket(serverAddress, port);
//              BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
//              BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//              PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

//             System.out.println("Connected to TCP Server.... ");
//             System.out.println("Type a message: ");

//             String message;
//             while ((message = userInput.readLine()) != null) {
//                 out.println(message); 
//                 System.out.println("Server Response: " + in.readLine());
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
// }
