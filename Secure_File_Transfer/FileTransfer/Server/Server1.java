import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(900)) {
            System.out.println("Server is listening on port 900");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                // Read file name and file size from the client
                String fileName = dataInputStream.readUTF();
                long fileSize = dataInputStream.readLong();

                // Create a new file in the server directory
                File file = new File(fileName);
                FileOutputStream fileOutputStream = new FileOutputStream(file);

                // Read file content from the client and write to the file
                byte[] buffer = new byte[4096];
                int bytesRead;
                long totalBytesRead = 0;
                while (totalBytesRead < fileSize && (bytesRead = dataInputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                    totalBytesRead += bytesRead;
                }

                fileOutputStream.close();
                System.out.println("File received from the client.");

                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dataInputStream != null)
                    dataInputStream.close();
                if (dataOutputStream != null)
                    dataOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}