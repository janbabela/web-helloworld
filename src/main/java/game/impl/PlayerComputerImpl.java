package game.impl;

import dto.CharPosition;
import dto.PositionDto;
import game.GameMode;
import lombok.Getter;
import parser.Parser;
import service.AdvancedMoveService;
import service.ArchiveService;
import service.ComputerMoveService;
import service.ModelingService;
import service.impl.AdvancedMoveServiceImpl;
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

//  private final ComputerMoveService computerMoveService = new ComputerMoveServiceImpl("O");

  private final AdvancedMoveService computerMoveService = new AdvancedMoveServiceImpl("O");

  public CharPosition playMoves(String position) {

    if (gameOver) {
      evaluation = -1;
      return new CharPosition(-1,-1);
    }

    String[][] positionMatrix = parser.parseAll(position);

    PositionDto lastXPosition = modelingService.describePosition(positionMatrix);
    archiveService.archivePosition(lastXPosition);

    if (lastXPosition.getXQuintuples() > 0) {
      gameOver();
      return new CharPosition(-1, -1);
    }

    CharPosition nextMove = computerMoveService.findBestMoveTwoSteps(positionMatrix, "O", modelingService.isStartOfGame(positionMatrix));
    positionMatrix[nextMove.getRow()][nextMove.getColumn()] = "O";
    PositionDto lastOPosition = modelingService.describePosition(positionMatrix);
    archiveService.archivePosition(lastOPosition);

    if (lastOPosition.getOQuintuples() > 0) {
      gameOver();
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

  private void gameOver() {
    archiveService.archiveGame();
    gameOver = true;
    evaluation = -1;
  }
}
