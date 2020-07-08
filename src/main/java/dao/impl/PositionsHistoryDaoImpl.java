package dao.impl;

import dao.PositionHistoryDao;
import model.PositionModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PositionsHistoryDaoImpl implements PositionHistoryDao {

  private static final String FILENAME = "gameshistory.db";
  private static final String JDBC_URL = "jdbc:sqlite:c:\\var\\sqlite\\db\\";

  private Connection connect() {
    
    String url = JDBC_URL + FILENAME;
    Connection conn = null;
    try {
      Class.forName("org.sqlite.JDBC");
    } catch (ClassNotFoundException e) {
      System.out.println(e.getMessage());
    }
    
    try {
      conn = DriverManager.getConnection(url);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return conn;
  }

  /**
   * Insert a new row into the positionshistory table
   *
   * @param positionModel
   *
   */
  public void insertPosition(PositionModel positionModel) {
    String sql = "INSERT INTO positionshistory (move, player_char_moved, evaluation," +
                 " oOpenDoubles," +
                 " oMostlyOpenDoubles," +
                 " oHalfOpenDoubles," +
                 " oOpenDisconnectedDoubles," +
                 " oMostlyOpenDisconnectedDoubles," +
                 " oHalfOpenDisconnectedDoubles," +
                 " oOpenTriples," +
                 " oMostlyOpenTriples," +
                 " oHalfOpenTriples," +
                 " oOpenDisconnectedTriples," +
                 " oHalfOpenDisconnectedTriples," +
                 " oTwiceDisconnectedTriples," +
                 " oOpenQuadruples," +
                 " oHalfOpenQuadruples," +
                 " oDisconnectedQuadruples," +
                 " oQuintuples," +
                 " xOpenDoubles," +
                 " xMostlyOpenDoubles," +
                 " xHalfOpenDoubles," +
                 " xOpenDisconnectedDoubles," +
                 " xMostlyOpenDisconnectedDoubles," +
                 " xHalfOpenDisconnectedDoubles," +
                 " xOpenTriples," +
                 " xMostlyOpenTriples," +
                 " xHalfOpenTriples," +
                 " xOpenDisconnectedTriples," +
                 " xHalfOpenDisconnectedTriples," +
                 " xTwiceDisconnectedTriples," +
                 " xOpenQuadruples," +
                 " xHalfOpenQuadruples," +
                 " xDisconnectedQuadruples," +
                 " xQuintuples)" +
                 " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    try (Connection conn = this.connect(); 
      PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
      preparedStatement.setInt(1, positionModel.getMoveNumber());
      preparedStatement.setString(2, positionModel.getLastMove());
      preparedStatement.setFloat(3, positionModel.getEvaluation());
      for (int i=4; i<36; i++) {
        preparedStatement.setInt(i, positionModel.getPositionDescription()[i-4]);
      }
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void insertGame(List<PositionModel> game) {

    String sql = "INSERT INTO positionshistory (move, player_char_moved, evaluation," +
            " oOpenDoubles," +
            " oMostlyOpenDoubles," +
            " oHalfOpenDoubles," +
            " oOpenDisconnectedDoubles," +
            " oMostlyOpenDisconnectedDoubles," +
            " oHalfOpenDisconnectedDoubles," +
            " oOpenTriples," +
            " oMostlyOpenTriples," +
            " oHalfOpenTriples," +
            " oOpenDisconnectedTriples," +
            " oHalfOpenDisconnectedTriples," +
            " oTwiceDisconnectedTriples," +
            " oOpenQuadruples," +
            " oHalfOpenQuadruples," +
            " oDisconnectedQuadruples," +
            " oQuintuples," +
            " xOpenDoubles," +
            " xMostlyOpenDoubles," +
            " xHalfOpenDoubles," +
            " xOpenDisconnectedDoubles," +
            " xMostlyOpenDisconnectedDoubles," +
            " xHalfOpenDisconnectedDoubles," +
            " xOpenTriples," +
            " xMostlyOpenTriples," +
            " xHalfOpenTriples," +
            " xOpenDisconnectedTriples," +
            " xHalfOpenDisconnectedTriples," +
            " xTwiceDisconnectedTriples," +
            " xOpenQuadruples," +
            " xHalfOpenQuadruples," +
            " xDisconnectedQuadruples," +
            " xQuintuples)" +
            " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


    try (Connection conn = this.connect();
         PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
      for (PositionModel positionModel:game) {
        preparedStatement.setInt(1, positionModel.getMoveNumber());
        preparedStatement.setString(2, positionModel.getLastMove());
        preparedStatement.setFloat(3, positionModel.getEvaluation());
        for (int i = 4; i < 36; i++) {
          preparedStatement.setInt(i, positionModel.getPositionDescription()[i - 4]);
        }
        preparedStatement.executeUpdate();
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * select all rows in the positionshistory table
   */
  public void selectFirstColumns() {
    String sql = "SELECT id, player_char_moved, evaluation FROM positionshistory";

    try (Connection conn = this.connect();
         Statement stmt  = conn.createStatement();
         ResultSet rs    = stmt.executeQuery(sql)){

      // loop through the result set
      while (rs.next()) {
        System.out.println(rs.getInt("id") + "\t" +
                           rs.getString("player_char_moved") + "\t" +
                           rs.getFloat("evaluation"));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
  
}
