package service;

import dto.CharPosition;

public interface ComputerMoveService {

  void inicialize();

  CharPosition makeMove(String[][] positionMatrix, String playerChar);
}
