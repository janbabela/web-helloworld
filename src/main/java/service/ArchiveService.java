package service;

import dto.PositionDto;

import java.util.List;

public interface ArchiveService {

  void archivePosition(PositionDto positionDto);

  void archiveGame();

  void evaluateGamePositions();

  List<PositionDto> getGame();

}
