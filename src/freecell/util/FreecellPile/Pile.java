package freecell.util.FreecellPile;

import freecell.model.PileType;
import freecell.util.FreecellCard.Card;

public interface Pile {

  PileType getType();

  int getNumberOfPiles();

  Pile addCard(int pileNumber, Card cardToAdd) throws IllegalArgumentException;

  Pile removeCard(int pileNumber, int cardIndex) throws IllegalArgumentException;

  Card getMovedCard(int pileNumber, int cardIndex) throws IllegalArgumentException;
  // pileNumber starting at 0, cardIndex starting at 0, print starting at 1.
  boolean isPileFull();
}
