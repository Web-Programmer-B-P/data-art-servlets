package dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PoolConnection {
    private static final PoolConnection INSTANCE = new PoolConnection();
    private static final Logger LOG = LogManager.getLogger(PoolConnection.class.getName());

    private PoolConnection() {

    }

    public static PoolConnection getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        Context context;
        Connection connection = null;
        try {
            context = new InitialContext();
            DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/todoPool");
            connection = ds.getConnection();
        } catch (NamingException | SQLException e) {
            LOG.error("Смотри в инициализацию контекста пула", e);
        }
        return connection;
    }
}
