import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * 
 * @author HOME
 */
public class server extends UnicastRemoteObject implements adder, time, message {
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