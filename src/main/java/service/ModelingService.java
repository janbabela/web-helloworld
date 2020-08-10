package service;

import dto.CharPosition;
import dto.PositionDto;

public interface ModelingService {

  PositionDto describePosition(String[][] positionMatrix);

  PositionDto fastDescribePosition(String[][] positionMatrix, PositionDto previousPositionDto, CharPosition nextMove, String playerChar);

  PositionDto getPositionDto();
}
