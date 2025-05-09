import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class StringServer {
    public static void main(String[] args) {
        try {
            // Start RMI Registry programmatically
            LocateRegistry.createRegistry(2000);

            // Bind the remote object to the RMI registry
            StringServerImpl server = new StringServerImpl();
            Naming.rebind("rmi://localhost/StringServer", server);

            System.out.println("String Server is ready.");
        } catch (Exception e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}
