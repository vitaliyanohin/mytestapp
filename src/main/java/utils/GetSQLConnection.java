package utils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetSQLConnection {

  private static final Logger LOGGER = Logger.getLogger(GetSQLConnection.class);
  static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/MyData";
  static final String USER = "postgres";
  static final String PASS = "root";

  public static Connection getMysqlConnection() {
    try {
      Class.forName("org.postgresql.Driver");
      Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
       LOGGER.info("DB name: " + connection.getMetaData().getDatabaseProductName());
       LOGGER.info("DB version: " + connection.getMetaData().getDatabaseProductVersion());
       LOGGER.info("Driver: " + connection.getMetaData().getDriverName());
       LOGGER.info("Autocommit: " + connection.getAutoCommit());
      return connection;
    } catch (ClassNotFoundException | SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to get connection: ", e);
    }
    return null;
  }
}
