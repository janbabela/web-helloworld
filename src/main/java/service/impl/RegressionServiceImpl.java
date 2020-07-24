package service.impl;

import model.PositionModel;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import service.RegressionService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class RegressionServiceImpl implements RegressionService {

  public double[] computeCoefficientLeastSquare(double[][] xVariable, double[] yVariable) {

    RealMatrix xMatrix = MatrixUtils.createRealMatrix(xVariable);
    RealVector yVector = MatrixUtils.createRealVector(yVariable);
    RealMatrix xTransposed = xMatrix.transpose();
    RealMatrix multiplied = xTransposed.multiply(xMatrix);
    RealMatrix inverted = new LUDecomposition(multiplied).getSolver().getInverse();

    RealVector xTransposedByY = xTransposed.operate(yVector);
    RealVector result = inverted.operate(xTransposedByY);

    return result.toArray();
  }

  public double[] createYVariable(List<PositionModel> positionModels) {

    List<Double> resultList = new ArrayList<>();

    positionModels.forEach(m-> {
      double evaluation = m.getEvaluation();
      if (evaluation ==1) evaluation = 0.99;
      if (evaluation ==0) evaluation = 0.01;
      double transformedEvaluation = Math.log(evaluation/(1-evaluation))/Math.log(2);
      resultList.add(transformedEvaluation);}
    );

    return resultList.stream().mapToDouble(d -> d).toArray();
  }

  public double[][] createXVariable(List<PositionModel> positionModels, String myChar) {

    double[][] result = new double[positionModels.size()][32];

    for (int i =0; i<positionModels.size(); i++) {

      PositionModel positionModel = positionModels.get(i);
      if (myChar.equals(positionModel.getLastMove())) {
        result[i] = Arrays.stream(positionModel.getPositionDescription()).asDoubleStream().toArray();
      } else {
        int[] modifiedPositionDescription1 = Arrays.copyOfRange(positionModel.getPositionDescription(),16,32);
        int[] modifiedPositionDescription2 = Arrays.copyOfRange(positionModel.getPositionDescription(),0,16);
        int[] modifiedPositionDescriptionFull = IntStream.concat(Arrays.stream(modifiedPositionDescription1), Arrays.stream(modifiedPositionDescription2)).toArray();
        result[i] = Arrays.stream(modifiedPositionDescriptionFull).asDoubleStream().toArray();
      }
    }

    return result;
  }

  public static void main(String[] args) {

    RegressionServiceImpl regressionService = new RegressionServiceImpl();

    double[] yInput = {-5.99996d,5.99996d,-5.99996d,5.99996d,5.99996d,5.99996d};
    double[][] xInput = {{1d,1d,1d,1d,1d,1d},{-5.999996d,1d,2d,3d,4d,6d}};

    double[] yInput2 = {-1.38, -1.38, -1.38, -1.38, -1.38, -1.38, 1.38, -1.38, 1.38, -1.38, 1.38, -1.38, 1.38, -1.38, 1.38, 1.38, 1.38, 1.38, 1.38, 1.38};
    double[][] xInput2 = {{1d,1d,1d,1d,1d,1d,1d,1d,1d,1d,1d,1d,1d,1d,1d,1d,1d,1d,1d,1d},{0.5, 0.75, 1, 1.25, 1.5, 1.75, 1.75, 2, 2.25, 2.5, 2.75, 3, 3.25, 3.5, 4, 4.25, 4.5, 4.75, 5, 5.5}};

    System.out.println(Arrays.toString(regressionService.computeCoefficientLeastSquare(xInput,yInput)));
    System.out.println(Arrays.toString(regressionService.computeCoefficientLeastSquare(xInput2,yInput2)));

  }

}
