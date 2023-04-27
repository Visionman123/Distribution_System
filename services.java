import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * @author HOME
 */
public interface services extends Remote {
    public long getTime() throws Remote Exception;
    public void putString(String message) throws RemoteException;
}