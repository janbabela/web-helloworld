package service.impl;

import dto.CharPosition;

public class ModelingUtilsImpl {

  String[][] positionMatrix = new String[25][25];

  private final static String ROW = "row";
  private final static String COLUMN = "column";
  private final static String FIRST_DIAGONAL = "firstDiagonal";
  private final static String SECOND_DIAGONAL = "secondDiagonal";
  private final static String EMPTY = "&nbsp;";


  boolean checkDouble(CharPosition charPosition, String positionType, String playerChar) {

    int rowIndex = charPosition.getRow();
    int columnIndex = charPosition.getColumn();

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

  boolean checkDisconnectedDouble(CharPosition charPosition, String positionType, String playerChar) {

    int rowIndex = charPosition.getRow();
    int columnIndex = charPosition.getColumn();

    if (ROW.equals(positionType)) {
      if (columnIndex >= 23) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex][columnIndex+2])
              && EMPTY.equals(positionMatrix[rowIndex][columnIndex+1])) return true;
    }
    if (COLUMN.equals(positionType)) {
      if (rowIndex >= 23) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+2][columnIndex])
              && EMPTY.equals(positionMatrix[rowIndex+1][columnIndex])) return true;
    }
    if (FIRST_DIAGONAL.equals(positionType)) {
      if (rowIndex >= 23 || columnIndex >= 23) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+2][columnIndex+2])
              && EMPTY.equals(positionMatrix[rowIndex+1][columnIndex+1])) return true;
    }
    if (SECOND_DIAGONAL.equals(positionType)) {
      if (rowIndex <= 1 || columnIndex >= 23) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex-2][columnIndex+2])
              && EMPTY.equals(positionMatrix[rowIndex-1][columnIndex+1])) return true;
    }

    return false;
  }

  boolean checkEmptyFields(CharPosition charPosition, String positionType, int numberOfCharsInPattern, int emptyFieldsBefore, int emptyFieldsAfter) {

    int rowIndex = charPosition.getRow();
    int columnIndex = charPosition.getColumn();

    if (ROW.equals(positionType)) {
      if (columnIndex < emptyFieldsBefore ||  columnIndex + numberOfCharsInPattern + emptyFieldsAfter > 25) return false;
      for (int i=1; i<emptyFieldsBefore+1; i++) {
        if (!EMPTY.equals(positionMatrix[rowIndex][columnIndex-i])) return false;
      }
      for (int i=0; i<emptyFieldsAfter; i++) {
        if (!EMPTY.equals(positionMatrix[rowIndex][columnIndex+i+numberOfCharsInPattern])) return false;
      }
    }
    if (COLUMN.equals(positionType)) {
      if (rowIndex < emptyFieldsBefore ||  rowIndex + numberOfCharsInPattern + emptyFieldsAfter > 25) return false;
      for (int i=1; i<emptyFieldsBefore+1; i++) {
        if (!EMPTY.equals(positionMatrix[rowIndex-i][columnIndex])) return false;
      }
      for (int i=0; i<emptyFieldsAfter; i++) {
        if (!EMPTY.equals(positionMatrix[rowIndex+i+numberOfCharsInPattern][columnIndex])) return false;
      }
    }
    if (FIRST_DIAGONAL.equals(positionType)) {
      if (columnIndex < emptyFieldsBefore ||  columnIndex + numberOfCharsInPattern + emptyFieldsAfter > 25) return false;
      if (rowIndex < emptyFieldsBefore ||  rowIndex + numberOfCharsInPattern + emptyFieldsAfter > 25) return false;
      for (int i=1; i<emptyFieldsBefore+1; i++) {
        if (!EMPTY.equals(positionMatrix[rowIndex-i][columnIndex-i])) return false;
      }
      for (int i=0; i<emptyFieldsAfter; i++) {
        if (!EMPTY.equals(positionMatrix[rowIndex+i+numberOfCharsInPattern][columnIndex+i+numberOfCharsInPattern])) return false;
      }
    }
    if (SECOND_DIAGONAL.equals(positionType)) {
      if (columnIndex < emptyFieldsBefore ||  columnIndex + numberOfCharsInPattern + emptyFieldsAfter > 25) return false;
      if (rowIndex + emptyFieldsBefore > 24 ||  rowIndex - numberOfCharsInPattern - emptyFieldsAfter + 1 < 0) return false;
      for (int i=1; i<emptyFieldsBefore+1; i++) {
        if (!EMPTY.equals(positionMatrix[rowIndex+i][columnIndex-i])) return false;
      }
      for (int i=0; i<emptyFieldsAfter; i++) {
        if (!EMPTY.equals(positionMatrix[rowIndex-i-numberOfCharsInPattern][columnIndex+i+numberOfCharsInPattern])) return false;
      }
    }

    return true;
  }

  boolean checkTriple(CharPosition charPosition, String positionType, String playerChar) {

    int rowIndex = charPosition.getRow();
    int columnIndex = charPosition.getColumn();

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

  boolean checkDisconnectedTriple(CharPosition charPosition, String positionType, String playerChar) {

    int rowIndex = charPosition.getRow();
    int columnIndex = charPosition.getColumn();

    if (ROW.equals(positionType)) {
      if (columnIndex >= 22) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex][columnIndex+3]) &&
           ( (playerChar.equals(positionMatrix[rowIndex][columnIndex+1]) && EMPTY.equals(positionMatrix[rowIndex][columnIndex+2])) ||
             (playerChar.equals(positionMatrix[rowIndex][columnIndex+2]) && EMPTY.equals(positionMatrix[rowIndex][columnIndex+1])) )) return true;
    }
    if (COLUMN.equals(positionType)) {
      if (rowIndex >= 22) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+3][columnIndex]) &&
           ( (playerChar.equals(positionMatrix[rowIndex+1][columnIndex]) && EMPTY.equals(positionMatrix[rowIndex+2][columnIndex])) ||
             (playerChar.equals(positionMatrix[rowIndex+2][columnIndex]) && EMPTY.equals(positionMatrix[rowIndex+1][columnIndex])) )) return true;
    }
    if (FIRST_DIAGONAL.equals(positionType)) {
      if (rowIndex >= 22 || columnIndex >= 22) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+3][columnIndex+3]) &&
           ( (playerChar.equals(positionMatrix[rowIndex+1][columnIndex+1]) && EMPTY.equals(positionMatrix[rowIndex+2][columnIndex+2])) ||
             (playerChar.equals(positionMatrix[rowIndex+2][columnIndex+2]) && EMPTY.equals(positionMatrix[rowIndex+1][columnIndex+1])) )) return true;
    }
    if (SECOND_DIAGONAL.equals(positionType)) {
      if (rowIndex <= 2 || columnIndex >=22) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex-3][columnIndex+3]) &&
           ( (playerChar.equals(positionMatrix[rowIndex-1][columnIndex+1]) && EMPTY.equals(positionMatrix[rowIndex-2][columnIndex+2])) ||
             (playerChar.equals(positionMatrix[rowIndex-2][columnIndex+2]) && EMPTY.equals(positionMatrix[rowIndex-1][columnIndex+1])) )) return true;
    }

    return false;
  }

  boolean checkTwiceDisconnectedTriple(CharPosition charPosition, String positionType, String playerChar) {

    int rowIndex = charPosition.getRow();
    int columnIndex = charPosition.getColumn();

    if (ROW.equals(positionType)) {
      if (columnIndex >= 21) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex][columnIndex+2]) &&
          playerChar.equals(positionMatrix[rowIndex][columnIndex+4]) && EMPTY.equals(positionMatrix[rowIndex][columnIndex+1]) &&
          EMPTY.equals(positionMatrix[rowIndex][columnIndex+3]) ) return true;
    }
    if (COLUMN.equals(positionType)) {
      if (rowIndex >= 21) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+2][columnIndex]) &&
          playerChar.equals(positionMatrix[rowIndex+4][columnIndex]) && EMPTY.equals(positionMatrix[rowIndex+1][columnIndex]) &&
          EMPTY.equals(positionMatrix[rowIndex+3][columnIndex]) ) return true;
    }
    if (FIRST_DIAGONAL.equals(positionType)) {
      if (rowIndex >= 21 || columnIndex >= 21) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+2][columnIndex+2]) &&
          playerChar.equals(positionMatrix[rowIndex+4][columnIndex+4]) && EMPTY.equals(positionMatrix[rowIndex+1][columnIndex+1]) &&
          EMPTY.equals(positionMatrix[rowIndex+3][columnIndex+3]) ) return true;
    }
    if (SECOND_DIAGONAL.equals(positionType)) {
      if (rowIndex <= 3 || columnIndex >=21) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex-2][columnIndex+2]) &&
          playerChar.equals(positionMatrix[rowIndex-4][columnIndex+4]) && EMPTY.equals(positionMatrix[rowIndex-1][columnIndex+1]) &&
          EMPTY.equals(positionMatrix[rowIndex-3][columnIndex+3]) ) return true;
    }

    return false;
  }

  boolean checkDoubleInsideTriple(CharPosition charPosition, String positionType, String playerChar) {

    int rowIndex = charPosition.getRow();
    int columnIndex = charPosition.getColumn();

    if (ROW.equals(positionType)) {
      if (columnIndex == 24 || columnIndex ==0) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex][columnIndex+1])
              && playerChar.equals(positionMatrix[rowIndex][columnIndex-1])) return true;
    }
    if (COLUMN.equals(positionType)) {
      if (rowIndex == 24 || rowIndex == 0) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+1][columnIndex])
              && playerChar.equals(positionMatrix[rowIndex-1][columnIndex])) return true;
    }
    if (FIRST_DIAGONAL.equals(positionType)) {
      if (rowIndex == 24 || columnIndex == 24 || rowIndex == 0 || columnIndex == 0) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+1][columnIndex+1])
              && playerChar.equals(positionMatrix[rowIndex-1][columnIndex-1])) return true;
    }
    if (SECOND_DIAGONAL.equals(positionType)) {
      if (rowIndex == 0 || columnIndex == 24 || rowIndex == 24 || columnIndex == 0) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex-1][columnIndex+1])
              && playerChar.equals(positionMatrix[rowIndex+1][columnIndex-1])) return true;
    }
    return false;
  }

  boolean checkDoubleInsideDisconnectedTriple(CharPosition charPosition, String positionType, String playerChar) {

    int rowIndex = charPosition.getRow();
    int columnIndex = charPosition.getColumn();

    if (ROW.equals(positionType)) {
      if (columnIndex == 24 || columnIndex <= 1) return false;
      if ( playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex][columnIndex+1])
          && playerChar.equals(positionMatrix[rowIndex][columnIndex-2]) && EMPTY.equals(positionMatrix[rowIndex][columnIndex-1]) ) return true;
    }
    if (COLUMN.equals(positionType)) {
      if (rowIndex == 24 || rowIndex <= 1) return false;
      if ( playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+1][columnIndex])
          && playerChar.equals(positionMatrix[rowIndex-2][columnIndex]) && EMPTY.equals(positionMatrix[rowIndex-1][columnIndex])) return true;
    }
    if (FIRST_DIAGONAL.equals(positionType)) {
      if (rowIndex == 24 || columnIndex == 24 || rowIndex <= 1 || columnIndex <= 1) return false;
      if ( playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+1][columnIndex+1])
          && playerChar.equals(positionMatrix[rowIndex-2][columnIndex-2]) && EMPTY.equals(positionMatrix[rowIndex-1][columnIndex-1])) return true;
    }
    if (SECOND_DIAGONAL.equals(positionType)) {
      if (rowIndex == 0 || columnIndex == 24 || rowIndex >= 23 || columnIndex <= 1) return false;
      if ( playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex-1][columnIndex+1])
          && playerChar.equals(positionMatrix[rowIndex+2][columnIndex-2]) && EMPTY.equals(positionMatrix[rowIndex+1][columnIndex-1])) return true;
    }

    return false;
  }

  boolean checkDisconnectedDoubleInsideDisconnectedTriple(CharPosition charPosition, String positionType, String playerChar) {

    int rowIndex = charPosition.getRow();
    int columnIndex = charPosition.getColumn();

    if (ROW.equals(positionType)) {
      if (columnIndex >= 23 || columnIndex == 0) return false;
      if ( playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex][columnIndex+2])
              && playerChar.equals(positionMatrix[rowIndex][columnIndex-1]) && EMPTY.equals(positionMatrix[rowIndex][columnIndex+1]) ) return true;
    }
    if (COLUMN.equals(positionType)) {
      if (rowIndex >= 23 || rowIndex == 0) return false;
      if ( playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+2][columnIndex])
              && playerChar.equals(positionMatrix[rowIndex-1][columnIndex]) && EMPTY.equals(positionMatrix[rowIndex+1][columnIndex])) return true;
    }
    if (FIRST_DIAGONAL.equals(positionType)) {
      if (rowIndex >= 23 || columnIndex >= 23 || rowIndex == 0 || columnIndex == 0) return false;
      if ( playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+2][columnIndex+2])
              && playerChar.equals(positionMatrix[rowIndex-1][columnIndex-1]) && EMPTY.equals(positionMatrix[rowIndex+1][columnIndex+1])) return true;
    }
    if (SECOND_DIAGONAL.equals(positionType)) {
      if (rowIndex <= 1 || columnIndex >=23 || rowIndex == 24 || columnIndex == 0) return false;
      if ( playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex-2][columnIndex+2])
              && playerChar.equals(positionMatrix[rowIndex+1][columnIndex-1]) && EMPTY.equals(positionMatrix[rowIndex-1][columnIndex+1])) return true;
    }

    return false;
  }

  boolean checkDisconnectedDoubleInsideTwiceDisconnectedTriple(CharPosition charPosition, String positionType, String playerChar) {

    int rowIndex = charPosition.getRow();
    int columnIndex = charPosition.getColumn();

    if (ROW.equals(positionType)) {
      if (columnIndex >= 23 || columnIndex <= 1) return false;
      if ( playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex][columnIndex+2])
              && playerChar.equals(positionMatrix[rowIndex][columnIndex-2]) && EMPTY.equals(positionMatrix[rowIndex][columnIndex+1])
              && EMPTY.equals(positionMatrix[rowIndex][columnIndex-1])) return true;
    }
    if (COLUMN.equals(positionType)) {
      if (rowIndex >= 23 || rowIndex <= 1) return false;
      if ( playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+2][columnIndex])
              && playerChar.equals(positionMatrix[rowIndex-2][columnIndex]) && EMPTY.equals(positionMatrix[rowIndex+1][columnIndex])
              && EMPTY.equals(positionMatrix[rowIndex+-1][columnIndex])) return true;
    }
    if (FIRST_DIAGONAL.equals(positionType)) {
      if (rowIndex >= 23 || columnIndex >= 23 || rowIndex <= 1 || columnIndex <= 1) return false;
      if ( playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+2][columnIndex+2])
              && playerChar.equals(positionMatrix[rowIndex-2][columnIndex-2]) && EMPTY.equals(positionMatrix[rowIndex+1][columnIndex+1])
              && EMPTY.equals(positionMatrix[rowIndex-1][columnIndex-1])) return true;
    }
    if (SECOND_DIAGONAL.equals(positionType)) {
      if (rowIndex <= 1 || columnIndex >=23 || rowIndex >= 23 || columnIndex <= 1) return false;
      if ( playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex-2][columnIndex+2])
              && playerChar.equals(positionMatrix[rowIndex+2][columnIndex-2]) && EMPTY.equals(positionMatrix[rowIndex-1][columnIndex+1])
              && EMPTY.equals(positionMatrix[rowIndex+1][columnIndex-1])) return true;
    }

    return false;
  }

  boolean checkQuadruple(CharPosition charPosition, String positionType, String playerChar) {

    int rowIndex = charPosition.getRow();
    int columnIndex = charPosition.getColumn();

    if (ROW.equals(positionType)) {
      if (columnIndex >= 22) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex][columnIndex+1]) &&
              playerChar.equals(positionMatrix[rowIndex][columnIndex+2]) && playerChar.equals(positionMatrix[rowIndex][columnIndex+3])) return true;
    }
    if (COLUMN.equals(positionType)) {
      if (rowIndex >= 22) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+1][columnIndex]) &&
              playerChar.equals(positionMatrix[rowIndex+2][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+3][columnIndex])) return true;
    }
    if (FIRST_DIAGONAL.equals(positionType)) {
      if (rowIndex >= 22 || columnIndex >= 22) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+1][columnIndex+1]) &&
              playerChar.equals(positionMatrix[rowIndex+2][columnIndex+2]) && playerChar.equals(positionMatrix[rowIndex+3][columnIndex+3])) return true;
    }
    if (SECOND_DIAGONAL.equals(positionType)) {
      if (rowIndex <= 2 || columnIndex >= 22) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex-1][columnIndex+1]) &&
              playerChar.equals(positionMatrix[rowIndex-2][columnIndex+2]) && playerChar.equals(positionMatrix[rowIndex-3][columnIndex+3])) return true;
    }

    return false;
  }

  boolean checkDisconnectedQuadruple(CharPosition charPosition, String positionType, String playerChar) {

    int rowIndex = charPosition.getRow();
    int columnIndex = charPosition.getColumn();

    if (ROW.equals(positionType)) {
      if (columnIndex >= 21) return false;
      if ( playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex][columnIndex+4]) &&
         ( (  playerChar.equals(positionMatrix[rowIndex][columnIndex+1]) && playerChar.equals(positionMatrix[rowIndex][columnIndex+2]) &&
              EMPTY.equals(positionMatrix[rowIndex][columnIndex+3]) ) ||
           (  playerChar.equals(positionMatrix[rowIndex][columnIndex+1]) && playerChar.equals(positionMatrix[rowIndex][columnIndex+3]) &&
              EMPTY.equals(positionMatrix[rowIndex][columnIndex+2]) ) ||
           (  playerChar.equals(positionMatrix[rowIndex][columnIndex+2]) && playerChar.equals(positionMatrix[rowIndex][columnIndex+3]) &&
                   EMPTY.equals(positionMatrix[rowIndex][columnIndex+1])
           ) ) ) return true;
    }
    if (COLUMN.equals(positionType)) {
      if (rowIndex >= 21) return false;
      if ( playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+4][columnIndex]) &&
              ( (  playerChar.equals(positionMatrix[rowIndex+1][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+2][columnIndex]) &&
                   EMPTY.equals(positionMatrix[rowIndex+3][columnIndex]) ) ||
                (  playerChar.equals(positionMatrix[rowIndex+1][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+3][columnIndex]) &&
                   EMPTY.equals(positionMatrix[rowIndex+2][columnIndex]) ) ||
                (  playerChar.equals(positionMatrix[rowIndex+2][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+3][columnIndex]) &&
                   EMPTY.equals(positionMatrix[rowIndex+1][columnIndex])
                ) ) ) return true;
    }
    if (FIRST_DIAGONAL.equals(positionType)) {
      if (rowIndex >= 21 || columnIndex >= 21) return false;
      if ( playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+4][columnIndex+4]) &&
              ( (  playerChar.equals(positionMatrix[rowIndex+1][columnIndex+1]) && playerChar.equals(positionMatrix[rowIndex+2][columnIndex+2]) &&
                   EMPTY.equals(positionMatrix[rowIndex+3][columnIndex+3]) ) ||
                (  playerChar.equals(positionMatrix[rowIndex+1][columnIndex+1]) && playerChar.equals(positionMatrix[rowIndex+3][columnIndex+3]) &&
                   EMPTY.equals(positionMatrix[rowIndex+2][columnIndex+2]) ) ||
                (  playerChar.equals(positionMatrix[rowIndex+2][columnIndex+2]) && playerChar.equals(positionMatrix[rowIndex+3][columnIndex+3]) &&
                   EMPTY.equals(positionMatrix[rowIndex+1][columnIndex+1])
                ) ) ) return true;
    }
    if (SECOND_DIAGONAL.equals(positionType)) {
      if (rowIndex <= 3 || columnIndex >= 21) return false;
      if ( playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex-4][columnIndex+4]) &&
              ( (  playerChar.equals(positionMatrix[rowIndex-1][columnIndex+1]) && playerChar.equals(positionMatrix[rowIndex-2][columnIndex+2]) &&
                   EMPTY.equals(positionMatrix[rowIndex-3][columnIndex+3]) ) ||
                (  playerChar.equals(positionMatrix[rowIndex-1][columnIndex+1]) && playerChar.equals(positionMatrix[rowIndex-3][columnIndex+3]) &&
                   EMPTY.equals(positionMatrix[rowIndex-2][columnIndex+2]) ) ||
                (  playerChar.equals(positionMatrix[rowIndex-2][columnIndex+2]) && playerChar.equals(positionMatrix[rowIndex-3][columnIndex+3]) &&
                   EMPTY.equals(positionMatrix[rowIndex-1][columnIndex+1])
                ) ) ) return true;
    }

    return false;
  }

  boolean checkTripleInsideQuadruple(CharPosition charPosition, String positionType, String playerChar) {

    int rowIndex = charPosition.getRow();
    int columnIndex = charPosition.getColumn();

    if (ROW.equals(positionType)) {
      if (columnIndex >= 23 || columnIndex ==0) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex][columnIndex+1])
          && playerChar.equals(positionMatrix[rowIndex][columnIndex+2]) && playerChar.equals(positionMatrix[rowIndex][columnIndex-1])) return true;
    }
    if (COLUMN.equals(positionType)) {
      if (rowIndex >= 23 || rowIndex == 0) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+1][columnIndex])
          && playerChar.equals(positionMatrix[rowIndex+2][columnIndex]) && playerChar.equals(positionMatrix[rowIndex-1][columnIndex])) return true;
    }
    if (FIRST_DIAGONAL.equals(positionType)) {
      if (rowIndex >= 23 || columnIndex >= 23 || rowIndex == 0 || columnIndex == 0) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+1][columnIndex+1])
          && playerChar.equals(positionMatrix[rowIndex+2][columnIndex+2]) && playerChar.equals(positionMatrix[rowIndex-1][columnIndex-1])) return true;
    }
    if (SECOND_DIAGONAL.equals(positionType)) {
      if (rowIndex <= 1 || columnIndex >= 23 || rowIndex == 24 || columnIndex == 0) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex-1][columnIndex+1])
          && playerChar.equals(positionMatrix[rowIndex-2][columnIndex+2]) && playerChar.equals(positionMatrix[rowIndex+1][columnIndex-1])) return true;
    }
    return false;
  }

  boolean checkTripleInsideDisconnectedQuadruple(CharPosition charPosition, String positionType, String playerChar) {

    int rowIndex = charPosition.getRow();
    int columnIndex = charPosition.getColumn();

    if (ROW.equals(positionType)) {
      if (columnIndex >= 23 || columnIndex <=1) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex][columnIndex+1])
              && playerChar.equals(positionMatrix[rowIndex][columnIndex+2]) && playerChar.equals(positionMatrix[rowIndex][columnIndex-2])
              && EMPTY.equals(positionMatrix[rowIndex][columnIndex-1])) return true;
    }
    if (COLUMN.equals(positionType)) {
      if (rowIndex >= 23 || rowIndex <= 1) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+1][columnIndex])
              && playerChar.equals(positionMatrix[rowIndex+2][columnIndex]) && playerChar.equals(positionMatrix[rowIndex-2][columnIndex])
              && EMPTY.equals(positionMatrix[rowIndex-1][columnIndex])) return true;
    }
    if (FIRST_DIAGONAL.equals(positionType)) {
      if (rowIndex >= 23 || columnIndex >= 23 || rowIndex <= 1 || columnIndex <= 1) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+1][columnIndex+1])
              && playerChar.equals(positionMatrix[rowIndex+2][columnIndex+2]) && playerChar.equals(positionMatrix[rowIndex-2][columnIndex-2])
              && EMPTY.equals(positionMatrix[rowIndex-1][columnIndex-1])) return true;
    }
    if (SECOND_DIAGONAL.equals(positionType)) {
      if (rowIndex <= 1 || columnIndex >= 23 || rowIndex >= 23 || columnIndex <= 1) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex-1][columnIndex+1])
              && playerChar.equals(positionMatrix[rowIndex-2][columnIndex+2]) && playerChar.equals(positionMatrix[rowIndex+2][columnIndex-2])
              && EMPTY.equals(positionMatrix[rowIndex+1][columnIndex-1])) return true;
    }
    return false;
  }

  boolean checkDisconnectedTripleInsideDisconnectedQuadruple(CharPosition charPosition, String positionType, String playerChar) {

    int rowIndex = charPosition.getRow();
    int columnIndex = charPosition.getColumn();

    if (ROW.equals(positionType)) {
      if (columnIndex >= 22 || columnIndex ==0) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex][columnIndex+3])
           && playerChar.equals(positionMatrix[rowIndex][columnIndex-1]) &&
           ( ( playerChar.equals(positionMatrix[rowIndex][columnIndex+1]) && EMPTY.equals(positionMatrix[rowIndex][columnIndex+2]) ) ||
             ( playerChar.equals(positionMatrix[rowIndex][columnIndex+2]) && EMPTY.equals(positionMatrix[rowIndex][columnIndex+1]) ) )
         ) return true;
    }
    if (COLUMN.equals(positionType)) {
      if (rowIndex >= 22 || rowIndex == 0) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+3][columnIndex])
              && playerChar.equals(positionMatrix[rowIndex-1][columnIndex]) &&
              ( ( playerChar.equals(positionMatrix[rowIndex+1][columnIndex]) && EMPTY.equals(positionMatrix[rowIndex+2][columnIndex]) ) ||
                ( playerChar.equals(positionMatrix[rowIndex+2][columnIndex]) && EMPTY.equals(positionMatrix[rowIndex+1][columnIndex]) ) )
      ) return true;
    }
    if (FIRST_DIAGONAL.equals(positionType)) {
      if (rowIndex >= 22 || columnIndex >= 22 || rowIndex == 0 || columnIndex == 0) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+3][columnIndex+3])
              && playerChar.equals(positionMatrix[rowIndex-1][columnIndex-1]) &&
              ( ( playerChar.equals(positionMatrix[rowIndex+1][columnIndex+1]) && EMPTY.equals(positionMatrix[rowIndex+2][columnIndex+2]) ) ||
                ( playerChar.equals(positionMatrix[rowIndex+2][columnIndex+2]) && EMPTY.equals(positionMatrix[rowIndex+1][columnIndex+1]) ) )
      ) return true;
    }
    if (SECOND_DIAGONAL.equals(positionType)) {
      if (rowIndex <= 2 || columnIndex >= 22 || rowIndex == 24 || columnIndex == 0) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex-3][columnIndex+3])
              && playerChar.equals(positionMatrix[rowIndex+1][columnIndex-1]) &&
              ( ( playerChar.equals(positionMatrix[rowIndex-1][columnIndex+1]) && EMPTY.equals(positionMatrix[rowIndex-2][columnIndex+2]) ) ||
                ( playerChar.equals(positionMatrix[rowIndex-2][columnIndex+2]) && EMPTY.equals(positionMatrix[rowIndex-1][columnIndex+1]) ) )
      ) return true;
    }
    return false;
  }

  boolean checkQuintuple(CharPosition charPosition, String positionType, String playerChar) {

    int rowIndex = charPosition.getRow();
    int columnIndex = charPosition.getColumn();

    if (ROW.equals(positionType)) {
      if (columnIndex >= 21) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex][columnIndex+1]) &&
              playerChar.equals(positionMatrix[rowIndex][columnIndex+2]) && playerChar.equals(positionMatrix[rowIndex][columnIndex+3])
              && playerChar.equals(positionMatrix[rowIndex][columnIndex+4])) return true;
    }
    if (COLUMN.equals(positionType)) {
      if (rowIndex >= 21) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+1][columnIndex]) &&
              playerChar.equals(positionMatrix[rowIndex+2][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+3][columnIndex])
              && playerChar.equals(positionMatrix[rowIndex+4][columnIndex])) return true;
    }
    if (FIRST_DIAGONAL.equals(positionType)) {
      if (rowIndex >= 21 || columnIndex >= 21) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+1][columnIndex+1]) &&
              playerChar.equals(positionMatrix[rowIndex+2][columnIndex+2]) && playerChar.equals(positionMatrix[rowIndex+3][columnIndex+3])
              && playerChar.equals(positionMatrix[rowIndex+4][columnIndex+4])) return true;
    }
    if (SECOND_DIAGONAL.equals(positionType)) {
      if (rowIndex <= 3 || columnIndex >= 21) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex-1][columnIndex+1]) &&
              playerChar.equals(positionMatrix[rowIndex-2][columnIndex+2]) && playerChar.equals(positionMatrix[rowIndex-3][columnIndex+3])
              && playerChar.equals(positionMatrix[rowIndex-4][columnIndex+4])) return true;
    }

    return false;
  }

  boolean checkQuadrupleInsideQuintuple(CharPosition charPosition, String positionType, String playerChar) {

    int rowIndex = charPosition.getRow();
    int columnIndex = charPosition.getColumn();

    if (ROW.equals(positionType)) {
      if (columnIndex >= 22 || columnIndex ==0) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex][columnIndex+1]) &&
              playerChar.equals(positionMatrix[rowIndex][columnIndex+2]) && playerChar.equals(positionMatrix[rowIndex][columnIndex+3])
              && playerChar.equals(positionMatrix[rowIndex][columnIndex-1])) return true;
    }
    if (COLUMN.equals(positionType)) {
      if (rowIndex >= 22 || rowIndex ==0) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+1][columnIndex]) &&
              playerChar.equals(positionMatrix[rowIndex+2][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+3][columnIndex])
              && playerChar.equals(positionMatrix[rowIndex-1][columnIndex])) return true;
    }
    if (FIRST_DIAGONAL.equals(positionType)) {
      if (rowIndex >= 22 || columnIndex >= 22 || rowIndex == 0 || columnIndex == 0) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex+1][columnIndex+1]) &&
              playerChar.equals(positionMatrix[rowIndex+2][columnIndex+2]) && playerChar.equals(positionMatrix[rowIndex+3][columnIndex+3])
              && playerChar.equals(positionMatrix[rowIndex-1][columnIndex-1])) return true;
    }
    if (SECOND_DIAGONAL.equals(positionType)) {
      if (rowIndex <= 2 || columnIndex >= 22 || rowIndex == 24 || columnIndex == 0) return false;
      if (playerChar.equals(positionMatrix[rowIndex][columnIndex]) && playerChar.equals(positionMatrix[rowIndex-1][columnIndex+1]) &&
              playerChar.equals(positionMatrix[rowIndex-2][columnIndex+2]) && playerChar.equals(positionMatrix[rowIndex-3][columnIndex+3])
              && playerChar.equals(positionMatrix[rowIndex+1][columnIndex-1])) return true;
    }

    return false;
  }

}
