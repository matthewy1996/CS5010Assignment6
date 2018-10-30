package freecell.util.FreecellPile;

import freecell.model.PileType;
import freecell.util.FreecellCard.Card;

public final class OpenPile extends AbstractPile {

  private final Card[] pile;
  private final int openSlots;

  public OpenPile(int numberOfPiles) {

    super(PileType.OPEN, numberOfPiles);
    openSlots = numberOfPiles;
    pile = new Card[numberOfPiles];
  }

  private OpenPile(int numberOfPilesCopy, int openSlotsCopy, Card[] pileCopy) {

    super(PileType.OPEN, numberOfPilesCopy);
    openSlots = openSlotsCopy;
    pile = pileCopy;
  }

  @Override
  public OpenPile addCard(int pileNumber, Card cardToAdd) throws IllegalArgumentException {

    if (pileNumber < 0 || pileNumber > getNumberOfPiles() - 1) {
      throw new IllegalArgumentException("OP: Does not have open pile " + pileNumber);
    } else if (pile[pileNumber] != null) {
      throw new IllegalArgumentException("OP: One open pile can only save one card at a time");
    }

    int numberOfPilesCopy = getNumberOfPiles(); // make copy
    int openSlotsCopy = openSlots - 1;
    Card[] pileCopy = new Card[numberOfPilesCopy];
    System.arraycopy(pile, 0, pileCopy, 0, getNumberOfPiles());

    pileCopy[pileNumber] = cardToAdd;

    return new OpenPile(numberOfPilesCopy, openSlotsCopy, pileCopy);
  }

  @Override
  public OpenPile removeCard(int pileNumber, int cardIndex) throws IllegalArgumentException {

    if (pileNumber < 0 || pileNumber > getNumberOfPiles() - 1) {
      throw new IllegalArgumentException("OP: Does not have open pile " + pileNumber);
    } else if (cardIndex != 0) {
      throw new IllegalArgumentException("OP: This pile does not have index " + cardIndex);
    } else if (pile[pileNumber] == null) {
      throw new IllegalArgumentException("OP: This pile has nothing to remove");
    }

    int numberOfPilesCopy = getNumberOfPiles(); // make copy
    int openSlotsCopy = openSlots + 1;
    Card[] pileCopy = new Card[numberOfPilesCopy];
    System.arraycopy(pile, 0, pileCopy, 0, getNumberOfPiles());

    pileCopy[pileNumber] = null;

    return new OpenPile(numberOfPilesCopy, openSlotsCopy, pileCopy);
  }

  @Override
  public Card getMovedCard(int pileNumber, int cardIndex) throws IllegalArgumentException {

    if (pileNumber < 0 || pileNumber > getNumberOfPiles() - 1) {
      throw new IllegalArgumentException("OP: Does not have open pile " + pileNumber);
    } else if (cardIndex != 0) {
      throw new IllegalArgumentException("OP: This pile does not have index " + cardIndex);
    } else if (pile[pileNumber] == null) {
      throw new IllegalArgumentException("OP: This pile does not have any card");
    }

    return pile[pileNumber];
  }

  @Override
  public boolean isPileFull() {

    boolean res = true;

    for (int i = 0; i < getNumberOfPiles(); i++) {
      res = res && pile[i] != null;
    }

    return res;
  }

  @Override
  public String toString() {

    StringBuilder res = new StringBuilder();

    for (int i = 0; i < getNumberOfPiles(); i++) {
      res.append(getType().getSymbol());
      res.append(i + 1);
      res.append(":");
      if (pile[i] != null) {
        res.append(" ");
        res.append(pile[i].toString());
      }
      res.append("\n");
    }

    return res.toString();
  }
}
