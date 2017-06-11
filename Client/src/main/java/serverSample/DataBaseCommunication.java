package serverSample;

import org.postgresql.ds.PGConnectionPoolDataSource;
import javax.sql.PooledConnection;
import java.sql.SQLException;

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
        System.out.println("Success");
    }
}