import org.junit.Before;
import org.junit.Test;

import freecell.util.FreecellCard.Card;
import freecell.util.FreecellCard.CardColor;
import freecell.util.FreecellCard.CardSuit;
import freecell.util.FreecellCard.CardValue;
import freecell.util.FreecellCard.FreecellCard;
import freecell.util.FreecellPile.FoundationPile;
import freecell.util.FreecellPile.Pile;

import static org.junit.Assert.*;

public class FoundationPileTest {

  private Pile foundation;
  private Card c1;
  private Card c2;
  private Card c3;
  private Card c4;
  private Card c5;
  private Card c6;
  private Card c7;
  private Card c8;
  private Card c9;
  private Card c10;
  private Card c11;
  private Card c12;
  private Card c13;
  private Card c14;

  @Before
  public void setUp() {

    foundation = new FoundationPile(4);

    c1 = new FreecellCard(CardSuit.CLUBS, CardColor.BLACK, CardValue.ONE);
    c2 = new FreecellCard(CardSuit.CLUBS, CardColor.BLACK, CardValue.TWO);
    c3 = new FreecellCard(CardSuit.CLUBS, CardColor.BLACK, CardValue.THREE);
    c4 = new FreecellCard(CardSuit.CLUBS, CardColor.BLACK, CardValue.FOUR);
    c5 = new FreecellCard(CardSuit.CLUBS, CardColor.BLACK, CardValue.FIVE);
    c6 = new FreecellCard(CardSuit.CLUBS, CardColor.BLACK, CardValue.SIX);
    c7 = new FreecellCard(CardSuit.CLUBS, CardColor.BLACK, CardValue.SEVEN);
    c8 = new FreecellCard(CardSuit.CLUBS, CardColor.BLACK, CardValue.EIGHT);
    c9 = new FreecellCard(CardSuit.CLUBS, CardColor.BLACK, CardValue.NINE);
    c10 = new FreecellCard(CardSuit.CLUBS, CardColor.BLACK, CardValue.TEN);
    c11 = new FreecellCard(CardSuit.CLUBS, CardColor.BLACK, CardValue.ELEVEN);
    c12 = new FreecellCard(CardSuit.CLUBS, CardColor.BLACK, CardValue.TWELVE);
    c13 = new FreecellCard(CardSuit.CLUBS, CardColor.BLACK, CardValue.THIRTEEN);
    c14 = new FreecellCard(CardSuit.HEARTS, CardColor.RED, CardValue.TWO);
  }

  @Test
  public void addCardTest() {

    System.out.println(foundation.toString());
    foundation = foundation.addCard(0, c1);
    System.out.println(foundation.toString());
    foundation = foundation.addCard(0, c2);
    System.out.println(foundation.toString());
    foundation = foundation.addCard(0, c3);
    System.out.println(foundation.toString());
    foundation = foundation.addCard(0, c4);
    System.out.println(foundation.toString());
    foundation = foundation.addCard(0, c5);
    System.out.println(foundation.toString());
    foundation = foundation.addCard(0, c6);
    System.out.println(foundation.toString());
    foundation = foundation.addCard(0, c7);
    System.out.println(foundation.toString());
    foundation = foundation.addCard(0, c8);
    System.out.println(foundation.toString());
    foundation = foundation.addCard(0, c9);
    System.out.println(foundation.toString());
    foundation = foundation.addCard(0, c10);
    System.out.println(foundation.toString());
    foundation = foundation.addCard(0, c11);
    System.out.println(foundation.toString());
    foundation = foundation.addCard(0, c12);
    System.out.println(foundation.toString());
    foundation = foundation.addCard(0, c13);
    System.out.println(foundation.toString());
    foundation = foundation.addCard(1, c1);
    System.out.println(foundation.toString());
    foundation = foundation.addCard(2, c1);
    System.out.println(foundation.toString());

    try {
      foundation.addCard(-1, c1); // out boundary
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      foundation.addCard(4, c1); // out boundary
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      foundation.addCard(0, c1); // full capacity
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      foundation.addCard(3, c2); // not ACE for first card
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      foundation.addCard(2, c3); // not +1 for next card
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      foundation.addCard(1, c14); // not same suit
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }
  }

  @Test
  public void removeCardTest() {

    System.out.println(foundation.toString());
    foundation = foundation.addCard(0, c1);
    System.out.println(foundation.toString());
    foundation = foundation.addCard(0, c2);
    System.out.println(foundation.toString());
    foundation = foundation.addCard(0, c3);
    System.out.println(foundation.toString());
    foundation = foundation.addCard(0, c4);
    System.out.println(foundation.toString());
    foundation = foundation.addCard(0, c5);
    System.out.println(foundation.toString());

    foundation = foundation.removeCard(0, 4);
    System.out.println(foundation.toString());
    foundation = foundation.removeCard(0, 3);
    System.out.println(foundation.toString());

    try {
      foundation.removeCard(-1, 0); // out boundary
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      foundation.removeCard(5, 0); // out boundary
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      foundation.removeCard(0, 5); // wrong index
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      foundation.removeCard(0, 1); // wrong index
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      foundation.removeCard(1, 0); // empty pile
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }
  }

  @Test
  public void getMovedCardTest() {

    System.out.println(foundation.toString());
    foundation = foundation.addCard(0, c1);
    System.out.println(foundation.toString());
    foundation = foundation.addCard(0, c2);
    System.out.println(foundation.toString());
    foundation = foundation.addCard(0, c3);
    System.out.println(foundation.toString());
    foundation = foundation.addCard(0, c4);
    System.out.println(foundation.toString());
    foundation = foundation.addCard(0, c5);
    System.out.println(foundation.toString());

    System.out.println(foundation.getMovedCard(0, 4));

    try {
      foundation.getMovedCard(-1, 0); // out boundary
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      foundation.getMovedCard(5, 0); // out boundary
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      foundation.getMovedCard(0, 5); // wrong index
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      foundation.getMovedCard(0, 1); // wrong index
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      foundation.getMovedCard(1, 0); // empty pile
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }
  }

  @Test
  public void isPileFullTest() {

    System.out.println(foundation.toString());
    System.out.println(foundation.isPileFull());

    for (int i = 0; i < 4; i++) {
      foundation = foundation.addCard(i, c1);
      foundation = foundation.addCard(i, c2);
      foundation = foundation.addCard(i, c3);
      foundation = foundation.addCard(i, c4);
      foundation = foundation.addCard(i, c5);
      foundation = foundation.addCard(i, c6);
      foundation = foundation.addCard(i, c7);
      foundation = foundation.addCard(i, c8);
      foundation = foundation.addCard(i, c9);
      foundation = foundation.addCard(i, c10);
      foundation = foundation.addCard(i, c11);
      foundation = foundation.addCard(i, c12);
      foundation = foundation.addCard(i, c13);
      System.out.println(foundation.toString());
      System.out.println(foundation.isPileFull());
    }
  }
}