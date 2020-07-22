package dto;

import lombok.Data;

@Data
public class CharPosition {

  int row;

  int column;

  public CharPosition(int rowIndex, int columnIndex) {
    row = rowIndex;
    column = columnIndex;
  }
}
