package service;

import dto.PositionDto;

import java.util.List;

public interface GameService {

  void archivePosition(PositionDto positionDto);

  void evaluateGamePositions();

  List<PositionDto> getGame();

}
