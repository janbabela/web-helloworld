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
import java.util.Random;

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

  public ComputerMoveServiceImpl() {

  }

  public ComputerMoveServiceImpl(String playerChar) {
    initialize(playerChar);
  }

  public void initialize(String playerChar) {
    List<PositionModel> positionsHistory = positionHistoryDao.selectAll();

    double[][] xVariable = regressionService.createXVariable(positionsHistory,playerChar);
    double[] yVariable = regressionService.createYVariable(positionsHistory);

    coefficients = regressionService.computeCoefficientLeastSquare(xVariable, yVariable);
  }

  public CharPosition makeMove(String[][] positionMatrix, String playerChar, boolean startOfGame) {

    List<CharPosition> reasonableMoves = findReasonableMoves(positionMatrix, startOfGame);

    CharPosition bestMove = reasonableMoves.get(0);
    String[][] currentPositionMatrix;
    double currentEvaluation;
    evaluation = 0;
    for (CharPosition possibleMove : reasonableMoves) {
      currentPositionMatrix = Arrays.stream(positionMatrix).map(String[]::clone).toArray(String[][]::new);;
      currentPositionMatrix[possibleMove.getRow()][possibleMove.getColumn()] = playerChar;
      modelingService.describePosition(currentPositionMatrix);
      currentEvaluation = evaluatePosition(modelingService.getPositionDto());
      if (currentEvaluation > evaluation) {
        evaluation = currentEvaluation;
        bestMove = possibleMove;
      }
    }

    return bestMove;
  }

  private List<CharPosition> findReasonableMoves(String[][] positionMatrix, boolean startOfGame) {

    List<CharPosition> result = new ArrayList<>();
    for (int i=0; i<25; i++) {
      for (int j=0; j<25; j++) {
        if (!"X".equals(positionMatrix[i][j]) && !"O".equals(positionMatrix[i][j])) {
          if (checkSurroundingCells(positionMatrix,i,j,startOfGame)) {
            result.add(new CharPosition(i,j));
          }
        }
      }
    }
    return result;
  }

  private boolean checkSurroundingCells(String[][] positionMatrix, int row, int column, boolean startOfGame) {

    int threshold = startOfGame ? 1 : 2;
    for (int i=-threshold; i<threshold+1; i++) {
      for (int j=-threshold; j<threshold+1; j++) {
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

  public CharPosition makeRandomMove(String[][] positionMatrix) {

    Random rand = new Random();
    int rand_i = rand.nextInt(25);
    int rand_j = rand.nextInt(25);
    if (!("X".equals(positionMatrix[rand_i][rand_j])) || "O".equals(positionMatrix[rand_i][rand_j])) {
      return new CharPosition(rand_i,rand_j);
    }
    else {
      return makeRandomMove(positionMatrix);
    }
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
    System.out.println(computerMoveService.findReasonableMoves(testPositionMatrix, true));
  }

  private static void testInitialize() {
    ComputerMoveServiceImpl computerMoveService = new ComputerMoveServiceImpl();
    computerMoveService.initialize("O");
    System.out.println(Arrays.toString(computerMoveService.getCoefficients()));
  }

  public static void main(String[] args) {
    testInitialize();
  }

}
