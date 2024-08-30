import java.io.*;
import java.net.Socket;

public class Client1 {
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 900)) {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            // Specify the file to send
            File file = new File("NewFile.txt");
            FileInputStream fileInputStream = new FileInputStream(file);

            // Send file name and file size to the server
            dataOutputStream.writeUTF(file.getName());
            dataOutputStream.writeLong(file.length());

            // Send file content to the server
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                dataOutputStream.write(buffer, 0, bytesRead);
            }

            fileInputStream.close();
            System.out.println("File sent to the server.");
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