package model;

import lombok.Data;

@Data
public class PositionModel {

  public PositionModel() {
    initPositionModel();
  }

  // ___OO___
  public int oOpenDoubles;

  // __OO__, __OO___
  public int oMostlyOpenDoubles;

  // _OO__, OO___
  public int oHalfOpenDoubles;

  // ___XX___
  public int xOpenDoubles;

  // __XX__, __XX___
  public int xMostlyOpenDoubles;

  // _XX__, XX___
  public int xHalfOpenDoubles;

  public void addOpenDoublesByPlayer(String playerChar) {
    if ("O".equals(playerChar)) {
      oOpenDoubles++;
    }
    else {
      xOpenDoubles++;
    }
  }

  public void addMostlyOpenDoublesByPlayer(String playerChar) {
    if ("O".equals(playerChar)) {
      oMostlyOpenDoubles++;
    }
    else {
      xMostlyOpenDoubles++;
    }
  }

  public void addHalfDoublesByPlayer(String playerChar) {
    if ("O".equals(playerChar)) {
      oHalfOpenDoubles++;
    }
    else {
      xHalfOpenDoubles++;
    }
  }

  private void initPositionModel() {
    oOpenDoubles = 0;
    oMostlyOpenDoubles = 0;
    oHalfOpenDoubles = 0;
    xOpenDoubles = 0;
    xMostlyOpenDoubles = 0;
    xHalfOpenDoubles = 0;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder("PositionModel" );
    sb.append(System.getProperty("line.separator"));
    sb.append("oOpenDoubles:   " + oOpenDoubles);
    sb.append(System.getProperty("line.separator"));
    sb.append("oMostlyOpenDoubles:   " + oMostlyOpenDoubles);
    sb.append(System.getProperty("line.separator"));
    sb.append("oHalfOpenDoubles:   " + oHalfOpenDoubles);
    sb.append(System.getProperty("line.separator"));
    sb.append("xOpenDoubles:   " + xOpenDoubles);
    sb.append(System.getProperty("line.separator"));
    sb.append("xMostlyOpenDoubles:   " + xMostlyOpenDoubles);
    sb.append(System.getProperty("line.separator"));
    sb.append("xHalfOpenDoubles:   " + xHalfOpenDoubles);

    return sb.toString();
  }

}
