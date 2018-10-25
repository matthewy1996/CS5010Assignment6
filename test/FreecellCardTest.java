import org.junit.Before;
import org.junit.Test;

import freecell.util.FreecellCard.FreecellCard;
import freecell.util.FreecellCard.CardColor;
import freecell.util.FreecellCard.CardSuit;
import freecell.util.FreecellCard.CardValue;

public class FreecellCardTest {

  private FreecellCard c1;
  private FreecellCard c2;
  private FreecellCard c3;

  @Before
  public void setUp() {
    c1 = new FreecellCard(CardSuit.CLUBS, CardColor.RED, CardValue.THREE);
    c2 = new FreecellCard(CardSuit.DIAMONDS, CardColor.BLACK, CardValue.ELEVEN);
    c3 = new FreecellCard(CardSuit.DIAMONDS, CardColor.BLACK, CardValue.TWELVE);
  }

  @Test
  public void test() {

    System.out.println(c3.isOneGreater(c2));
    System.out.println(c2.isOneGreater(c3));

    System.out.println(c3.isOneSmaller(c2));
    System.out.println(c2.isOneSmaller(c3));
  }

}