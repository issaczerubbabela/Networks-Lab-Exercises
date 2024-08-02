
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.*;

public class Client {
    private static DataOutputStream out = null;
    private static DataInputStream in = null;

    public static void main(String args[]) {
        try (Socket Client = new Socket("localhost", 6666)) {
            in = new DataInputStream(Client.getInputStream());
            out = new DataOutputStream(Client.getOutputStream());
            System.out.println("Sending file to server");
            sendFile("Z:\\Networks\\FileTransfer\\NewFile.txt");
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendFile(String path) throws Exception {
        int bytes = 0;
        File file = new File(path);
        FileInputStream filein = new FileInputStream(file);
        out.writeLong(file.length());

        byte[] buffer = new byte[4 * 1024];
        while ((bytes = filein.read(buffer)) != -1) {
            out.write(buffer, 0, bytes);
            out.flush();
        }
        filein.close();
    }

}
