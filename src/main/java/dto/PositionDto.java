package dto;

import lombok.Data;

@Data
public class PositionDto {

  public PositionDto() {
    evaluation = (float) -1;
  }

  private int moveNumber;
  private String lastMove = "";
  private float evaluation;
  
  // ___OO___
  private int oOpenDoubles;

  // __OO__, __OO___
  private int oMostlyOpenDoubles;

  // _OO__, OO___
  private int oHalfOpenDoubles;

  // __O_O__
  private int oOpenDisconnectedDoubles;

  // _O_O__
  private int oMostlyOpenDisconnectedDoubles;

  // O_O__, _O_O_
  private int oHalfOpenDisconnectedDoubles;

  // __OOO__
  private int oOpenTriples;

  // _OOO_
  private int oMostlyOpenTriples;

  // OOO__
  private int oHalfOpenTriples;

  // _OO_O_
  private int oOpenDisconnectedTriples;
  
  // OO_O_, O_OO_
  private int oHalfOpenDisconnectedTriples;

  // O_O_O
  private int oTwiceDisconnectedTriples;

  // _OOOOO_
  private int oOpenQuadruples;

  // OOOO_
  private int oHalfOpenQuadruples;

  // OOO_O, OO_OO
  private int oDisconnectedQuadruples;

  // OOOOO
  private int oQuintuples;

  // ___XX___
  private int xOpenDoubles;

  // __XX__, __XX___
  private int xMostlyOpenDoubles;

  // _XX__, XX___
  private int xHalfOpenDoubles;

  // __X_X__
  private int xOpenDisconnectedDoubles;

  // _X_X__
  private int xMostlyOpenDisconnectedDoubles;

  // X_X__, _X_X_
  private int xHalfOpenDisconnectedDoubles;

  // __XXX__
  private int xOpenTriples;

  // _XXX_
  private int xMostlyOpenTriples;

  // XXX__
  private int xHalfOpenTriples;

  // _XX_X_
  private int xOpenDisconnectedTriples;

  // XX_X_, X_XX_
  private int xHalfOpenDisconnectedTriples;

  // X_X_X
  private int xTwiceDisconnectedTriples;

  // _XXXX_
  private int xOpenQuadruples;

  // XXXX_
  private int xHalfOpenQuadruples;

  // XXX_X, XX_XX
  private int xDisconnectedQuadruples;

  // XXXXX
  private int xQuintuples;

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

  public void addHalfOpenDoublesByPlayer(String playerChar) {
    if ("O".equals(playerChar)) {
      oHalfOpenDoubles++;
    }
    else {
      xHalfOpenDoubles++;
    }
  }

  public void addOpenDisconnectedDoublesByPlayer(String playerChar) {
    if ("O".equals(playerChar)) {
      oOpenDisconnectedDoubles++;
    }
    else {
      xOpenDisconnectedDoubles++;
    }
  }

  public void addMostlyOpenDisconnectedDoublesByPlayer(String playerChar) {
    if ("O".equals(playerChar)) {
      oMostlyOpenDisconnectedDoubles++;
    }
    else {
      xMostlyOpenDisconnectedDoubles++;
    }
  }

  public void addHalfOpenDisconnectedDoublesByPlayer(String playerChar) {
    if ("O".equals(playerChar)) {
      oHalfOpenDisconnectedDoubles++;
    }
    else {
      xHalfOpenDisconnectedDoubles++;
    }
  }

  public void addOpenTriplesByPlayer(String playerChar) {
    if ("O".equals(playerChar)) {
      oOpenTriples++;
    }
    else {
      xOpenTriples++;
    }
  }

  public void addMostlyOpenTriplesByPlayer(String playerChar) {
    if ("O".equals(playerChar)) {
      oMostlyOpenTriples++;
    }
    else {
      xMostlyOpenTriples++;
    }
  }

  public void addHalfOpenTriplesByPlayer(String playerChar) {
    if ("O".equals(playerChar)) {
      oHalfOpenTriples++;
    }
    else {
      xHalfOpenTriples++;
    }
  }

  public void addOpenDisconnectedTriplesByPlayer(String playerChar) {
    if ("O".equals(playerChar)) {
      oOpenDisconnectedTriples++;
    }
    else {
      xOpenDisconnectedTriples++;
    }
  }
  
