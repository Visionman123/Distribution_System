package Hands_on_lab_1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public interface ServerInterface extends Remote {
    ResultSetMetaData printItembyProvider(String providerName) throws RemoteException, SQLException;
}
