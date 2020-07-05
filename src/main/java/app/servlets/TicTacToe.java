package app.servlets;

import parser.Parser;
import service.ModelingService;
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

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String position = request.getParameter("position");

    Parser parser = new Parser();
    String[][] positionMatrix = parser.parseAll(position);

    modelingService.describePosition(positionMatrix);

    response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
    response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
    response.getWriter().write(modelingService.getPositionModel().toString());       // Write response body.
  }

}
