package model;

import lombok.Data;

@Data
public class CurrentPosition {

  int row;

  int column;

  public CurrentPosition(int rowIndex, int columnIndex) {
    row = rowIndex;
    column = columnIndex;
  }
}
