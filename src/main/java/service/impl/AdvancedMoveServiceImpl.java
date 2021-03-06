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
import service.AdvancedMoveService;
import service.ModelingService;
import service.RegressionService;

import java.util.Arrays;
import java.util.List;

public class AdvancedMoveServiceImpl implements AdvancedMoveService {

  private final static String EMPTY = "&nbsp;";

  @Getter
  @Setter
  private double[] coefficients;

  @Getter
  @Setter
  private double[] opponetCoefficients;

  @Getter
  @Setter
  private double evaluation;

  private final PositionAssembler assembler = new PositionAssembler();

  private final ModelingService modelingService = new ModelingServiceImpl();

  private final PositionHistoryDao positionHistoryDao = new PositionsHistoryDaoImpl();

  private final RegressionService regressionService = new RegressionServiceImpl();

  public AdvancedMoveServiceImpl() {}

  public AdvancedMoveServiceImpl(String playerChar) {
    initialize(playerChar);
  }

  private void initialize(String playerChar) {
    List<PositionModel> positionsHistory = positionHistoryDao.selectAll();

    double[][] xVariable = regressionService.createXVariable(positionsHistory,playerChar);
    double[][] xOpponentVariable = regressionService.createXVariable(positionsHistory,opponentChar(playerChar));
    double[] yVariable = regressionService.createYVariable(positionsHistory);

    coefficients = regressionService.computeCoefficientLeastSquare(xVariable, yVariable);
    opponetCoefficients = regressionService.computeCoefficientLeastSquare(xOpponentVariable,yVariable);
  }

  public CharPosition findBestMoveTwoSteps(String[][] positionMatrix, String playerChar, boolean startOfGame) {

    List<CharPosition> reasonableMoves = MoveUtilsImpl.findReasonableMoves(positionMatrix, startOfGame);

    PositionDto previousPositionDto = modelingService.describePosition(positionMatrix);
    CharPosition possibleWinning = checkWinning(reasonableMoves,positionMatrix,previousPositionDto,playerChar);
    if (!possibleWinning.equals(new CharPosition(-1,-1))) {
      return possibleWinning;
    }

    CharPosition bestMove = reasonableMoves.get(0);
    String[][] currentPositionMatrix;
    double currentEvaluation;

    CharPosition[] worstOpponentMove = findWorstMoveOneStep(positionMatrix, previousPositionDto, opponentChar(playerChar),true);
    if (checkStopLoosing(positionMatrix, worstOpponentMove, previousPositionDto, playerChar)) {
      return worstOpponentMove[0];
    }

    double noMoveEvaluation = computeNoMoveEvaluation(positionMatrix, worstOpponentMove, previousPositionDto, playerChar);
    evaluation = noMoveEvaluation;

    for (CharPosition possibleMove : reasonableMoves) {

      PositionDto currentPositionDto = modelingService.fastDescribePosition(positionMatrix, previousPositionDto, possibleMove, playerChar);

      if (currentPositionDto.equals(previousPositionDto)) {
        //if position is the same as position without move we can easily evaluate and save time
        if (noMoveEvaluation == evaluation) {
          bestMove = possibleMove;
        }
      }
      else {
        currentPositionMatrix = Arrays.stream(positionMatrix).map(String[]::clone).toArray(String[][]::new);
        currentPositionMatrix[possibleMove.getRow()][possibleMove.getColumn()] = playerChar;

        worstOpponentMove = findWorstMoveOneStep(currentPositionMatrix, currentPositionDto, opponentChar(playerChar), true);

        PositionDto oneStepPositionDto = modelingService.fastDescribePosition(currentPositionMatrix, currentPositionDto, worstOpponentMove[0], opponentChar(playerChar));

        currentPositionMatrix[worstOpponentMove[0].getRow()][worstOpponentMove[0].getColumn()] = opponentChar(playerChar);
        PositionDto twoStepsPositionDto = modelingService.fastDescribePosition(currentPositionMatrix, oneStepPositionDto, worstOpponentMove[1], playerChar);

        currentEvaluation = evaluatePosition(twoStepsPositionDto);
        if (currentEvaluation > evaluation) {
          evaluation = currentEvaluation;
          bestMove = possibleMove;
        }
      }
    }
    return bestMove;
  }

