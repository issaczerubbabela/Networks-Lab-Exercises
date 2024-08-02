
import java.io.*;
import java.net.*;

public class DailyAdviceServer {
    public static void main(String args[]) {
        try {
            ServerSocket serverSocket = new ServerSocket(4242);
            while (true) {
                Socket outgoing = serverSocket.accept();
                PrintWriter writer = new PrintWriter(outgoing.getOutputStream());
                writer.println("Hello there");
                writer.close();
            }
        } catch (Exception e) {
            System.out.println("Server Side Problem");
        }
    }
}

// // Server side
// import java.io.*;
// import java.net.*;

// public class MyServer {
//     public static void main(String[] args) {
//         try {
//             ServerSocket serverSocket = new ServerSocket(4242);
//             System.out.println("Server listening on port 4242...");

//             while (true) {
//                 Socket clientSocket = serverSocket.accept();
//                 System.out.println("Client connected: " + clientSocket.getInetAddress());

//                 // Get input stream from client
//                 InputStream inputStream = clientSocket.getInputStream();
//                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

//                 // Read user input
//                 String userInput = reader.readLine();
//                 System.out.println("Received from client: " + userInput);

//                 // Process the input (e.g., send a response back to the client)
//                 // ...

//                 reader.close();
//                 clientSocket.close();
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
// }
