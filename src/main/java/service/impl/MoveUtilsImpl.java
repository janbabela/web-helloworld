package service.impl;

import dto.CharPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MoveUtilsImpl {

  public static List<CharPosition> findReasonableMoves(String[][] positionMatrix, boolean startOfGame) {

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

  private static boolean checkSurroundingCells(String[][] positionMatrix, int row, int column, boolean startOfGame) {

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
    System.out.println(findReasonableMoves(testPositionMatrix, true));
  }

}