  private CharPosition[] findWorstMoveOneStep(String[][] positionMatrix, PositionDto previousPositionDto, String playerChar, boolean startOfGame) {

    List<CharPosition> reasonableMoves = MoveUtilsImpl.findReasonableMoves(positionMatrix, startOfGame);

    CharPosition[] result = new CharPosition[2];

    CharPosition worstMove;
    String[][] currentPositionMatrix;
    double currentEvaluation;
    double worstEvaluation = 1;

    PositionDto nextPositionDto;

    for (CharPosition possibleMove : reasonableMoves) {

      PositionDto currentPositionDto = modelingService.fastDescribePosition(positionMatrix, previousPositionDto, possibleMove, playerChar);
      if (checkWinning(currentPositionDto, playerChar)) {
        evaluation = -1;
        result[0] = possibleMove;
        result[1] = new CharPosition(-1,-1);
        return result;
      }

      currentPositionMatrix = Arrays.stream(positionMatrix).map(String[]::clone).toArray(String[][]::new);
      currentPositionMatrix[possibleMove.getRow()][possibleMove.getColumn()] = playerChar;

      CharPosition bestOpponentMove = findBestMove(currentPositionMatrix, currentPositionDto, opponentChar(playerChar),startOfGame);
      nextPositionDto = modelingService.fastDescribePosition(currentPositionMatrix, currentPositionDto, bestOpponentMove, opponentChar(playerChar));

      currentEvaluation = evaluatePosition(nextPositionDto);
      if (currentEvaluation < worstEvaluation) {
        worstEvaluation = currentEvaluation;
        worstMove = possibleMove;
        result[0] = worstMove;
        result[1] = bestOpponentMove;
      }
    }
    return result;
  }

  private CharPosition findBestMove(String[][] positionMatrix, PositionDto previousPositionDto, String playerChar, boolean startOfGame) {

    List<CharPosition> reasonableMoves = MoveUtilsImpl.findReasonableMoves(positionMatrix, startOfGame);

    CharPosition bestMove = reasonableMoves.get(0);
    double currentEvaluation;
    double bestEvaluation = 0;
    for (CharPosition possibleMove : reasonableMoves) {
      currentEvaluation = evaluatePosition(modelingService.fastDescribePosition(positionMatrix, previousPositionDto, possibleMove, playerChar));
      if (currentEvaluation > bestEvaluation) {
        bestEvaluation = currentEvaluation;
        bestMove = possibleMove;
      }
    }
    return bestMove;
  }

  public CharPosition findBestMoveOneStep(String[][] positionMatrix, String playerChar, boolean startOfGame) {

    List<CharPosition> reasonableMoves = MoveUtilsImpl.findReasonableMoves(positionMatrix, startOfGame);

    CharPosition bestMove = reasonableMoves.get(0);
    String[][] currentPositionMatrix;
    double currentEvaluation;
    evaluation = 1;
    PositionDto previousPositionDto = modelingService.describePosition(positionMatrix);
    PositionDto nextPositionDto;
    for (CharPosition possibleMove : reasonableMoves) {
      currentPositionMatrix = Arrays.stream(positionMatrix).map(String[]::clone).toArray(String[][]::new);
      currentPositionMatrix[possibleMove.getRow()][possibleMove.getColumn()] = playerChar;

      PositionDto currentPositionDto = modelingService.fastDescribePosition(positionMatrix, previousPositionDto, possibleMove, playerChar);
      if (checkWinning(currentPositionDto, playerChar)) {
        evaluation = -1;
        return possibleMove;
      }

      CharPosition bestOpponentMove = findWorstMove(currentPositionMatrix, opponentChar(playerChar),startOfGame);
      nextPositionDto = modelingService.fastDescribePosition(currentPositionMatrix, currentPositionDto, bestOpponentMove, opponentChar(playerChar));
      currentEvaluation = evaluateOpponentPosition(nextPositionDto);
      if (currentEvaluation < evaluation) {
        evaluation = currentEvaluation;
        bestMove = possibleMove;
      }
    }
    return bestMove;
  }