  public void addHalfOpenDisconnectedTriplesByPlayer(String playerChar) {
    if ("O".equals(playerChar)) {
      oHalfOpenDisconnectedTriples++;
    }
    else {
      xHalfOpenDisconnectedTriples++;
    }
  }

  public void addTwiceDisconnectedTriplesByPlayer(String playerChar) {
    if ("O".equals(playerChar)) {
      oTwiceDisconnectedTriples++;
    }
    else {
      xTwiceDisconnectedTriples++;
    }
  }

  public void addOpenQuadruplesByPlayer(String playerChar) {
    if ("O".equals(playerChar)) {
      oOpenQuadruples++;
    }
    else {
      xOpenQuadruples++;
    }
  }

  public void addHalfOpenQuadruplesByPlayer(String playerChar) {
    if ("O".equals(playerChar)) {
      oHalfOpenQuadruples++;
    }
    else {
      xHalfOpenQuadruples++;
    }
  }

  public void addDisconnectedQuadruplesByPlayer(String playerChar) {
    if ("O".equals(playerChar)) {
      oDisconnectedQuadruples++;
    }
    else {
      xDisconnectedQuadruples++;
    }
  }

  public void addQuintuplesByPlayer(String playerChar) {
    if ("O".equals(playerChar)) {
      oQuintuples++;
    }
    else {
      xQuintuples++;
    }
  }

  public PositionDto add(PositionDto secondPositionDto) {

    PositionDto result = new PositionDto();

    result.setOOpenDoubles(this.oOpenDoubles + secondPositionDto.getOOpenDoubles());
    result.setOMostlyOpenDoubles(this.oMostlyOpenDoubles + secondPositionDto.getOMostlyOpenDoubles());
    result.setOHalfOpenDoubles(this.oHalfOpenDoubles + secondPositionDto.getOHalfOpenDoubles());
    result.setOOpenDisconnectedDoubles(this.oOpenDisconnectedDoubles + secondPositionDto.getOOpenDisconnectedDoubles());
    result.setOMostlyOpenDisconnectedDoubles(this.oMostlyOpenDisconnectedDoubles + secondPositionDto.getOMostlyOpenDoubles());
    result.setOHalfOpenDisconnectedDoubles(this.oHalfOpenDisconnectedDoubles + secondPositionDto.getOHalfOpenDisconnectedDoubles());
    result.setOOpenTriples(this.oOpenTriples + secondPositionDto.getOOpenTriples());
    result.setOMostlyOpenTriples(this.oMostlyOpenTriples + secondPositionDto.getOMostlyOpenTriples());
    result.setOHalfOpenTriples(this.oHalfOpenTriples + secondPositionDto.getOHalfOpenTriples());
    result.setOOpenDisconnectedTriples(this.oOpenDisconnectedTriples + secondPositionDto.getOOpenDisconnectedTriples());
    result.setOHalfOpenDisconnectedTriples(this.oHalfOpenDisconnectedTriples + secondPositionDto.getOHalfOpenDisconnectedTriples());
    result.setOTwiceDisconnectedTriples(this.oTwiceDisconnectedTriples + secondPositionDto.getOTwiceDisconnectedTriples());
    result.setOOpenQuadruples(this.oOpenQuadruples + secondPositionDto.getOOpenQuadruples());
    result.setOHalfOpenQuadruples(this.oHalfOpenQuadruples + secondPositionDto.getOHalfOpenQuadruples());
    result.setODisconnectedQuadruples(this.oDisconnectedQuadruples + secondPositionDto.getODisconnectedQuadruples());
    result.setOQuintuples(this.oQuintuples + secondPositionDto.getOQuintuples());

    result.setXOpenDoubles(this.xOpenDoubles + secondPositionDto.getXOpenDoubles());
    result.setXMostlyOpenDoubles(this.xMostlyOpenDoubles + secondPositionDto.getXMostlyOpenDoubles());
    result.setXHalfOpenDoubles(this.xHalfOpenDoubles + secondPositionDto.getXHalfOpenDoubles());
    result.setXOpenDisconnectedDoubles(this.xOpenDisconnectedDoubles + secondPositionDto.getXOpenDisconnectedDoubles());
    result.setXMostlyOpenDisconnectedDoubles(this.xMostlyOpenDisconnectedDoubles + secondPositionDto.getXMostlyOpenDoubles());
    result.setXHalfOpenDisconnectedDoubles(this.xHalfOpenDisconnectedDoubles + secondPositionDto.getXHalfOpenDisconnectedDoubles());
    result.setXOpenTriples(this.xOpenTriples + secondPositionDto.getXOpenTriples());
    result.setXMostlyOpenTriples(this.xMostlyOpenTriples + secondPositionDto.getXMostlyOpenTriples());
    result.setXHalfOpenTriples(this.xHalfOpenTriples + secondPositionDto.getXHalfOpenTriples());
    result.setXOpenDisconnectedTriples(this.xOpenDisconnectedTriples + secondPositionDto.getXOpenDisconnectedTriples());
    result.setXHalfOpenDisconnectedTriples(this.xHalfOpenDisconnectedTriples + secondPositionDto.getXHalfOpenDisconnectedTriples());
    result.setXTwiceDisconnectedTriples(this.xTwiceDisconnectedTriples + secondPositionDto.getXTwiceDisconnectedTriples());
    result.setXOpenQuadruples(this.xOpenQuadruples + secondPositionDto.getXOpenQuadruples());
    result.setXHalfOpenQuadruples(this.xHalfOpenQuadruples + secondPositionDto.getXHalfOpenQuadruples());
    result.setXDisconnectedQuadruples(this.xDisconnectedQuadruples + secondPositionDto.getXDisconnectedQuadruples());
    result.setXQuintuples(this.xQuintuples + secondPositionDto.getXQuintuples());

    return result;
  }

