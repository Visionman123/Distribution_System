import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Time;
import java.time.ZonedDateTime;
import java.util.TimeZone;

/**
 * 
 * @author HOME
 */
public class server extends UnicastRemoteObject implements adder, services {
    public server() throws RemoteException {
        super();
    }

    @Override
    public int add(int n1, int n2) throws RemoteException {
        return n1 + n2;
    }

    @Override
    public long getTime() throws RemoteException {
        return System.nanoTime();
    }

    @Override
    public void putString(String message) throws RemoteException {
        System.out.println(message);
    }

    @Override
    public ZonedDateTime getZonedDateTime(String zonedTime) throws RemoteException {
        ZonedDateTime zoneNow = ZonedDateTime.now(TimeZone.getTimeZone(zonedTime).toZoneId());
        return zoneNow;
    }
    
    public static void main(String args[]) throws RemoteException {
        try {
            Registry reg = LocateRegistry.createRegistry(4444);
            reg.rebind("Add service", new server());
            System.out.println("Server is ready...");

            
        } catch (RemoteException e) {
            System.out.println("Exception: " +e);
        }
    }
}