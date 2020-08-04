package service;

import dto.PositionDto;

public interface ModelingService {

  PositionDto describePosition(String[][] positionMatrix);

  PositionDto getPositionDto();
}
