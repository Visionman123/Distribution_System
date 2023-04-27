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

            services Services = (services) registry.lookup("Add service");
            System.out.println("Local time: " + System.nanoTime());
            System.out.println("Remote time: " + Services.getTime());

            Services.putString("Local time: " + System.nanoTime());
            Services.putString("Remote time: " + Services.getTime());

            System.out.println("Current time of Asia/HoChiMinh: " + Services.getZonedDateTime("Asia/HoChiMinh"));
            System.out.println("Current time of Europe/Munich: " + Services.getZonedDateTime("Europe/Munich"));


        } catch (NotBoundException | RemoteException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}