import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
    private static DataOutputStream out = null;
    private static DataInputStream in = null;

    public static void main(String args[]) {
        try (ServerSocket serverSocket = new ServerSocket(6666)) {
            System.out.println("Server is Starting in Port 6666");
            Socket clientSocket = serverSocket.accept();
            System.out.println("connected");
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            receiveFile("NewFile.pdf");

            in.close();
            out.close();
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void receiveFile(String fileName) throws Exception {
        int bytes = 0;
        FileOutputStream fileout = new FileOutputStream(fileName);
        long size = in.readLong();
        byte[] buffer = new byte[4 * 1024];
        while (size > 0 && (bytes = in.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
            out.write(buffer, 0, bytes);
            size -= bytes;
        }
        System.out.println("File is Received");
        out.close();
    }

}
