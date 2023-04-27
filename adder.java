import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * @author HOME
 */
public interface adder extends Remote {
    public int add(int n1, int n2) throws RemoteException;
}