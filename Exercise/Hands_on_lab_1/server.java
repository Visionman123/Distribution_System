package Hands_on_lab_1;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.ZonedDateTime;
import java.util.TimeZone;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author HOME
 */

public class server extends UnicastRemoteObject implements services {

    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "Nhn@300102";

    public server() throws RemoteException {
        super();
    }

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
        String[] id = TimeZone.getAvailableIDs();
        ZonedDateTime zoneNow = ZonedDateTime.now(TimeZone.getTimeZone(zonedTime).toZoneId());
        return zoneNow;
    }

    @Override
    public String printItembyProvider(String providerName) throws RemoteException {
        String query = "SELECT * FROM \"Retail Sales\" WHERE supplier = \'" + providerName + "\'";
        String ans = "";
        try {
            Connection conn = connect();
            // System.out.println(conn);
            Statement pstmt = conn.createStatement();
            ResultSet rs = pstmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            for (int i = 1; i <= columnsNumber; i++) {
                ans = ans + (rsmd.getColumnName(i) + "\t");
            }

            while (rs.next()) {
                ans = ans + (rs.getString("year") + "\t"
                        + rs.getString("month") + "\t"
                        + rs.getString("supplier") + "\t"
                        + rs.getString("item_code") + "\t"
                        + rs.getString("item_description") + "\t"
                        + rs.getString("item_type") + "\t"
                        + rs.getString("retail_sales") + "\t"
                        + rs.getString("retail_transfer") + "\t"
                        + rs.getString("warehouse_sales") + "\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ans;
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