package Hands_on_lab_1;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.*;

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
            Scanner sc = new Scanner(System.in);

            Registry reg = LocateRegistry.getRegistry("localhost", 4444);
            services service = (services) reg.lookup("Add service");
            System.out.println("Addition is: " + service.add(87, 92));

            long local = System.nanoTime();
            long host = service.getTime();
            System.out.println("Local time: " + local);
            System.out.println("Host time: " + host);

            service.putString("Local time: " + local);
            service.putString("Host time: " + host);

            System.out.println("Current time of Asia/Ho_Chi_minh: " + service.getZonedDateTime("Asia/Ho_Chi_minh"));
            System.out.println("Current time of Europe/Munich: " + service.getZonedDateTime("Europe/Munich"));

            //service.resultSetDisplay();

            System.out.print("Enter a provider name: ");
            String providerName = sc.nextLine();
            System.out.println(service.printItembyProvider(providerName));

        } catch (NotBoundException | RemoteException e) {
            System.out.println("Exception: " + e);
        }

    }
}