  public PositionDto substract(PositionDto secondPositionDto) {

    PositionDto result = new PositionDto();

    result.setOOpenDoubles(this.oOpenDoubles - secondPositionDto.getOOpenDoubles());
    result.setOMostlyOpenDoubles(this.oMostlyOpenDoubles - secondPositionDto.getOMostlyOpenDoubles());
    result.setOHalfOpenDoubles(this.oHalfOpenDoubles - secondPositionDto.getOHalfOpenDoubles());
    result.setOOpenDisconnectedDoubles(this.oOpenDisconnectedDoubles - secondPositionDto.getOOpenDisconnectedDoubles());
    result.setOMostlyOpenDisconnectedDoubles(this.oMostlyOpenDisconnectedDoubles - secondPositionDto.getOMostlyOpenDoubles());
    result.setOHalfOpenDisconnectedDoubles(this.oHalfOpenDisconnectedDoubles - secondPositionDto.getOHalfOpenDisconnectedDoubles());
    result.setOOpenTriples(this.oOpenTriples - secondPositionDto.getOOpenTriples());
    result.setOMostlyOpenTriples(this.oMostlyOpenTriples - secondPositionDto.getOMostlyOpenTriples());
    result.setOHalfOpenTriples(this.oHalfOpenTriples - secondPositionDto.getOHalfOpenTriples());
    result.setOOpenDisconnectedTriples(this.oOpenDisconnectedTriples - secondPositionDto.getOOpenDisconnectedTriples());
    result.setOHalfOpenDisconnectedTriples(this.oHalfOpenDisconnectedTriples - secondPositionDto.getOHalfOpenDisconnectedTriples());
    result.setOTwiceDisconnectedTriples(this.oTwiceDisconnectedTriples - secondPositionDto.getOTwiceDisconnectedTriples());
    result.setOOpenQuadruples(this.oOpenQuadruples - secondPositionDto.getOOpenQuadruples());
    result.setOHalfOpenQuadruples(this.oHalfOpenQuadruples - secondPositionDto.getOHalfOpenQuadruples());
    result.setODisconnectedQuadruples(this.oDisconnectedQuadruples - secondPositionDto.getODisconnectedQuadruples());
    result.setOQuintuples(this.oQuintuples - secondPositionDto.getOQuintuples());

    result.setXOpenDoubles(this.xOpenDoubles - secondPositionDto.getXOpenDoubles());
    result.setXMostlyOpenDoubles(this.xMostlyOpenDoubles - secondPositionDto.getXMostlyOpenDoubles());
    result.setXHalfOpenDoubles(this.xHalfOpenDoubles - secondPositionDto.getXHalfOpenDoubles());
    result.setXOpenDisconnectedDoubles(this.xOpenDisconnectedDoubles - secondPositionDto.getXOpenDisconnectedDoubles());
    result.setXMostlyOpenDisconnectedDoubles(this.xMostlyOpenDisconnectedDoubles - secondPositionDto.getXMostlyOpenDoubles());
    result.setXHalfOpenDisconnectedDoubles(this.xHalfOpenDisconnectedDoubles - secondPositionDto.getXHalfOpenDisconnectedDoubles());
    result.setXOpenTriples(this.xOpenTriples - secondPositionDto.getXOpenTriples());
    result.setXMostlyOpenTriples(this.xMostlyOpenTriples - secondPositionDto.getXMostlyOpenTriples());
    result.setXHalfOpenTriples(this.xHalfOpenTriples - secondPositionDto.getXHalfOpenTriples());
    result.setXOpenDisconnectedTriples(this.xOpenDisconnectedTriples - secondPositionDto.getXOpenDisconnectedTriples());
    result.setXHalfOpenDisconnectedTriples(this.xHalfOpenDisconnectedTriples - secondPositionDto.getXHalfOpenDisconnectedTriples());
    result.setXTwiceDisconnectedTriples(this.xTwiceDisconnectedTriples - secondPositionDto.getXTwiceDisconnectedTriples());
    result.setXOpenQuadruples(this.xOpenQuadruples - secondPositionDto.getXOpenQuadruples());
    result.setXHalfOpenQuadruples(this.xHalfOpenQuadruples - secondPositionDto.getXHalfOpenQuadruples());
    result.setXDisconnectedQuadruples(this.xDisconnectedQuadruples - secondPositionDto.getXDisconnectedQuadruples());
    result.setXQuintuples(this.xQuintuples - secondPositionDto.getXQuintuples());

    return result;
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder("PositionModel" );
    sb.append(System.getProperty("line.separator"));
    sb.append("oOpenDoubles:                   " + oOpenDoubles + " | xOpenDoubles:                   " + xOpenDoubles);
    sb.append(System.getProperty("line.separator"));
    sb.append("oMostlyOpenDoubles:             " + oMostlyOpenDoubles + " | xMostlyOpenDoubles:             " + xMostlyOpenDoubles);
    sb.append(System.getProperty("line.separator"));
    sb.append("oHalfOpenDoubles:               " + oHalfOpenDoubles + " | xHalfOpenDoubles:               " + xHalfOpenDoubles);
    sb.append(System.getProperty("line.separator"));
    sb.append("oOpenDisconnectedDoubles:       " + oOpenDisconnectedDoubles + " | xOpenDisconnectedDoubles:       " + xOpenDisconnectedDoubles);
    sb.append(System.getProperty("line.separator"));
    sb.append("oMostlyOpenDisconnectedDoubles: " + oMostlyOpenDisconnectedDoubles + " | xMostlyOpenDisconnectedDoubles: " + xMostlyOpenDisconnectedDoubles);
    sb.append(System.getProperty("line.separator"));
    sb.append("oHalfOpenDisconnectedDoubles:   " + oHalfOpenDisconnectedDoubles + " | xHalfOpenDisconnectedDoubles:   " + xHalfOpenDisconnectedDoubles);
    sb.append(System.getProperty("line.separator"));
    sb.append("---------------------------------------------------------------------");
    sb.append(System.getProperty("line.separator"));
    sb.append("oOpenTriples:                   " + oOpenTriples + " | xOpenTriples:                   " + xOpenTriples);
    sb.append(System.getProperty("line.separator"));
    sb.append("oMostlyOpenTriples:             " + oMostlyOpenTriples + " | xMostlyOpenTriples:             " + xMostlyOpenTriples);
    sb.append(System.getProperty("line.separator"));
    sb.append("oHalfOpenTriples:               " + oHalfOpenTriples + " | xHalfOpenTriples:               " + xHalfOpenTriples);
    sb.append(System.getProperty("line.separator"));
    sb.append("oOpenDisconnectedTriples:       " + oOpenDisconnectedTriples + " | xOpenDisconnectedTriples:       " + xOpenDisconnectedTriples);
    sb.append(System.getProperty("line.separator"));
    sb.append("oHalfOpenDisconnectedTriples:   " + oHalfOpenDisconnectedTriples + " | xHalfOpenDisconnectedTriples:   " + xHalfOpenDisconnectedTriples);
    sb.append(System.getProperty("line.separator"));
    sb.append("oTwiceDisconnectedTriples:      " + oTwiceDisconnectedTriples + " | xTwiceDisconnectedTriples:      " + xTwiceDisconnectedTriples);
    sb.append(System.getProperty("line.separator"));
    sb.append("---------------------------------------------------------------------");
    sb.append(System.getProperty("line.separator"));
    sb.append("oOpenQuadruples:                " + oOpenQuadruples + " | xOpenQuadruples:                " + xOpenQuadruples);
    sb.append(System.getProperty("line.separator"));
    sb.append("oHalfOpenQuadruples:            " + oHalfOpenQuadruples + " | xHalfOpenQuadruples:            " + xHalfOpenQuadruples);
    sb.append(System.getProperty("line.separator"));
    sb.append("oDisconnectedQuadruples:        " + oDisconnectedQuadruples + " | xDisconnectedQuadruples:        " + xDisconnectedQuadruples);
    sb.append(System.getProperty("line.separator"));
    sb.append("oQuintuples:                    " + oQuintuples + " | xQuintuples:                    " + xQuintuples);

    return sb.toString();
  }
  
