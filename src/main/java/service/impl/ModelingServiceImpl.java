package service.impl;

import lombok.Getter;
import lombok.Setter;
import dto.CharPosition;
import dto.PositionDto;
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
  public void describePosition(String[][] positionMatrix) {

    this.positionMatrix = positionMatrix;
    modelingUtils.positionMatrix = positionMatrix;
    positionDto = new PositionDto();
    countPatterns();
  }

  private void countPatterns() {

    for (int rowIndex=0; rowIndex<25; rowIndex++) {
      for (int columnIndex = 0; columnIndex < 25; columnIndex++) {
        CharPosition charPosition = new CharPosition(rowIndex, columnIndex);
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
    }
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

}
