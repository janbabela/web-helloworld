package service;

import dto.CharPosition;

public interface AdvancedMoveService {

  CharPosition findBestMoveTwoSteps(String[][] positionMatrix, String playerChar, boolean startOfGame);

  CharPosition findBestMoveOneStep(String[][] positionMatrix, String playerChar, boolean startOfGame);

  double getEvaluation();
}
