package service.impl;

import lombok.Getter;
import lombok.Setter;
import model.PositionModel;
import service.ModelingService;

@Getter
@Setter
public class ModelingServiceImpl implements ModelingService {

  PositionModel positionModel = new PositionModel();

  String[][] positionMatrix = new String[25][25];

  private final static String ROW = "row";
  private final static String COLUMN = "column";
  private final static String FIRST_DIAGONAL = "firstDiagonal";
  private final static String SECOND_DIAGONAL = "secondDiagonal";

  @Override
  public void describePosition(String[][] positionMatrix) {

    this.positionMatrix = positionMatrix;
    positionModel = new PositionModel();
    countDoubles();
  }

  private void countDoubles() {

    countDoublesForPlayer( "X");
    countDoublesForPlayer( "O");
  }

  private void countDoublesForPlayer(String playerChar) {

    countDoublesForPlayerForPositionType(playerChar, ROW);
    countDoublesForPlayerForPositionType(playerChar, COLUMN);
    countDoublesForPlayerForPositionType(playerChar, FIRST_DIAGONAL);
    countDoublesForPlayerForPositionType(playerChar, SECOND_DIAGONAL);
  }

  private void countDoublesForPlayerForPositionType(String playerChar, String positionType) {

    for (int rowIndex=0; rowIndex<25; rowIndex++) {
      for (int columnIndex=0; columnIndex<25; columnIndex++) {
        if ( checkDouble(rowIndex, columnIndex, positionType, playerChar) & !checkTriple(rowIndex, columnIndex, positionType, playerChar)
                & !checkInsideTriple(rowIndex, columnIndex, positionType, playerChar)) {
        // double found, now what is type of double
          if ( checkEmptyFields(rowIndex, columnIndex, positionType, 2,3, 3 )) {
          // open double
            positionModel.addOpenDoublesByPlayer(playerChar);
          }
          else if (checkEmptyFields(rowIndex, columnIndex, positionType, 2,2, 2 )) {
          // mostly open double
            positionModel.addMostlyOpenDoublesByPlayer(playerChar);
          }
          else {
            if (checkEmptyFields(rowIndex, columnIndex, positionType, 2,1, 2 ) ||
                checkEmptyFields(rowIndex, columnIndex, positionType, 2,2, 1 ) ||
                checkEmptyFields(rowIndex, columnIndex, positionType, 2,0, 3 ) ||
                checkEmptyFields(rowIndex, columnIndex, positionType, 2,3, 0 )) {
            // half open double
            positionModel.addHalfDoublesByPlayer(playerChar);
            }
          }
        }
      }
    }
  }

