package Hands_on_lab_1;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.ZonedDateTime;

/**
 * 
 * @author HOME
 */
public interface services extends Remote {
    public long getTime() throws RemoteException;
    public void putString(String message) throws RemoteException;
    public ZonedDateTime getZonedDateTime(String zonedTime) throws RemoteException;
}