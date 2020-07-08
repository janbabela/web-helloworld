package service;

import dto.PositionDto;

public interface ModelingService {

  void describePosition(String[][] positionMatrix);

  PositionDto getPositionDto();
}
