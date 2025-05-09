import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class StringServerImpl extends UnicastRemoteObject implements StringServerIntf {
    public StringServerImpl() throws RemoteException {
        super();
    }

    public String reverseString(String input) throws RemoteException {
        System.out.println("Client requested string reversal.");
        return new StringBuilder(input).reverse().toString();
    }
}
