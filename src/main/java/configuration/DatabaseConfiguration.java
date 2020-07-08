package configuration;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConfiguration {

  private static final String FILENAME = "gameshistory.db";
  private static final String JDBC_URL = "jdbc:sqlite:c:\\var\\sqlite\\db\\";

  public static void createNewDatabase() {

    String url = JDBC_URL + FILENAME;

    try {
      Class.forName("org.sqlite.JDBC");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println(e.getMessage());
    }

    try (Connection conn = DriverManager.getConnection(url)) {
      if (conn != null) {
        DatabaseMetaData meta = conn.getMetaData();
        System.out.println("The driver name is " + meta.getDriverName());
        System.out.println("A new database has been created.");
      }

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void createNewTable() {
    // SQLite connection string
    String url = JDBC_URL + FILENAME;

    // SQL statement for creating a new table
    String sql = "CREATE TABLE IF NOT EXISTS positionshistory ("
            + "	id integer PRIMARY KEY,"
            + "	move integer,"
            + "	player_char_moved text,"
            + "	evaluation real,"
            + " oOpenDoubles integer,"
            + " oMostlyOpenDoubles integer,"
            + " oHalfOpenDoubles integer,"
            + " oOpenDisconnectedDoubles integer,"
            + " oMostlyOpenDisconnectedDoubles integer,"
            + " oHalfOpenDisconnectedDoubles integer,"
            + " oOpenTriples integer,"
            + " oMostlyOpenTriples integer,"
            + " oHalfOpenTriples integer,"
            + " oOpenDisconnectedTriples integer,"
            + " oHalfOpenDisconnectedTriples integer,"
            + " oTwiceDisconnectedTriples integer,"
            + " oOpenQuadruples integer,"
            + " oHalfOpenQuadruples integer,"
            + " oDisconnectedQuadruples integer,"
            + " oQuintuples integer,"
            + " xOpenDoubles integer,"
            + " xMostlyOpenDoubles integer,"
            + " xHalfOpenDoubles integer,"
            + " xOpenDisconnectedDoubles integer,"
            + " xMostlyOpenDisconnectedDoubles integer,"
            + " xHalfOpenDisconnectedDoubles integer,"
            + " xOpenTriples integer,"
            + " xMostlyOpenTriples integer,"
            + " xHalfOpenTriples integer,"
            + " xOpenDisconnectedTriples integer,"
            + " xHalfOpenDisconnectedTriples integer,"
            + " xTwiceDisconnectedTriples integer,"
            + " xOpenQuadruples integer,"
            + " xHalfOpenQuadruples integer,"
            + " xDisconnectedQuadruples integer,"
            + " xQuintuples integer"
            + ");";

    try {
      Class.forName("org.sqlite.JDBC");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println(e.getMessage());
    }

    try (Connection conn = DriverManager.getConnection(url);
         Statement stmt = conn.createStatement()) {
      // create a new table
      stmt.execute(sql);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

}
