package app.servlets;

import assembler.PositionAssembler;
import dao.impl.PositionsHistoryDaoImpl;
import dto.PositionDto;
import parser.Parser;
import service.GameService;
import service.ModelingService;
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

  PositionsHistoryDaoImpl positionsHistoryDaoImpl = new PositionsHistoryDaoImpl();

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String position = request.getParameter("position");

    Parser parser = new Parser();
    String[][] positionMatrix = parser.parseAll(position);

    modelingService.describePosition(positionMatrix);

    PositionDto lastPosition = modelingService.getPositionDto();
    gameService.archivePosition(lastPosition);

    if (lastPosition.getOQuintuples() == 1 || lastPosition.getXQuintuples() ==1) {
      gameService.evaluateGamePositions();
      positionsHistoryDaoImpl.insertGame(positionAssembler.extractPositionModels(gameService.getGame()));
    }


    response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(lastPosition.toString());
  }

}