  public boolean equals(PositionDto other) {
    return (this.getOOpenDoubles() == other.getOOpenDoubles() && 
            this.getOHalfOpenDoubles() == other.getOHalfOpenDoubles() &&
            this.getOMostlyOpenDoubles() == other.getOMostlyOpenDoubles() &&
            this.getOOpenDisconnectedDoubles() == other.getOOpenDisconnectedDoubles() &&
            this.getOHalfOpenDisconnectedDoubles() == other.getOHalfOpenDisconnectedDoubles() &&
            this.getOMostlyOpenDisconnectedDoubles() == other.getOMostlyOpenDisconnectedDoubles() &&
            this.getOOpenTriples() == other.getOOpenTriples() &&
            this.getOMostlyOpenTriples() == other.getOMostlyOpenTriples() &&
            this.getOHalfOpenTriples() == other.getOHalfOpenTriples() &&
            this.getOOpenDisconnectedTriples() == other.getOOpenDisconnectedTriples() &&
            this.getOHalfOpenDisconnectedTriples() == other.getOHalfOpenDisconnectedTriples() &&
            this.getOTwiceDisconnectedTriples() == other.getOTwiceDisconnectedTriples() &&
            this.getOOpenQuadruples() == other.getOOpenQuadruples() &&
            this.getOHalfOpenQuadruples() == other.getOHalfOpenQuadruples() &&
            this.getODisconnectedQuadruples() == other.getODisconnectedQuadruples() &&
            this.getOQuintuples() == other.getOQuintuples() &&
            this.getXOpenDoubles() == other.getXOpenDoubles() &&
            this.getXHalfOpenDoubles() == other.getXHalfOpenDoubles() &&
            this.getXMostlyOpenDoubles() == other.getXMostlyOpenDoubles() &&
            this.getXOpenDisconnectedDoubles() == other.getXOpenDisconnectedDoubles() &&
            this.getXHalfOpenDisconnectedDoubles() == other.getXHalfOpenDisconnectedDoubles() &&
            this.getXMostlyOpenDisconnectedDoubles() == other.getXMostlyOpenDisconnectedDoubles() &&
            this.getXOpenTriples() == other.getXOpenTriples() &&
            this.getXMostlyOpenTriples() == other.getXMostlyOpenTriples() &&
            this.getXHalfOpenTriples() == other.getXHalfOpenTriples() &&
            this.getXOpenDisconnectedTriples() == other.getXOpenDisconnectedTriples() &&
            this.getXHalfOpenDisconnectedTriples() == other.getXHalfOpenDisconnectedTriples() &&
            this.getXTwiceDisconnectedTriples() == other.getXTwiceDisconnectedTriples() &&
            this.getXOpenQuadruples() == other.getXOpenQuadruples() &&
            this.getXHalfOpenQuadruples() == other.getXHalfOpenQuadruples() &&
            this.getXDisconnectedQuadruples() == other.getXDisconnectedQuadruples() &&
            this.getXQuintuples() == other.getXQuintuples());
  }
}
