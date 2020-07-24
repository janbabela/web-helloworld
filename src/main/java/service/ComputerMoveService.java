package service;

import dto.CharPosition;

public interface ComputerMoveService {

  void initialize(String playerChar);

  CharPosition makeMove(String[][] positionMatrix, String playerChar);
}
