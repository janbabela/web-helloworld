package service.impl;

import lombok.Getter;
import lombok.Setter;
import model.CurrentPosition;
import model.PositionModel;
import service.ModelingService;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class ModelingServiceImpl implements ModelingService {

  PositionModel positionModel = new PositionModel();

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
    positionModel = new PositionModel();
    countPatterns();
  }

  private void countPatterns() {

    for (int rowIndex=0; rowIndex<25; rowIndex++) {
      for (int columnIndex = 0; columnIndex < 25; columnIndex++) {
        CurrentPosition currentPosition = new CurrentPosition(rowIndex, columnIndex);
        positionTypes.forEach(t -> checkDoubleForPositionForPlayerForPositionType(currentPosition, "O", t) );
        positionTypes.forEach(t -> checkDisconnectedDoubleForPositionForPlayerForPositionType(currentPosition, "O", t) );
        positionTypes.forEach(t -> checkDoubleForPositionForPlayerForPositionType(currentPosition, "X", t) );
        positionTypes.forEach(t -> checkDisconnectedDoubleForPositionForPlayerForPositionType(currentPosition, "X", t) );
        positionTypes.forEach(t -> checkTripleForPositionForPlayerForPositionType(currentPosition, "O", t) );
        positionTypes.forEach(t -> checkDisconnectedTripleForPositionForPlayerForPositionType(currentPosition, "O", t) );
        positionTypes.forEach(t -> checkTripleForPositionForPlayerForPositionType(currentPosition, "X", t) );
        positionTypes.forEach(t -> checkDisconnectedTripleForPositionForPlayerForPositionType(currentPosition, "X", t) );
        positionTypes.forEach(t -> checkQuadrupleForPositionForPlayerForPositionType(currentPosition, "O", t) );
        positionTypes.forEach(t -> checkQuadrupleForPositionForPlayerForPositionType(currentPosition, "X", t) );
        positionTypes.forEach(t -> checkQuintupleForPositionForPlayerForPositionType(currentPosition, "O", t) );
        positionTypes.forEach(t -> checkQuintupleForPositionForPlayerForPositionType(currentPosition, "X", t) );
      }
    }
  }
  
  private void checkDoubleForPositionForPlayerForPositionType(CurrentPosition currentPosition, String playerChar, String positionType) {
    if ( modelingUtils.checkDouble(currentPosition, positionType, playerChar) 
         && !modelingUtils.checkTriple(currentPosition, positionType, playerChar)
         && !modelingUtils.checkDisconnectedTriple(currentPosition, positionType, playerChar)
         && !modelingUtils.checkDoubleInsideTriple(currentPosition, positionType, playerChar)
         && !modelingUtils.checkDoubleInsideDisconnectedTriple(currentPosition, positionType, playerChar)) {
      // double found, now what is type of double
      if ( modelingUtils.checkEmptyFields(currentPosition, positionType, 2,3, 3 )) {
        // open double
        positionModel.addOpenDoublesByPlayer(playerChar);
      }
      else if (modelingUtils.checkEmptyFields(currentPosition, positionType, 2,2, 2 )) {
        // mostly open double
        positionModel.addMostlyOpenDoublesByPlayer(playerChar);
      }
      else if (modelingUtils.checkEmptyFields(currentPosition, positionType, 2,1, 2 ) ||
              modelingUtils.checkEmptyFields(currentPosition, positionType, 2,2, 1 ) ||
              modelingUtils.checkEmptyFields(currentPosition, positionType, 2,0, 3 ) ||
              modelingUtils.checkEmptyFields(currentPosition, positionType, 2,3, 0 )) {
        // half open double
        positionModel.addHalfOpenDoublesByPlayer(playerChar);
      }
    }
  }  
  
  private void checkDisconnectedDoubleForPositionForPlayerForPositionType(CurrentPosition currentPosition, String playerChar, String positionType) {
    if (modelingUtils.checkDisconnectedDouble(currentPosition, positionType, playerChar) &&
        !modelingUtils.checkDisconnectedTriple(currentPosition, positionType, playerChar) &&
        !modelingUtils.checkDisconnectedDoubleInsideDisconnectedTriple(currentPosition, positionType, playerChar) &&
        !modelingUtils.checkDisconnectedDoubleInsideTwiceDisconnectedTriple(currentPosition, positionType, playerChar)) {
      // disconnected double found, now what is type of double
      if ( modelingUtils.checkEmptyFields(currentPosition, positionType, 3,2, 2 )) {
        // open disconnected double
        positionModel.addOpenDisconnectedDoublesByPlayer(playerChar);
      }
      else if (modelingUtils.checkEmptyFields(currentPosition, positionType, 3,1, 2 ) ||
              modelingUtils.checkEmptyFields(currentPosition, positionType, 3,2, 1 )) {
        // mostly open disconnected double
        positionModel.addMostlyOpenDisconnectedDoublesByPlayer(playerChar);
      }
      else if (modelingUtils.checkEmptyFields(currentPosition, positionType, 3,1, 1 ) ||
              modelingUtils.checkEmptyFields(currentPosition, positionType, 3,2, 0 ) ||
              modelingUtils.checkEmptyFields(currentPosition, positionType, 3,0, 2 )) {
        // half open disconnected double
        positionModel.addHalfOpenDisconnectedDoublesByPlayer(playerChar);
      }
    }
  }

  private void checkTripleForPositionForPlayerForPositionType(CurrentPosition currentPosition, String playerChar, String positionType) {
    if ( modelingUtils.checkTriple(currentPosition, positionType, playerChar) && !modelingUtils.checkQuadruple(currentPosition, positionType,playerChar)
         && !modelingUtils.checkDisconnectedQuadruple(currentPosition, positionType, playerChar)
         && !modelingUtils.checkTripleInsideQuadruple(currentPosition, positionType, playerChar)
         && !modelingUtils.checkTripleInsideDisconnectedQuadruple(currentPosition, positionType, playerChar) ) {
      // triple found, now what is its type
      if ( modelingUtils.checkEmptyFields(currentPosition, positionType, 3,2, 2 )) {
        // open triple
        positionModel.addOpenTriplesByPlayer(playerChar);
      }
      else if (modelingUtils.checkEmptyFields(currentPosition, positionType, 3,1, 1 )) {
        // mostly open triple
        positionModel.addMostlyOpenTriplesByPlayer(playerChar);
      }
      else if (modelingUtils.checkEmptyFields(currentPosition, positionType, 3,0, 2 ) ||
              modelingUtils.checkEmptyFields(currentPosition, positionType, 3,2, 0 )) {
        // half open triple
        positionModel.addHalfOpenTriplesByPlayer(playerChar);
      }
    }
  }

  private void checkDisconnectedTripleForPositionForPlayerForPositionType(CurrentPosition currentPosition, String playerChar, String positionType) {
    if ( modelingUtils.checkDisconnectedTriple(currentPosition, positionType, playerChar) &&
         !modelingUtils.checkDisconnectedQuadruple(currentPosition, positionType, playerChar) &&
         !modelingUtils.checkDisconnectedTripleInsideDisconnectedQuadruple(currentPosition, positionType, playerChar)) {
      // disconnected triple found, now what is its type
      if ( modelingUtils.checkEmptyFields(currentPosition, positionType, 4,1, 1 )) {
        // open disconnected triple
        positionModel.addOpenDisconnectedTriplesByPlayer(playerChar);
      }
      else if (modelingUtils.checkEmptyFields(currentPosition, positionType, 4,0, 1 ) ||
              modelingUtils.checkEmptyFields(currentPosition, positionType, 4,1, 0 )) {
        // half open disconnected triple
        positionModel.addHalfOpenDisconnectedTriplesByPlayer(playerChar);
      }
    }
    if ( modelingUtils.checkTwiceDisconnectedTriple(currentPosition, positionType, playerChar) ) {
      // twice disconnected triple found
      positionModel.addTwiceDisconnectedTriplesByPlayer(playerChar);
    }
  }

  private void checkQuadrupleForPositionForPlayerForPositionType(CurrentPosition currentPosition, String playerChar, String positionType) {
    if ( modelingUtils.checkQuadruple(currentPosition, positionType, playerChar) &&
         !modelingUtils.checkQuintuple(currentPosition, positionType, playerChar) &&
         !modelingUtils.checkQuadrupleInsideQuintuple(currentPosition, positionType, playerChar)) {
      // quadruple found, now what is its type
      if ( modelingUtils.checkEmptyFields(currentPosition, positionType, 4,1, 1 )) {
        // open quadruple
        positionModel.addOpenQuadruplesByPlayer(playerChar);
      }
      else if (modelingUtils.checkEmptyFields(currentPosition, positionType, 4,0, 1 ) ||
              modelingUtils.checkEmptyFields(currentPosition, positionType, 4,1, 0 )) {
        // half open quadruple
        positionModel.addHalfOpenQuadruplesByPlayer(playerChar);
      }
    }
    if (modelingUtils.checkDisconnectedQuadruple(currentPosition, positionType, playerChar)) {
      // disconnected quadruple found
      positionModel.addDisconnectedQuadruplesByPlayer(playerChar);
    }
  }

  private void checkQuintupleForPositionForPlayerForPositionType(CurrentPosition currentPosition, String playerChar, String positionType) {
    if ( modelingUtils.checkQuintuple(currentPosition, positionType, playerChar) ) {
      // quintuple found
      positionModel.addQuintuplesByPlayer(playerChar);
    }
  }

}
