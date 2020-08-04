package game.impl;

import dto.CharPosition;
import dto.PositionDto;
import game.GameMode;
import lombok.Getter;
import parser.Parser;
import service.ArchiveService;
import service.ModelingService;
import service.impl.ArchiveServiceImpl;
import service.impl.ModelingServiceImpl;

public class PlayerPlayerImpl implements GameMode {

  @Getter
  private double evaluation;

  private String playerChar;

  private final ModelingService modelingService = new ModelingServiceImpl();
  private final ArchiveService archiveService = new ArchiveServiceImpl();
  private final Parser parser = new Parser();
  boolean gameOver;

  public PlayerPlayerImpl() {
    this.playerChar = "X";
  }

  public CharPosition playMoves(String position) {

    if (gameOver) return new CharPosition(-1, -1);

    PositionDto lastPosition = modelingService.describePosition(parser.parseAll(position));
    archiveService.archivePosition(lastPosition);

    if (checkGameOver(lastPosition)) {
      archiveService.archiveGame();
      evaluation = -1;
      return new CharPosition(-1, -1);
    }

    playerChar = "X".equals(playerChar) ? "O" : "X";
    return new CharPosition(0, 0);
  }

  private boolean checkGameOver(PositionDto lastPosition) {
    if (lastPosition.getXQuintuples() > 0 || lastPosition.getOQuintuples() > 0) {
      gameOver = true;
      return true;
    }
    return false;
  }
}
