package app.servlets;

import dto.CharPosition;
import game.GameMode;
import game.impl.ComputerComputerImpl;
import game.impl.PlayerComputerImpl;
import game.impl.PlayerPlayerImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/position/*")
public class TicTacToe extends HttpServlet {

  GameMode gameMode = new PlayerComputerImpl();

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    String gameModeId;
    CharPosition nextMove = gameMode.playMoves(request.getParameter("position"));

    response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
    response.setCharacterEncoding("UTF-8");

    if (gameMode instanceof PlayerPlayerImpl) {
      gameModeId = "1";
    }
    else if (gameMode instanceof ComputerComputerImpl) {
      gameModeId = "3";
    }
    else {
      gameModeId = "2";
    }
    response.getWriter().write(gameModeId + " | " + nextMove.toString() + " " + gameMode.getEvaluation());
  }

}
