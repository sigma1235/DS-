import java.rmi.Naming;
import java.util.Scanner;

public class StringClient {
    public static void main(String[] args) {
        try {
            // Connect to the remote server
            StringServerIntf server = (StringServerIntf) Naming.lookup("rmi://localhost/StringServer");

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter a string to reverse: ");
            String input = sc.nextLine();

            String result = server.reverseString(input);
            System.out.println("Reversed String: " + result);
        } catch (Exception e) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}
