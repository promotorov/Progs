package serverSample;

import org.postgresql.ds.PGConnectionPoolDataSource;
import javax.sql.PooledConnection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseCommunication {
    private final PGConnectionPoolDataSource pooledDataSource = new PGConnectionPoolDataSource();
    private final PooledConnection pooledConnection;
    private final String username;
    private final String password;
    public DataBaseCommunication(String url, String username, String password, String driver)throws ClassNotFoundException, SQLException {
        this.username=username;
        Class.forName(driver);
        this.password=password;
        pooledDataSource.setUrl(url);
        pooledConnection = pooledDataSource.getPooledConnection(username, password);
        pooledConnection.getConnection().setAutoCommit(false);
        Statement statement = pooledConnection.getConnection().createStatement();
        System.out.println("Success");
    }
    public Statement getStatement(){
        try {
            return pooledConnection.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public PooledConnection getPooledConnection() {
        return pooledConnection;
    }
}