import org.junit.Before;
import org.junit.Test;

import freecell.util.FreecellCard.Card;
import freecell.util.FreecellCard.CardColor;
import freecell.util.FreecellCard.CardSuit;
import freecell.util.FreecellCard.CardValue;
import freecell.util.FreecellCard.FreecellCard;
import freecell.util.FreecellDeck.Deck;
import freecell.util.FreecellDeck.FreecellDeck;
import freecell.util.FreecellPile.CascadePile;
import freecell.util.FreecellPile.Pile;

import static org.junit.Assert.*;

public class CascadePileTest {

  private Deck deck;
  private Pile cascade;
  private Pile cascade1;

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
  private Card c15;

  @Before
  public void setUp() {

    deck = new FreecellDeck();
    cascade = new CascadePile(8, deck.getDeck());
    cascade1 = new CascadePile(4, deck.getDeck());

    c1 = new FreecellCard(CardSuit.CLUBS, CardColor.BLACK, CardValue.ONE);
    c2 = new FreecellCard(CardSuit.CLUBS, CardColor.BLACK, CardValue.TWO);
    c3 = new FreecellCard(CardSuit.CLUBS, CardColor.BLACK, CardValue.THREE);
    c4 = new FreecellCard(CardSuit.CLUBS, CardColor.BLACK, CardValue.FOUR);
    c5 = new FreecellCard(CardSuit.CLUBS, CardColor.BLACK, CardValue.FIVE);
    c6 = new FreecellCard(CardSuit.CLUBS, CardColor.BLACK, CardValue.SIX);
    c7 = new FreecellCard(CardSuit.CLUBS, CardColor.BLACK, CardValue.SEVEN);
    c8 = new FreecellCard(CardSuit.HEARTS, CardColor.RED, CardValue.EIGHT);
    c9 = new FreecellCard(CardSuit.HEARTS, CardColor.RED, CardValue.NINE);
    c10 = new FreecellCard(CardSuit.HEARTS, CardColor.RED, CardValue.TEN);
    c11 = new FreecellCard(CardSuit.HEARTS, CardColor.RED, CardValue.ELEVEN);
    c12 = new FreecellCard(CardSuit.HEARTS, CardColor.RED, CardValue.TWELVE);
    c13 = new FreecellCard(CardSuit.HEARTS, CardColor.RED, CardValue.THIRTEEN);
    c14 = new FreecellCard(CardSuit.SPADES, CardColor.BLACK, CardValue.ONE);
    c15 = new FreecellCard(CardSuit.DIAMONDS, CardColor.RED, CardValue.TWO);
  }

  @Test
  public void addCardTest() {

    System.out.println(cascade.toString() + "\n");
    System.out.println(cascade1.toString() + "\n");

    cascade = cascade.addCard(7, c8);
    cascade1 = cascade1.addCard(3, c12);
    System.out.println(cascade.toString() + "\n");
    System.out.println(cascade1.toString() + "\n");

    cascade = cascade.addCard(7, c7);
    cascade1 = cascade1.addCard(2, c11);
    System.out.println(cascade.toString() + "\n");
    System.out.println(cascade1.toString() + "\n");

    try {
      cascade.addCard(-1, c1); // out boundary
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      cascade.addCard(8, c1); // out boundary
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      cascade.addCard(3, c11); // not -1 for next card
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      cascade.addCard(7, c6); // same color
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }
  }

  @Test
  public void removeCardTest() {

    System.out.println(cascade.toString() + "\n");

    cascade = cascade.removeCard(1, 6);
    System.out.println(cascade.toString() + "\n");

    cascade = cascade.removeCard(7, 5);
    System.out.println(cascade.toString() + "\n");

    cascade = cascade.removeCard(7, 4);
    System.out.println(cascade.toString() + "\n");

    cascade = cascade.removeCard(7, 3);
    System.out.println(cascade.toString() + "\n");

    cascade = cascade.removeCard(7, 2);
    System.out.println(cascade.toString() + "\n");

    cascade = cascade.removeCard(7, 1);
    System.out.println(cascade.toString() + "\n");

    cascade = cascade.removeCard(7, 0);
    System.out.println(cascade.toString() + "\n");

    try {
      cascade.removeCard(-1, 0); // out boundary
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      cascade.removeCard(8, 0); // out boundary
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      cascade.removeCard(0, 10); // wrong index
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      cascade.removeCard(0, 1); // wrong index
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      cascade.removeCard(7, 0); // empty pile
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }
  }

  @Test
  public void getMovedCardTest() {

    System.out.println(cascade.toString() + "\n");
    System.out.println(cascade.getMovedCard(0, 6));
    System.out.println(cascade.getMovedCard(7, 5));

    cascade = cascade.removeCard(7, 5);
    cascade = cascade.removeCard(7, 4);
    cascade = cascade.removeCard(7, 3);
    cascade = cascade.removeCard(7, 2);
    cascade = cascade.removeCard(7, 1);
    cascade = cascade.removeCard(7, 0);
    System.out.println(cascade.toString() + "\n");

    try {
      cascade.getMovedCard(-1, 0); // out boundary
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      cascade.getMovedCard(8, 0); // out boundary
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      cascade.getMovedCard(0, 10); // wrong index
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      cascade.getMovedCard(0, 1); // wrong index
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }

    try {
      cascade.getMovedCard(7, 0); // empty pile
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.toString());
    }
  }

  @Test
  public void isPileFullTest() {

    System.out.println(cascade.toString());
    System.out.println(cascade.isPileFull());

    cascade = cascade.removeCard(7, 5);
    System.out.println(cascade.toString() + "\n");
    System.out.println(cascade.isPileFull());
  }
}