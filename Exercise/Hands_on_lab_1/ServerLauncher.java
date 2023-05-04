package Hands_on_lab_1;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerLauncher {

    public static void main(String[] args) {
        try {
            // Create and export the server object
            ServerInterface server = new server();
            // Get the RMI registry
            Registry registry = LocateRegistry.createRegistry(1099);
            // Bind the server object to the registry
            registry.rebind("Server", server);
            System.out.println("Server ready");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
