import java.rmi.*;
import java.util.Scanner;

public class MyClient {
    public static void main(String args[]) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter first number: ");
            int num1 = scanner.nextInt();

            System.out.print("Enter second number: ");
            int num2 = scanner.nextInt();

            Adder stub = (Adder) Naming.lookup("rmi//localhost:5000/admin");
            System.out.println("Result: " + stub.add(num1, num2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}