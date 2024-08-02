import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Server
{
	public static void main(String args[])
	{
		try
		{
			ServerSocket ss = new ServerSocket(6666);
			Socket s = ss.accept();
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			Scanner sc = new Scanner(System.in);
			String str = "", str2 = "";
			while(!str2.equals("stop"))
			{
				System.out.println("sending..");
				str = dis.readUTF();
				System.out.println("the server says: " + str);
				str2 = sc.nextLine();
				dout.writeUTF(str2);
				dout.flush();
			}
			System.out.println("Communications have halted");
			dout.close(); 
			ss.close();
		}
		catch(Exception e)
		{System.out.println(e);}
	}
}