package service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.ModelingService;

public class ModelingServiceImplTest {

  private String[][] positionMatrix = new String[25][25];
  private ModelingService modelingService = new ModelingServiceImpl();

  @Before
  public void init() {
    for (int i=0;i<25;i++) {
      for (int j=0;j<25;j++) {
        positionMatrix[i][j] = "&nbsp;";
      }
    }
  }

  @Test
  public void describePosition_shouldCountDoubles() {

    positionMatrix[0][5] = "X";
    positionMatrix[0][6] = "X";
    positionMatrix[0][23] = "O";
    positionMatrix[1][24] = "O";
    positionMatrix[0][15] = "O";
    positionMatrix[3][18] = "X";
    positionMatrix[4][19] = "X";

    modelingService.describePosition(positionMatrix);

    Assert.assertEquals(1, modelingService.getPositionModel().getXOpenDoubles());
    Assert.assertEquals(0, modelingService.getPositionModel().getOHalfOpenDoubles());
    Assert.assertEquals(1, modelingService.getPositionModel().getXMostlyOpenDoubles());
  }
}
