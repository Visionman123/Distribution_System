import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 
 * @author HOME
 */
public class client {
    public static void main(String args[]) {
        client c = new client();
        c.connectRemote();
    }

    
    private void connectRemote() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4444);

            adder ad = (adder) registry.lookup("Add service");
            System.out.println("Addition is: " +ad.add(87, 92));

            time Time = (time) registry.lookup("Add service");
            System.out.println("Local time: " + System.nanoTime());
            System.out.println("Remote time: " + Time.getTime());

            service Massage = (message) registry.lookup("Add service");
            Message.putString("hehe");


        } catch (NotBoundException | RemoteException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}