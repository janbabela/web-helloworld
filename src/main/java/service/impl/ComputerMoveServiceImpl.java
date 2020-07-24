package service.impl;

import assembler.PositionAssembler;
import dao.PositionHistoryDao;
import dao.impl.PositionsHistoryDaoImpl;
import dto.CharPosition;
import dto.PositionDto;
import lombok.Getter;
import lombok.Setter;
import model.PositionModel;
import org.apache.commons.math3.linear.MatrixUtils;
import service.ComputerMoveService;
import service.ModelingService;
import service.RegressionService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComputerMoveServiceImpl implements ComputerMoveService {

  @Getter
  @Setter
  private double[] coefficients;

  @Getter
  @Setter
  private double evaluation;

  private final PositionAssembler assembler = new PositionAssembler();

  private final ModelingService modelingService = new ModelingServiceImpl();

  private final PositionHistoryDao positionHistoryDao = new PositionsHistoryDaoImpl();

  private final RegressionService regressionService = new RegressionServiceImpl();

  public void initialize(String playerChar) {
    List<PositionModel> positionsHistory = positionHistoryDao.selectAll();

    double[][] xVariable = regressionService.createXVariable(positionsHistory,playerChar);
    double[] yVariable = regressionService.createYVariable(positionsHistory);

    coefficients = regressionService.computeCoefficientLeastSquare(xVariable, yVariable);
  }

  public CharPosition makeMove(String[][] positionMatrix, String playerChar) {

    List<CharPosition> reasonableMoves = findReasonableMoves(positionMatrix);

    CharPosition bestMove = new CharPosition(12,12);
    double currentEvaluation;
    evaluation = 0;
    for (CharPosition possibleMove : reasonableMoves) {
      positionMatrix[possibleMove.getRow()][possibleMove.getColumn()] = playerChar;
      modelingService.describePosition(positionMatrix);
      currentEvaluation = evaluatePosition(modelingService.getPositionDto());
      if (currentEvaluation > evaluation) {
        evaluation = currentEvaluation;
        bestMove = possibleMove;
      }
    }

    return bestMove;
  }

  private List<CharPosition> findReasonableMoves(String[][] positionMatrix) {

    List<CharPosition> result = new ArrayList<>();
    for (int i=0; i<25; i++) {
      for (int j=0; j<25; j++) {
        if (!"X".equals(positionMatrix[i][j]) && !"O".equals(positionMatrix[i][j])) {
          if (checkSurroundingCells(positionMatrix,i,j)) {
            result.add(new CharPosition(i,j));
          }
        }
      }
    }
    return result;
  }

  private boolean checkSurroundingCells(String[][] positionMatrix, int row, int column) {
    for (int i=-2; i<3; i++) {
      for (int j=-2; j<3; j++) {
        if (row+i>=0 && column+j>=0 && row+i<25 && column+j<25 && ("X".equals(positionMatrix[row+i][column+j]) || "O".equals(positionMatrix[row+i][column+j]))) {
          return true;
        }
      }
    }
    return false;
  }

  private double evaluatePosition(PositionDto positionDto) {

    double[] positionDescription = Arrays.stream(assembler.extractPositionModel(positionDto).getPositionDescription()).asDoubleStream().toArray();

    double dotProduct = MatrixUtils.createRealVector(coefficients).dotProduct(MatrixUtils.createRealVector(positionDescription));

    return 1/(1+Math.exp(-dotProduct*Math.log(2)));
  }


  private static void testReasonableMoves() {
    String[][] testPositionMatrix = new String[25][25];
    for (int i = 0; i<25; i++) {
      for (int j=0; j<25; j++) {
        testPositionMatrix[i][j] = "&nbsp;";
      }
    }
    testPositionMatrix[1][1]= "X";
    ComputerMoveServiceImpl computerMoveService = new ComputerMoveServiceImpl();
    System.out.println(computerMoveService.findReasonableMoves(testPositionMatrix));
  }


  public static void main(String[] args) {
    ComputerMoveServiceImpl computerMoveService = new ComputerMoveServiceImpl();
    computerMoveService.initialize("O");
    System.out.println(Arrays.toString(computerMoveService.getCoefficients()));
  }

}
