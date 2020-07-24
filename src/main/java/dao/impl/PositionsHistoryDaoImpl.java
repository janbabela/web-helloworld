package dao.impl;

import dao.PositionHistoryDao;
import model.PositionModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
  public List<PositionModel> selectAll() {
    String sql = "SELECT id, player_char_moved, evaluation," +
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
            " xQuintuples" +
            " FROM positionshistory";

    List<PositionModel> positionsHistory = new ArrayList<>();
    try (Connection conn = this.connect();
         Statement stmt  = conn.createStatement();
         ResultSet rs    = stmt.executeQuery(sql)){

      // loop through the result set
      while (rs.next()) {
        PositionModel positionModel = new PositionModel();
        positionModel.setLastMove(rs.getString("player_char_moved"));
        positionModel.setEvaluation(rs.getFloat("evaluation"));
        for (int i=0; i<32; i++) {
          positionModel.getPositionDescription()[i] = rs.getInt(i+4);
        }
        positionsHistory.add(positionModel);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return positionsHistory;
  }

  public static void main(String[] args) {
    PositionsHistoryDaoImpl positionsHistoryDao = new PositionsHistoryDaoImpl();
    for (int i=28; i<32; i++) {
      PositionModel positionModel = new PositionModel();
      positionModel.setLastMove("O");
      positionModel.setMoveNumber(5);
      positionModel.setEvaluation(0.4f);
      positionModel.getPositionDescription()[i] = 1;
      positionsHistoryDao.insertPosition(positionModel);
    }
  }
  
}
