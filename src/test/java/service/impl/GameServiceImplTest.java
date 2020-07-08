package service.impl;

import dto.PositionDto;
import org.junit.Test;

import java.util.List;

public class GameServiceImplTest {

  private GameServiceImpl gameService = new GameServiceImpl();


  @Test
  public void evaluateGamePositions_shortGame() {

    List<PositionDto> testGame = gameService.getGame();
    testGame.add(new PositionDto());
    testGame.add(new PositionDto());
    testGame.add(new PositionDto());

    gameService.evaluateGamePositions();

    System.out.println("1 X : " + testGame.get(0).getEvaluation());
    System.out.println("2 O : " + testGame.get(1).getEvaluation());
    System.out.println("3 X : " + testGame.get(2).getEvaluation());
  }

  @Test
  public void evaluateGamePositions_mediumGame() {

    List<PositionDto> testGame = gameService.getGame();
    testGame.add(new PositionDto());
    testGame.add(new PositionDto());
    testGame.add(new PositionDto());
    testGame.add(new PositionDto());
    testGame.add(new PositionDto());
    testGame.add(new PositionDto());
    testGame.add(new PositionDto());

    gameService.evaluateGamePositions();

    System.out.println("1 X : " + testGame.get(0).getEvaluation());
    System.out.println("2 O : " + testGame.get(1).getEvaluation());
    System.out.println("3 X : " + testGame.get(2).getEvaluation());
    System.out.println("4 O : " + testGame.get(3).getEvaluation());
    System.out.println("5 X : " + testGame.get(4).getEvaluation());
    System.out.println("6 O : " + testGame.get(5).getEvaluation());
    System.out.println("7 X : " + testGame.get(6).getEvaluation());

  }

  @Test
  public void evaluateGamePositions_longerGame() {

    List<PositionDto> testGame = gameService.getGame();

    for (int i=0; i<30; i++) {
      testGame.add(new PositionDto());
    }

    gameService.evaluateGamePositions();

    for (int i=0; i<30; i++) {
      String playerChar = (i%2==0) ? "X" : "O";
      System.out.println((i+1) + " " + playerChar + " : " + testGame.get(i).getEvaluation());
    }
  }

}