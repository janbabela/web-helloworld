package game.impl;

import dto.CharPosition;
import dto.PositionDto;
import game.GameMode;
import lombok.Getter;
import parser.Parser;
import service.ArchiveService;
import service.ComputerMoveService;
import service.ModelingService;
import service.impl.ArchiveServiceImpl;
import service.impl.ComputerMoveServiceImpl;
import service.impl.ModelingServiceImpl;

public class ComputerComputerImpl implements GameMode {

  @Getter
  private double evaluation;

  private String computerChar;

  private final ModelingService modelingService = new ModelingServiceImpl();
  private final ArchiveService archiveService = new ArchiveServiceImpl();
  private final Parser parser = new Parser();
  boolean startOfGame;
  boolean gameOver;

  private final ComputerMoveService computerOMoveService = new ComputerMoveServiceImpl("O");
  private final ComputerMoveService computerXMoveService = new ComputerMoveServiceImpl("X");

  public ComputerComputerImpl() {
    this.computerChar = "O";
  }


  public CharPosition playMoves(String position) {

    if (gameOver) {
      evaluation = -1;
      return new CharPosition(-1,-1);
    }

    String[][] positionMatrix = parser.parseAll(position);

    if (archiveService.getGame().isEmpty()) {
      PositionDto lastPosition = modelingService.describePosition(positionMatrix);
      archiveService.archivePosition(lastPosition);
    }

    startOfGame = archiveService.getGame().size() < 10;
    CharPosition nextMove;
    if ("X".equals(computerChar)) {
      nextMove = computerXMoveService.makeMove(positionMatrix, "X", startOfGame);
      positionMatrix[nextMove.getRow()][nextMove.getColumn()] = "X";
      PositionDto lastXPosition = modelingService.describePosition(positionMatrix);
      archiveService.archivePosition(lastXPosition);
      evaluation = computerXMoveService.getEvaluation();

      if (lastXPosition.getXQuintuples() > 0) {
        archiveService.archiveGame();
        gameOver = true;
        evaluation = -1;
      }
    } else {
      nextMove = computerOMoveService.makeMove(positionMatrix, "O", startOfGame);
      positionMatrix[nextMove.getRow()][nextMove.getColumn()] = "O";
      PositionDto lastOPosition = modelingService.describePosition(positionMatrix);
      archiveService.archivePosition(lastOPosition);
      evaluation = computerOMoveService.getEvaluation();

      if (lastOPosition.getOQuintuples() > 0) {
        archiveService.archiveGame();
        gameOver = true;
        evaluation = -1;
      }
    }

    transformToJSTable(nextMove);
    computerChar = "X".equals(computerChar) ? "O" : "X";
    return nextMove;
  }

  private void transformToJSTable(CharPosition nextMove) {
    nextMove.setRow(nextMove.getRow() + 1);
    nextMove.setColumn(nextMove.getColumn() + 1);
  }

}
