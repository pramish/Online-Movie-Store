package MODEL.DAO;

import java.sql.Connection;

public abstract class Database {

    protected String URL = "jdbc:derby://localhost:1527/";
    protected String databaseName = "MovieStore";
    protected String user = "movie";
    protected String password = "movie";
    protected String driver = "org.apache.derby.jdbc.ClientDriver";
    protected Connection conn;
}
