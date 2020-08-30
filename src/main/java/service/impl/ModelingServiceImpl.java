package service.impl;

import dto.CharPosition;
import dto.PositionDto;
import lombok.Getter;
import lombok.Setter;
import service.ModelingService;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class ModelingServiceImpl implements ModelingService {

  PositionDto positionDto = new PositionDto();

  String[][] positionMatrix = new String[25][25];

  ModelingUtilsImpl modelingUtils = new ModelingUtilsImpl();

  private final static String ROW = "row";
  private final static String COLUMN = "column";
  private final static String FIRST_DIAGONAL = "firstDiagonal";
  private final static String SECOND_DIAGONAL = "secondDiagonal";
  private List<String> positionTypes = Arrays.asList(ROW, COLUMN, FIRST_DIAGONAL, SECOND_DIAGONAL);
  
  private final static String EMPTY = "&nbsp;";

  @Override
  public PositionDto describePosition(String[][] positionMatrix) {

    this.positionMatrix = positionMatrix;
    modelingUtils.positionMatrix = positionMatrix;
    positionDto = new PositionDto();
    countPatterns();
    return positionDto;
  }

  @Override
  public PositionDto fastDescribePosition(String[][] positionMatrix, PositionDto previousPositionDto, CharPosition nextMove, String playerChar) {

    String[][] positionMatrixCloned = Arrays.stream(positionMatrix).map(String[]::clone).toArray(String[][]::new);

    PositionDto previousLimitedDto = limitedDescribePosition(positionMatrixCloned, nextMove);
    positionMatrixCloned[nextMove.getRow()][nextMove.getColumn()] = playerChar;
    PositionDto nextLimitedDto = limitedDescribePosition(positionMatrixCloned, nextMove);

    return previousPositionDto.add(nextLimitedDto.substract(previousLimitedDto));
  }

  @Override
  public boolean isStartOfGame(String[][] positionMatrix) {

    int numberOfChars = 0;

    for (int i =0; i<25; i++) {
      for (int j=0; j<25; j++) {
        if ("X".equals(positionMatrix[i][j]) || "O".equals(positionMatrix[i][j])) {
          numberOfChars++;
        }
      }
    }
    return numberOfChars <= 10;
  }

  private PositionDto limitedDescribePosition(String[][] positionMatrix, CharPosition change) {

    String[][] positionMatrixCloned = Arrays.stream(positionMatrix).map(String[]::clone).toArray(String[][]::new);

    this.positionMatrix = positionMatrixCloned;
    modelingUtils.positionMatrix = positionMatrixCloned;
    positionDto = new PositionDto();

    int rowStart  = Math.max((change.getRow() - 6), 0);
    int rowEnd = Math.min(change.getRow()+6, 24);
    int columnStart = Math.max((change.getColumn() - 6), 0);
    int columnEnd = Math.min(change.getColumn()+6, 24);

    CharPosition charPosition;
    for (int rowIndex=rowStart; rowIndex<=rowEnd; rowIndex++) {
      charPosition = new CharPosition(rowIndex, change.getColumn());
      countPatternsForPositionForPositionType(charPosition, COLUMN);
    }
    for (int columnIndex=columnStart; columnIndex<=columnEnd; columnIndex++) {
      charPosition = new CharPosition(change.getRow(), columnIndex);
      countPatternsForPositionForPositionType(charPosition, ROW);
    }
    for (int firstDiagonalIndex=-24; firstDiagonalIndex<25; firstDiagonalIndex++) {
      if (change.getRow() + firstDiagonalIndex >= rowStart && change.getRow() + firstDiagonalIndex <= rowEnd
              && change.getColumn() + firstDiagonalIndex >= columnStart && change.getColumn() + firstDiagonalIndex <= columnEnd) {
        charPosition = new CharPosition(change.getRow() + firstDiagonalIndex, change.getColumn() + firstDiagonalIndex);
        countPatternsForPositionForPositionType(charPosition, FIRST_DIAGONAL);
      }
    }
    for (int secondDiagonalIndex=-24; secondDiagonalIndex<25; secondDiagonalIndex++) {
      if (change.getRow() - secondDiagonalIndex >= rowStart && change.getRow() - secondDiagonalIndex <= rowEnd
              && change.getColumn() + secondDiagonalIndex >= columnStart && change.getColumn() + secondDiagonalIndex <= columnEnd) {
        charPosition = new CharPosition(change.getRow() - secondDiagonalIndex, change.getColumn() + secondDiagonalIndex);
        countPatternsForPositionForPositionType(charPosition, SECOND_DIAGONAL);
      }
    }
    return positionDto;
  }

  private void countPatterns() {
    for (int rowIndex=0; rowIndex<25; rowIndex++) {
      for (int columnIndex = 0; columnIndex < 25; columnIndex++) {
        CharPosition charPosition = new CharPosition(rowIndex, columnIndex);
        countPatternsForPosition(charPosition);
      }
    }
  }

  private void countPatternsForPosition(CharPosition charPosition) {
    positionTypes.forEach(t -> checkDoubleForPositionForPlayerForPositionType(charPosition, "O", t) );
    positionTypes.forEach(t -> checkDisconnectedDoubleForPositionForPlayerForPositionType(charPosition, "O", t) );
    positionTypes.forEach(t -> checkDoubleForPositionForPlayerForPositionType(charPosition, "X", t) );
    positionTypes.forEach(t -> checkDisconnectedDoubleForPositionForPlayerForPositionType(charPosition, "X", t) );
    positionTypes.forEach(t -> checkTripleForPositionForPlayerForPositionType(charPosition, "O", t) );
    positionTypes.forEach(t -> checkDisconnectedTripleForPositionForPlayerForPositionType(charPosition, "O", t) );
    positionTypes.forEach(t -> checkTripleForPositionForPlayerForPositionType(charPosition, "X", t) );
    positionTypes.forEach(t -> checkDisconnectedTripleForPositionForPlayerForPositionType(charPosition, "X", t) );
    positionTypes.forEach(t -> checkQuadrupleForPositionForPlayerForPositionType(charPosition, "O", t) );
    positionTypes.forEach(t -> checkQuadrupleForPositionForPlayerForPositionType(charPosition, "X", t) );
    positionTypes.forEach(t -> checkQuintupleForPositionForPlayerForPositionType(charPosition, "O", t) );
    positionTypes.forEach(t -> checkQuintupleForPositionForPlayerForPositionType(charPosition, "X", t) );
  }

  private void countPatternsForPositionForPositionType(CharPosition charPosition, String positionType) {
    checkDoubleForPositionForPlayerForPositionType(charPosition, "O", positionType);
    checkDisconnectedDoubleForPositionForPlayerForPositionType(charPosition, "O", positionType);
    checkDoubleForPositionForPlayerForPositionType(charPosition, "X", positionType);
    checkDisconnectedDoubleForPositionForPlayerForPositionType(charPosition, "X", positionType);
    checkTripleForPositionForPlayerForPositionType(charPosition, "O", positionType);
    checkDisconnectedTripleForPositionForPlayerForPositionType(charPosition, "O", positionType);
    checkTripleForPositionForPlayerForPositionType(charPosition, "X", positionType);
    checkDisconnectedTripleForPositionForPlayerForPositionType(charPosition, "X", positionType);
    checkQuadrupleForPositionForPlayerForPositionType(charPosition, "O", positionType);
    checkQuadrupleForPositionForPlayerForPositionType(charPosition, "X", positionType);
    checkQuintupleForPositionForPlayerForPositionType(charPosition, "O", positionType);
    checkQuintupleForPositionForPlayerForPositionType(charPosition, "X", positionType);
  }
  
  private void checkDoubleForPositionForPlayerForPositionType(CharPosition charPosition, String playerChar, String positionType) {
    if ( modelingUtils.checkDouble(charPosition, positionType, playerChar)
         && !modelingUtils.checkTriple(charPosition, positionType, playerChar)
         && !modelingUtils.checkDisconnectedTriple(charPosition, positionType, playerChar)
         && !modelingUtils.checkDoubleInsideTriple(charPosition, positionType, playerChar)
         && !modelingUtils.checkDoubleInsideDisconnectedTriple(charPosition, positionType, playerChar)) {
      // double found, now what is type of double
      if ( modelingUtils.checkEmptyFields(charPosition, positionType, 2,3, 3 )) {
        // open double
        positionDto.addOpenDoublesByPlayer(playerChar);
      }
      else if (modelingUtils.checkEmptyFields(charPosition, positionType, 2,2, 2 )) {
        // mostly open double
        positionDto.addMostlyOpenDoublesByPlayer(playerChar);
      }
      else if (modelingUtils.checkEmptyFields(charPosition, positionType, 2,1, 2 ) ||
              modelingUtils.checkEmptyFields(charPosition, positionType, 2,2, 1 ) ||
              modelingUtils.checkEmptyFields(charPosition, positionType, 2,0, 3 ) ||
              modelingUtils.checkEmptyFields(charPosition, positionType, 2,3, 0 )) {
        // half open double
        positionDto.addHalfOpenDoublesByPlayer(playerChar);
      }
    }
  }  
  
  private void checkDisconnectedDoubleForPositionForPlayerForPositionType(CharPosition charPosition, String playerChar, String positionType) {
    if (modelingUtils.checkDisconnectedDouble(charPosition, positionType, playerChar) &&
        !modelingUtils.checkDisconnectedTriple(charPosition, positionType, playerChar) &&
        !modelingUtils.checkDisconnectedDoubleInsideDisconnectedTriple(charPosition, positionType, playerChar) &&
        !modelingUtils.checkDisconnectedDoubleInsideTwiceDisconnectedTriple(charPosition, positionType, playerChar)) {
      // disconnected double found, now what is type of double
      if ( modelingUtils.checkEmptyFields(charPosition, positionType, 3,2, 2 )) {
        // open disconnected double
        positionDto.addOpenDisconnectedDoublesByPlayer(playerChar);
      }
      else if (modelingUtils.checkEmptyFields(charPosition, positionType, 3,1, 2 ) ||
              modelingUtils.checkEmptyFields(charPosition, positionType, 3,2, 1 )) {
        // mostly open disconnected double
        positionDto.addMostlyOpenDisconnectedDoublesByPlayer(playerChar);
      }
      else if (modelingUtils.checkEmptyFields(charPosition, positionType, 3,1, 1 ) ||
              modelingUtils.checkEmptyFields(charPosition, positionType, 3,2, 0 ) ||
              modelingUtils.checkEmptyFields(charPosition, positionType, 3,0, 2 )) {
        // half open disconnected double
        positionDto.addHalfOpenDisconnectedDoublesByPlayer(playerChar);
      }
    }
  }

  private void checkTripleForPositionForPlayerForPositionType(CharPosition charPosition, String playerChar, String positionType) {
    if ( modelingUtils.checkTriple(charPosition, positionType, playerChar) && !modelingUtils.checkQuadruple(charPosition, positionType,playerChar)
         && !modelingUtils.checkDisconnectedQuadruple(charPosition, positionType, playerChar)
         && !modelingUtils.checkTripleInsideQuadruple(charPosition, positionType, playerChar)
         && !modelingUtils.checkTripleInsideDisconnectedQuadruple(charPosition, positionType, playerChar) ) {
      // triple found, now what is its type
      if ( modelingUtils.checkEmptyFields(charPosition, positionType, 3,2, 2 )) {
        // open triple
        positionDto.addOpenTriplesByPlayer(playerChar);
      }
      else if (modelingUtils.checkEmptyFields(charPosition, positionType, 3,1, 1 )) {
        // mostly open triple
        positionDto.addMostlyOpenTriplesByPlayer(playerChar);
      }
      else if (modelingUtils.checkEmptyFields(charPosition, positionType, 3,0, 2 ) ||
              modelingUtils.checkEmptyFields(charPosition, positionType, 3,2, 0 )) {
        // half open triple
        positionDto.addHalfOpenTriplesByPlayer(playerChar);
      }
    }
  }

  private void checkDisconnectedTripleForPositionForPlayerForPositionType(CharPosition charPosition, String playerChar, String positionType) {
    if ( modelingUtils.checkDisconnectedTriple(charPosition, positionType, playerChar) &&
         !modelingUtils.checkDisconnectedQuadruple(charPosition, positionType, playerChar) &&
         !modelingUtils.checkDisconnectedTripleInsideDisconnectedQuadruple(charPosition, positionType, playerChar)) {
      // disconnected triple found, now what is its type
      if ( modelingUtils.checkEmptyFields(charPosition, positionType, 4,1, 1 )) {
        // open disconnected triple
        positionDto.addOpenDisconnectedTriplesByPlayer(playerChar);
      }
      else if (modelingUtils.checkEmptyFields(charPosition, positionType, 4,0, 1 ) ||
              modelingUtils.checkEmptyFields(charPosition, positionType, 4,1, 0 )) {
        // half open disconnected triple
        positionDto.addHalfOpenDisconnectedTriplesByPlayer(playerChar);
      }
    }
    if ( modelingUtils.checkTwiceDisconnectedTriple(charPosition, positionType, playerChar) ) {
      // twice disconnected triple found
      positionDto.addTwiceDisconnectedTriplesByPlayer(playerChar);
    }
  }

  private void checkQuadrupleForPositionForPlayerForPositionType(CharPosition charPosition, String playerChar, String positionType) {
    if ( modelingUtils.checkQuadruple(charPosition, positionType, playerChar) &&
         !modelingUtils.checkQuintuple(charPosition, positionType, playerChar) &&
         !modelingUtils.checkQuadrupleInsideQuintuple(charPosition, positionType, playerChar)) {
      // quadruple found, now what is its type
      if ( modelingUtils.checkEmptyFields(charPosition, positionType, 4,1, 1 )) {
        // open quadruple
        positionDto.addOpenQuadruplesByPlayer(playerChar);
      }
      else if (modelingUtils.checkEmptyFields(charPosition, positionType, 4,0, 1 ) ||
              modelingUtils.checkEmptyFields(charPosition, positionType, 4,1, 0 )) {
        // half open quadruple
        positionDto.addHalfOpenQuadruplesByPlayer(playerChar);
      }
    }
    if (modelingUtils.checkDisconnectedQuadruple(charPosition, positionType, playerChar)) {
      // disconnected quadruple found
      positionDto.addDisconnectedQuadruplesByPlayer(playerChar);
    }
  }

  private void checkQuintupleForPositionForPlayerForPositionType(CharPosition charPosition, String playerChar, String positionType) {
    if ( modelingUtils.checkQuintuple(charPosition, positionType, playerChar) ) {
      // quintuple found
      positionDto.addQuintuplesByPlayer(playerChar);
    }
  }

  public static void main(String[] args) {
    String[][] testPositionMatrix = new String[25][25];
    for (int i=0; i<25; i++) {
      for (int j=0; j<25; j++) {
        testPositionMatrix[i][j] = "&nbsp;";
      }
    }
    testPositionMatrix[12][12] = "X";
    testPositionMatrix[12][13] = "O";
    testPositionMatrix[13][12] = "X";
    testPositionMatrix[11][12] = "O";
    testPositionMatrix[14][12] = "X";
    testPositionMatrix[10][11] = "O";
    testPositionMatrix[13][13] = "X";
    testPositionMatrix[13][14] = "O";
    testPositionMatrix[14][15] = "X";

    ModelingServiceImpl modelingService = new ModelingServiceImpl();

    PositionDto previousPositionDto = modelingService.describePosition(testPositionMatrix);
    PositionDto testedPositionDto = modelingService.fastDescribePosition(testPositionMatrix, previousPositionDto,new CharPosition(9,10), "O");

    testPositionMatrix[9][10] = "O";
    PositionDto expectedPositionDto = modelingService.describePosition(testPositionMatrix);

    System.out.println(expectedPositionDto);
    System.out.println("*************************************************************************************");
    System.out.println(testedPositionDto);
  }
}
