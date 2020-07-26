package app.servlets;

import assembler.PositionAssembler;
import dao.PositionHistoryDao;
import dao.impl.PositionsHistoryDaoImpl;
import dto.CharPosition;
import dto.PositionDto;
import parser.Parser;
import service.ComputerMoveService;
import service.GameService;
import service.ModelingService;
import service.impl.ComputerMoveServiceImpl;
import service.impl.GameServiceImpl;
import service.impl.ModelingServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/position/*")
public class TicTacToe extends HttpServlet {

  ModelingService modelingService = new ModelingServiceImpl();
  GameService gameService = new GameServiceImpl();
  PositionAssembler positionAssembler = new PositionAssembler();
  Parser parser = new Parser();
  boolean startOfGame;
  boolean gameOver;

  PositionHistoryDao positionsHistoryDao = new PositionsHistoryDaoImpl();
  ComputerMoveService computerMoveService = new ComputerMoveServiceImpl("O");

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String position = request.getParameter("position");

    String[][] positionMatrix = parser.parseAll(position);

    modelingService.describePosition(positionMatrix);
    PositionDto lastXPosition = modelingService.getPositionDto();
    gameService.archivePosition(lastXPosition);

    if (lastXPosition.getXQuintuples() ==1) {
      gameService.evaluateGamePositions();
      positionsHistoryDao.insertGame(positionAssembler.extractPositionModels(gameService.getGame()));
      gameOver = true;
    }

    startOfGame = gameService.getGame().size() < 10;
    CharPosition nextMove = computerMoveService.makeMove(positionMatrix, "O", startOfGame);
    positionMatrix[nextMove.getRow()][nextMove.getColumn()] = "O";
    modelingService.describePosition(positionMatrix);
    PositionDto lastOPosition = modelingService.getPositionDto();
    gameService.archivePosition(lastOPosition);

    if (lastOPosition.getOQuintuples() ==1) {
      gameService.evaluateGamePositions();
      positionsHistoryDao.insertGame(positionAssembler.extractPositionModels(gameService.getGame()));
      gameOver = true;
    }

    // transform to js table
    nextMove.setRow(nextMove.getRow()+1);
    nextMove.setColumn(nextMove.getColumn()+1);

    response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(nextMove.toString() + " " + computerMoveService.getEvaluation());
  }

}
