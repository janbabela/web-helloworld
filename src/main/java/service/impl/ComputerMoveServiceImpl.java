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

    List<CharPosition> reasonableMoves = MoveUtilsImpl.findReasonableMoves(positionMatrix, startOfGame);

    CharPosition bestMove = reasonableMoves.get(0);
    String[][] currentPositionMatrix;
    double currentEvaluation;
    evaluation = 0;
    for (CharPosition possibleMove : reasonableMoves) {
      currentPositionMatrix = Arrays.stream(positionMatrix).map(String[]::clone).toArray(String[][]::new);
      currentPositionMatrix[possibleMove.getRow()][possibleMove.getColumn()] = playerChar;
      currentEvaluation = evaluatePosition(modelingService.describePosition(currentPositionMatrix));
      if (currentEvaluation > evaluation) {
        evaluation = currentEvaluation;
        bestMove = possibleMove;
      }
    }

    return bestMove;
  }


  private double evaluatePosition(PositionDto positionDto) {

    double[] positionDescription = Arrays.stream(assembler.extractPositionModel(positionDto).getPositionDescription()).asDoubleStream().toArray();

    double dotProduct = MatrixUtils.createRealVector(coefficients).dotProduct(MatrixUtils.createRealVector(positionDescription));

    return 1/(1+Math.exp(-dotProduct*Math.log(2)));
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
