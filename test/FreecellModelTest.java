import org.junit.Before;
import org.junit.Test;

import freecell.model.FreecellModel;
import freecell.model.FreecellOperations;
import freecell.model.PileType;

public class FreecellModelTest {

  private FreecellOperations game;
  private FreecellOperations game1;

  @Before
  public void setUp() {

    game = FreecellModel.getBuilder().cascades(52).opens(4).build();
    game1 = FreecellModel.getBuilder().cascades(6).opens(2).build();
  }

  @Test
  public void test() {

    System.out.println(game.getDeck().toString());
    System.out.println(game.isGameOver());
    System.out.println(game.getGameState());
    game.startGame(game.getDeck(), false);
    System.out.println(game.getGameState());

    for (int i = 0; i < 13; i++) {
      game.move(PileType.CASCADE, i, 0, PileType.FOUNDATION, 0);
      game.move(PileType.CASCADE, 13 + i, 0, PileType.FOUNDATION, 1);
      game.move(PileType.CASCADE, 26 + i, 0, PileType.FOUNDATION, 2);
      game.move(PileType.CASCADE, 39 + i, 0, PileType.FOUNDATION, 3);
      System.out.println(game.getGameState());
      System.out.println(game.isGameOver());
    }
  }

}