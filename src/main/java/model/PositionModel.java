package model;

import lombok.Data;

@Data
public class PositionModel {

  private int moveNumber;
  private String lastMove = "";
  private float evaluation;

  private int[] positionDescription = new int[32];

}
