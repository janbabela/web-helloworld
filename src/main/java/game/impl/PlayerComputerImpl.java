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

public class PlayerComputerImpl implements GameMode {

  private final ModelingService modelingService = new ModelingServiceImpl();
  private final ArchiveService archiveService = new ArchiveServiceImpl();
  private final Parser parser = new Parser();
  boolean gameOver;

  @Getter
  private double evaluation;

  private final ComputerMoveService computerMoveService = new ComputerMoveServiceImpl("O");

  public CharPosition playMoves(String position) {

    if (gameOver) {
      evaluation = -1;
      return new CharPosition(-1,-1);
    }

    String[][] positionMatrix = parser.parseAll(position);

    PositionDto lastXPosition = modelingService.describePosition(positionMatrix);
    archiveService.archivePosition(lastXPosition);

    if (lastXPosition.getXQuintuples() > 0) {
      archiveService.archiveGame();
      gameOver = true;
      evaluation = -1;
      return new CharPosition(-1, -1);
    }

    CharPosition nextMove = computerMoveService.makeMove(positionMatrix, "O", archiveService.getGame().size() < 10);
    positionMatrix[nextMove.getRow()][nextMove.getColumn()] = "O";
    PositionDto lastOPosition = modelingService.describePosition(positionMatrix);
    archiveService.archivePosition(lastOPosition);

    if (lastOPosition.getOQuintuples() > 0) {
      archiveService.archiveGame();
      gameOver = true;
      evaluation = -1;
    }
    else {
      evaluation = computerMoveService.getEvaluation();
    }

    transformToJSTable(nextMove);

    return nextMove;
  }

  private void transformToJSTable(CharPosition nextMove) {
    nextMove.setRow(nextMove.getRow() + 1);
    nextMove.setColumn(nextMove.getColumn() + 1);
  }
}
