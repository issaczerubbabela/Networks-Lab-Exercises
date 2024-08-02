import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Client
{
	public static void main(String args[])
	{
		try
		{
			Socket s = new Socket("localhost", 6666);
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			Scanner sc = new Scanner(System.in);
			String str="", str2="";
			System.out.println("enter the first message: ");
			while(!str.equals("stop"))
			{
				str = sc.nextLine();
				dout.writeUTF(str);
				dout.flush();
				str2 = dis.readUTF();
				System.out.println("the server says: "+ str2);
			}
			dout.close();
			s.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}