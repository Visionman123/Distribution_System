package Hands_on_lab_1;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.TimeZone;

/**
 * 
 * @author HOME
 */
public class Server extends UnicastRemoteObject implements adder, services, ServerInterface {
    public Server() throws RemoteException {
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

    // Set up database
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "Nhn@300102";
    private final String database = "Distributed_system";

    public Connection connect() throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // Database query
    public ResultSetMetaData printItembyProvider(String providerName) throws RemoteException, SQLException{
        String SQL = "SELECT * FROM \"Retail Sales\" where supplier=?";
        ResultSet rs = null;
        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            rs = pstmt.executeQuery();
        }

        // result set display
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        for (int i = 1; i <= columnsNumber; i++) {
            System.out.print(rsmd.getColumnName(i) + "\t");
        }
        

        while (rs.next()) {
            System.out.println(rs.getString("year") + "It"
                    + rs.getString("month") + "It"
                    + rs.getString("supplier") + "It"
                    + rs.getString("item_code") + "It"
                    + rs.getString("item description") + "It"
                    + rs.getString("item type") + "It"
                    + rs.getString("retail sales") + "It"
                    + rs.getString("retail transfer") + "It"
                    + rs.getString("warehouse sales"));
        }
        return rsmd;
    }

    public static void main(String args[]) throws RemoteException {
        try {
            Registry reg = LocateRegistry.createRegistry(4444);
            reg.rebind("Add service", new server());
            System.out.println("Server is ready...");
        } catch (RemoteException e) {
            System.out.println("Exception: " + e);
        }
    }
}