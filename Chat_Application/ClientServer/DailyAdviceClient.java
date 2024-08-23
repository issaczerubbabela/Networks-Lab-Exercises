// Client side
import java.io.*;
import java.net.*;

public class DailyAdviceClient {
    public static void main(String args[]) {
        try {
            Socket incoming = new Socket("192.168.72.94", 4242);
            InputStreamReader stream = new InputStreamReader(incoming.getInputStream());
            BufferedReader reader = new BufferedReader(stream);
            String advice = reader.readLine();
            reader.close();
            System.out.println("Today's advice is: " + advice);
        } catch (Exception e) {
            System.out.println("Client Side Error");
        }
    }
}