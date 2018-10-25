import org.junit.Before;
import org.junit.Test;

import freecell.util.FreecellDeck.FreecellDeck;

public class FreecellDeckTest {

  private FreecellDeck deck;

  @Before
  public void setUp() {

    deck = new FreecellDeck();
  }

  @Test
  public void print() {

    System.out.println(deck.getDeck().toString());
  }
}