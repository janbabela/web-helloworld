package dao;

import model.PositionModel;

import java.util.List;

public interface PositionHistoryDao {

  void insertPosition(PositionModel positionModel);

  void insertGame(List<PositionModel> game);

}
