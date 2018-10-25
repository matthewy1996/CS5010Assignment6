import org.junit.Before;
import org.junit.Test;

import freecell.util.FreecellCard.Card;
import freecell.util.FreecellCard.CardColor;
import freecell.util.FreecellCard.CardSuit;
import freecell.util.FreecellCard.CardValue;
import freecell.util.FreecellCard.FreecellCard;
import freecell.util.FreecellPile.OpenPile;
import freecell.util.FreecellPile.Pile;

import static org.junit.Assert.*;

public class OpenPileTest {

  private Pile open;
  private Pile open1;
  private Card c1;
  private Card c2;
  private Card c3;
  private Card c4;

  @Before
  public void setUp() {

    open = new OpenPile(4);
    open1 = new OpenPile(5);

    c1 = new FreecellCard(CardSuit.CLUBS, CardColor.BLACK, CardValue.THREE);
    c2 = new FreecellCard(CardSuit.DIAMONDS, CardColor.RED, CardValue.EIGHT);
    c3 = new FreecellCard(CardSuit.HEARTS, CardColor.RED, CardValue.FOUR);
    c4 = new FreecellCard(CardSuit.SPADES, CardColor.BLACK, CardValue.ELEVEN);
  }

  @Test
  public void addCardTest() {

    System.out.println(open.toString());
    System.out.println(open1.toString());

    open = open.addCard(1, c1);
    open1 = open1.addCard(3, c1);
    System.out.println(open.toString());
    System.out.println(open1.toString());

    open = open.addCard(2, c2);
    open1 = open1.addCard(1, c2);
    System.out.println(open.toString());
    System.out.println(open1.toString());

    open = open.addCard(3, c3);
    open1 = open1.addCard(2, c3);
    System.out.println(open.toString());
    System.out.println(open1.toString());

    open = open.addCard(0, c4);
    open1 = open1.addCard(4, c4);
    System.out.println(open.toString());
    System.out.println(open1.toString());

    try {
      open = open.addCard(0, c4); // full capacity
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      open = open.addCard(-1, c4); // out boundary
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      open = open.addCard(4, c4); // out boundary
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }
  }

  @Test
  public void removeCardTest() {

    System.out.println(open.toString());
    System.out.println(open1.toString());

    open = open.addCard(1, c1);
    open1 = open1.addCard(3, c1);
    System.out.println(open.toString());
    System.out.println(open1.toString());

    open = open.addCard(2, c2);
    open1 = open1.addCard(1, c2);
    System.out.println(open.toString());
    System.out.println(open1.toString());

    open = open.removeCard(2, 0);
    open1 = open1.removeCard(1, 0);
    System.out.println(open.toString());
    System.out.println(open1.toString());

    open = open.removeCard(1, 0);
    open1 = open1.removeCard(3, 0);
    System.out.println(open.toString());
    System.out.println(open1.toString());

    try {
      open = open.removeCard(3, 1); // wrong index
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      open = open.removeCard(-1, 0); // out boundary
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      open = open.removeCard(5, 0); // out boundary
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      open = open.removeCard(0, 0); // empty pile
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }
  }

  @Test
  public void getMovedCardTest() {

    System.out.println(open.toString());
    System.out.println(open1.toString());

    open = open.addCard(1, c1);
    open1 = open1.addCard(3, c1);
    System.out.println(open.toString());
    System.out.println(open1.toString());

    System.out.println(open.getMovedCard(1, 0).toString());
    System.out.println(open1.getMovedCard(3, 0).toString());

    try {
      open.getMovedCard(0, 0); // empty pile
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      open.getMovedCard(0, 1); // wrong index
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      open.getMovedCard(4, 0); // out boundary
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      open.getMovedCard(-1, 0); // out boundary
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }
  }

  @Test
  public void isPileFullTest() {

    System.out.println(open.isPileFull());
    System.out.println(open1.isPileFull());

    open = open.addCard(0, c4);
    open = open.addCard(1, c1);
    open = open.addCard(2, c2);
    open = open.addCard(3, c3);
    open1 = open1.addCard(1, c2);
    open1 = open1.addCard(2, c3);
    open1 = open1.addCard(3, c1);
    open1 = open1.addCard(4, c4);
    System.out.println(open.toString());
    System.out.println(open1.toString());

    System.out.println(open.isPileFull());
    System.out.println(open1.isPileFull());
  }
}