  private CharPosition findWorstMove(String[][] positionMatrix, String playerChar, boolean startOfGame) {

    List<CharPosition> reasonableMoves = MoveUtilsImpl.findReasonableMoves(positionMatrix, startOfGame);

    CharPosition worstMove = reasonableMoves.get(0);
    double currentEvaluation;
    double worstEvaluation = 0;
    PositionDto currentPositionDto = modelingService.describePosition(positionMatrix);
    for (CharPosition possibleMove : reasonableMoves) {
      currentEvaluation = evaluateOpponentPosition(modelingService.fastDescribePosition(positionMatrix, currentPositionDto, possibleMove, playerChar));
      if (currentEvaluation > worstEvaluation) {
        worstEvaluation = currentEvaluation;
        worstMove = possibleMove;
      }
    }

    return worstMove;
  }

  private double evaluatePosition(PositionDto positionDto) {

    double[] positionDescription = Arrays.stream(assembler.extractPositionModel(positionDto).getPositionDescription()).asDoubleStream().toArray();

    double dotProduct = MatrixUtils.createRealVector(coefficients).dotProduct(MatrixUtils.createRealVector(positionDescription));

    return 1/(1+Math.exp(-dotProduct*Math.log(2)));
  }

  private double evaluateOpponentPosition(PositionDto positionDto) {

    double[] positionDescription = Arrays.stream(assembler.extractPositionModel(positionDto).getPositionDescription()).asDoubleStream().toArray();

    double dotProduct = MatrixUtils.createRealVector(opponetCoefficients).dotProduct(MatrixUtils.createRealVector(positionDescription));

    return 1/(1+Math.exp(-dotProduct*Math.log(2)));
  }

  private String opponentChar(String playerChar) {
    return "X".equals(playerChar) ? "O" : "X";
  }

  private CharPosition checkWinning(List<CharPosition> reasonableMoves, String[][] positionMatrix,PositionDto previousPositionDto, String playerChar) {
    for (CharPosition possibleMove: reasonableMoves) {
      PositionDto currentPositionDto = modelingService.fastDescribePosition(positionMatrix, previousPositionDto, possibleMove, playerChar);
      if (checkWinning(currentPositionDto, playerChar)) {
        evaluation = -1;
        return possibleMove;
      }
    }
    return new CharPosition(-1,-1);
  }

  private boolean checkWinning(PositionDto lastPosition, String playerChar) {

    if ("X".equals(playerChar)) {
      return lastPosition.getXQuintuples()>0;
    }
    if ("O".equals(playerChar)) {
      return lastPosition.getOQuintuples()>0;
    }
    return false;
  }

  private boolean checkStopLoosing(String[][] positionMatrix, CharPosition[] worstOpponentMove, PositionDto previousPositionDto, String playerChar) {
    if (worstOpponentMove[1].equals(new CharPosition(-1,-1))) {
      PositionDto stopLosingPositionDto = modelingService.fastDescribePosition(positionMatrix, previousPositionDto, worstOpponentMove[0], playerChar);
      evaluation = evaluatePosition(stopLosingPositionDto);
      return true;
    }
    return false;
  }

  private double computeNoMoveEvaluation(String[][] positionMatrix, CharPosition[] worstOpponentMove, PositionDto previousPositionDto, String playerChar) {
    PositionDto oneStepPositionDto = modelingService.fastDescribePosition(positionMatrix, previousPositionDto, worstOpponentMove[0], opponentChar(playerChar));
    positionMatrix[worstOpponentMove[0].getRow()][worstOpponentMove[0].getColumn()] = opponentChar(playerChar);
    PositionDto twoStepsPositionDto = modelingService.fastDescribePosition(positionMatrix, oneStepPositionDto, worstOpponentMove[1], playerChar);
    positionMatrix[worstOpponentMove[0].getRow()][worstOpponentMove[0].getColumn()] = EMPTY;
    return evaluatePosition(twoStepsPositionDto);
  }

  public static void main(String[] args) {
    String[][] positionMatrix = new String[25][25];
    for (int i=0; i<25; i++) {
      for (int j=0; j<25; j++) {
        positionMatrix[i][j] = "&nbsp;";
      }
    }
    positionMatrix[12][12] = "X";
    positionMatrix[12][13] = "O";
    positionMatrix[13][12] = "X";
    positionMatrix[11][12] = "O";


    boolean startOfGame = true;
    String playerChar = "X";
    AdvancedMoveServiceImpl advancedMoveService = new AdvancedMoveServiceImpl(playerChar);
    System.out.println(advancedMoveService.findBestMoveOneStep(positionMatrix,playerChar,startOfGame));
  }

}