  private boolean checkDouble(int rowIndex, int columnIndex, String positionType, String playerChar) {

    if (ROW.equals(positionType)) {
      if (columnIndex == 24) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex][columnIndex+1])) return true;
    }
    if (COLUMN.equals(positionType)) {
      if (rowIndex == 24) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+1][columnIndex])) return true;
    }
    if (FIRST_DIAGONAL.equals(positionType)) {
      if (rowIndex == 24 || columnIndex == 24) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+1][columnIndex+1])) return true;
    }
    if (SECOND_DIAGONAL.equals(positionType)) {
      if (rowIndex == 0 || columnIndex == 24) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex-1][columnIndex+1])) return true;
    }

    return false;
  }

  private boolean checkEmptyFields(int rowIndex, int columnIndex, String positionType, int numberOfCharsInPattern, int emptyFieldsBefore, int emptyFieldsAfter) {

    if (ROW.equals(positionType)) {
      if (columnIndex < emptyFieldsBefore ||  columnIndex + numberOfCharsInPattern + emptyFieldsAfter > 25) return false;
      for (int i=1; i<emptyFieldsBefore+1; i++) {
        if (!"&nbsp;".equals(positionMatrix[rowIndex][columnIndex-i])) return false;
      }
      for (int i=0; i<emptyFieldsAfter; i++) {
        if (!"&nbsp;".equals(positionMatrix[rowIndex][columnIndex+i+numberOfCharsInPattern])) return false;
      }
    }
    if (COLUMN.equals(positionType)) {
      if (rowIndex < emptyFieldsBefore ||  rowIndex + numberOfCharsInPattern + emptyFieldsAfter > 25) return false;
      for (int i=1; i<emptyFieldsBefore+1; i++) {
        if (!"&nbsp;".equals(positionMatrix[rowIndex-i][columnIndex])) return false;
      }
      for (int i=0; i<emptyFieldsAfter; i++) {
        if (!"&nbsp;".equals(positionMatrix[rowIndex+i+numberOfCharsInPattern][columnIndex])) return false;
      }
    }
    if (FIRST_DIAGONAL.equals(positionType)) {
      if (columnIndex < emptyFieldsBefore ||  columnIndex + numberOfCharsInPattern + emptyFieldsAfter > 25) return false;
      if (rowIndex < emptyFieldsBefore ||  rowIndex + numberOfCharsInPattern + emptyFieldsAfter > 25) return false;
      for (int i=1; i<emptyFieldsBefore+1; i++) {
        if (!"&nbsp;".equals(positionMatrix[rowIndex-i][columnIndex-i])) return false;
      }
      for (int i=0; i<emptyFieldsAfter; i++) {
        if (!"&nbsp;".equals(positionMatrix[rowIndex+i+numberOfCharsInPattern][columnIndex+i+numberOfCharsInPattern])) return false;
      }
    }
    if (SECOND_DIAGONAL.equals(positionType)) {
      if (columnIndex < emptyFieldsBefore ||  columnIndex + numberOfCharsInPattern + emptyFieldsAfter > 25) return false;
      if (rowIndex + emptyFieldsBefore > 24 ||  rowIndex - numberOfCharsInPattern - emptyFieldsAfter + 1 < 0) return false;
      for (int i=1; i<emptyFieldsBefore+1; i++) {
        if (!"&nbsp;".equals(positionMatrix[rowIndex+i][columnIndex-i])) return false;
      }
      for (int i=0; i<emptyFieldsAfter; i++) {
        if (!"&nbsp;".equals(positionMatrix[rowIndex-i-numberOfCharsInPattern][columnIndex+i+numberOfCharsInPattern])) return false;
      }
    }

    return true;
  }

  private boolean checkTriple(int rowIndex, int columnIndex, String positionType, String playerChar) {

    if (ROW.equals(positionType)) {
      if (columnIndex >= 23) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex][columnIndex+1])
              && playerChar.equals(positionMatrix[rowIndex][columnIndex+2])) return true;
    }
    if (COLUMN.equals(positionType)) {
      if (rowIndex >= 23) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+1][columnIndex])
              && playerChar.equals(positionMatrix[rowIndex+2][columnIndex])) return true;
    }
    if (FIRST_DIAGONAL.equals(positionType)) {
      if (rowIndex >= 23 || columnIndex >= 23) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+1][columnIndex+1])
              && playerChar.equals(positionMatrix[rowIndex+2][columnIndex+2])) return true;
    }
    if (SECOND_DIAGONAL.equals(positionType)) {
      if (rowIndex <= 1 || columnIndex >=23) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex-1][columnIndex+1])
              && playerChar.equals(positionMatrix[rowIndex-2][columnIndex+2])) return true;
    }

    return false;
  }

  private boolean checkInsideTriple(int rowIndex, int columnIndex, String positionType, String playerChar) {

    if (ROW.equals(positionType)) {
      if (columnIndex >= 23) return false;
      if (columnIndex == 0) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex][columnIndex+1])
              && playerChar.equals(positionMatrix[rowIndex][columnIndex-1])) return true;
    }
    if (COLUMN.equals(positionType)) {
      if (rowIndex >= 23) return false;
      if (rowIndex == 0) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+1][columnIndex])
              && playerChar.equals(positionMatrix[rowIndex-1][columnIndex])) return true;
    }
    if (FIRST_DIAGONAL.equals(positionType)) {
      if (rowIndex >= 23 || columnIndex >= 23) return false;
      if (rowIndex == 0 || columnIndex == 0) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+1][columnIndex+1])
              && playerChar.equals(positionMatrix[rowIndex-1][columnIndex-1])) return true;
    }
    if (SECOND_DIAGONAL.equals(positionType)) {
      if (rowIndex <= 1 || columnIndex >= 23) return false;
      if (rowIndex == 24 || columnIndex == 0) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex-1][columnIndex+1])
              && playerChar.equals(positionMatrix[rowIndex+1][columnIndex-1])) return true;
    }

    return false;
  }

}
