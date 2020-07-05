package service;

import model.PositionModel;

public interface ModelingService {

  void describePosition(String[][] positionMatrix);

  PositionModel getPositionModel();
}
