import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StringServerIntf extends Remote {
    String reverseString(String input) throws RemoteException;
}
