import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * @author HOME
 */
public interface time extends Remote {
    public long getTime() throws RemoteException;
}

