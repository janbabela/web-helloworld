package service;

import model.PositionModel;

import java.util.List;

public interface RegressionService {

  double[] computeCoefficientLeastSquare(double[][] xVariable, double[] yVariable);

  double[] createYVariable(List<PositionModel> positionModels);

  double[][] createXVariable(List<PositionModel> positionModels, String myChar);

}
