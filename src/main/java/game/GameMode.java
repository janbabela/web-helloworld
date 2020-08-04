package game;

import dto.CharPosition;

public interface GameMode {

  CharPosition playMoves(String position);

  double getEvaluation();

}
