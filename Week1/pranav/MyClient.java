import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
public class MyClient
{
	public static void main(String args[])
	{
		JFrame f= new JFrame("TextField Example");  
    		JTextField t1;
    		t1=new JTextField("Welcome to Javatpoint.");  
    		t1.setBounds(50,100, 200,30);  
		t1.addFocusListener(new FocusListener() {
		public void focusGained(FocusEvent e) {
   			 t1.setText(""); 
		}
		public void focusLost(FocusEvent e) {
		}
		}); 
		JButton b1 = new JButton("send");
		b1.setBounds(250, 100, 100, 40);   
		f.add(t1);
		f.add(b1);
		f.setSize(400, 400);
		f.setLayout(null);
		f.setVisible(true);
		try
		{
			Socket s = new Socket("localhost", 6666);
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			Scanner sc = new Scanner(System.in);
			String str="", str2="";
			System.out.println("enter the first message: ");
			b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){ 
					try{ 
            			  	String st = t1.getText();
					dout.writeUTF(st);
					dout.flush();}	
					catch(Exception ex){}
				}		
			});
			while(!str.equals("stop"))
			{
				str = t1.getText();
				str2 = dis.readUTF();
				System.out.println("the server says: "+ str2);
			}
			dout.close();
			s.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
}