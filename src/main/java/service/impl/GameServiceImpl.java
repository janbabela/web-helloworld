package service.impl;

import dto.PositionDto;
import lombok.Getter;
import lombok.Setter;
import service.GameService;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GameServiceImpl implements GameService {

  private List<PositionDto> game = new ArrayList<>();

  private int currentMove = 1;

  private String currentPlayer = "X";

  public void archivePosition(PositionDto positionDto) {

    positionDto.setMoveNumber(currentMove);
    positionDto.setLastMove(currentPlayer);

    game.add(positionDto);

    currentMove++;
    currentPlayer = "X".equals(currentPlayer) ? "O" : "X";
  }

  public void evaluateGamePositions() {

    int gameLength = game.size();
    int xGameLength = (gameLength + 1) / 2;
    int oGameLength = gameLength / 2;
    boolean xWinner = (xGameLength > oGameLength);

    int xMove = 0;
    int oMove = 0;
    for (int move = 0; move < gameLength; move++) {
      PositionDto positionToEvaluate = game.get(move);
      if (move % 2 == 0) {
        // evaluating X move
        xMove++;
        if (xWinner) {
          positionToEvaluate.setEvaluation((float) (0.5 * Math.pow(2, (float) xMove/xGameLength )) );
        } else {
          positionToEvaluate.setEvaluation((float) (0.5 * Math.log((2 -  (float) xMove/xGameLength)) / Math.log(2)) );
        }
      } else {
        // evaluating oMove
        oMove++;
        if (xWinner) {
          positionToEvaluate.setEvaluation((float) (0.5 * Math.log((2 -  (float) oMove/oGameLength)) / Math.log(2)) );
        } else {
          positionToEvaluate.setEvaluation((float) (0.5 * Math.pow(2, (float) oMove/oGameLength)) );
        }
      }
    }
  }

}
