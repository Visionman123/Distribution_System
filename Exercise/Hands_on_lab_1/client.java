package Hands_on_lab_1;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.ResultSetMetaData;

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
            System.out.println("Addition is: " + ad.add(87, 92));

            services Services = (services) registry.lookup("Add service");
            System.out.println("Local time: " + System.nanoTime());
            System.out.println("Remote time: " + Services.getTime());

            Services.putString("Local time: " + System.nanoTime());
            Services.putString("Remote time: " + Services.getTime());

            System.out.println("Current time of Asia/HoChiMinh: " + Services.getZonedDateTime("Asia/HoChiMinh"));
            System.out.println("Current time of Europe/Munich: " + Services.getZonedDateTime("Europe/Munich"));

            // print the ResultSetMetaData
            // Call the remote method
            server Server = (server) registry.lookup("Server");
            ResultSetMetaData rsmd = Server.printItembyProvider("DOPS INC");

            // Process the result set metadata received from the server
            int columnsNumber = rsmd.getColumnCount();
            for (int i = 1; i <= columnsNumber; i++) {
                System.out.print(rsmd.getColumnName(i) + "\t");
            }

        } catch (NotBoundException | RemoteException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}