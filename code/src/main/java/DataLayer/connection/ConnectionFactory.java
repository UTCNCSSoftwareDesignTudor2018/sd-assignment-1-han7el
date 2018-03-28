package DataLayer.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Boros on 3/26/2018.
 */

public class ConnectionFactory {

    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DBURL = "jdbc:mysql://localhost:3306/utcn";
    private static final String USER = "root";
    private static final String PASS = "dbuser";

    private static ConnectionFactory singleInstance = new ConnectionFactory();

    private ConnectionFactory(){
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }

    public static Connection getConnection(){
        return singleInstance.createConnection();
    }

    public static void close(Connection connection){
        if(connection != null){
            try{
                connection.close();
            }catch(SQLException e){
                LOGGER.log(Level.WARNING,"An error occured while trying to close the connection");
            }
        }
    }

    public static void close(Statement statement){
        if(statement !=null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
            }
        }
    }

    public static void close(ResultSet resultSet){
        if(resultSet != null){
            try{
                resultSet.close();
            }catch(SQLException e){
                LOGGER.log(Level.WARNING,"An error occured while trying to close the ResultSet");
            }
        }
    }


}
