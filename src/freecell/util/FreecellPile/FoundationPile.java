package freecell.util.FreecellPile;

import freecell.util.FreecellCard.Card;
import freecell.util.FreecellCard.CardValue;

public final class FoundationPile extends AbstractPile {

  private static final int SIZE_OF_EACH_PILE = 13;

  private final Card[][] pile; // there is no required ordering of suits in the foundation piles
  private final int[] pileCounter;

  public FoundationPile(int numberOfPiles) {

    super(PileType.FOUNDATION, numberOfPiles);
    pile = new Card[numberOfPiles][SIZE_OF_EACH_PILE];
    pileCounter = new int[numberOfPiles];
    for (int i = 0; i < numberOfPiles; i++) {
      pileCounter[i] = 0;
    }
  }

  private FoundationPile(int numberOfPiles, Card[][] pileCopy, int[] pileCounterCopy) {

    super(PileType.FOUNDATION, numberOfPiles);
    pile = pileCopy;
    pileCounter = pileCounterCopy;
  }

  private boolean isValidCardToAdd(int pileNumber, Card cardToAdd) {

    if (pileCounter[pileNumber] == 0) {
      return cardToAdd.getCardValue() == CardValue.ONE;
    } else {
      Card topCard = pile[pileNumber][pileCounter[pileNumber] - 1];
      return cardToAdd.isOneGreater(topCard) && cardToAdd.isSameSuit(topCard);
    }
  }

  @Override
  public Pile addCard(int pileNumber, Card cardToAdd) throws IllegalArgumentException {

    if (pileNumber < 0 || pileNumber > getNumberOfPiles() - 1) {
      throw new IllegalArgumentException("FP: Does not have foundation pile " + pileNumber);
    } else if (pileCounter[pileNumber] == SIZE_OF_EACH_PILE) {
      throw new IllegalArgumentException("FP: Reach the max size of pile " + pileNumber);
    } else if (!isValidCardToAdd(pileNumber, cardToAdd)) {
      throw new IllegalArgumentException("FP: Not a valid card to add to pile " + pileNumber);
    }

    int numberOfPilesCopy = getNumberOfPiles(); // make copy
    Card[][] pileCopy = new Card[numberOfPilesCopy][SIZE_OF_EACH_PILE];
    for (int i = 0; i < numberOfPilesCopy; i++) {
      System.arraycopy(pile[i], 0, pileCopy[i], 0, SIZE_OF_EACH_PILE);
    }
    int[] pileCounterCopy = new int[numberOfPilesCopy];
    System.arraycopy(pileCounter, 0, pileCounterCopy, 0, numberOfPilesCopy);

    pileCopy[pileNumber][pileCounterCopy[pileNumber]] = cardToAdd;
    pileCounterCopy[pileNumber]++;

    return new FoundationPile(numberOfPilesCopy, pileCopy, pileCounterCopy);
  }

  @Override
  public Pile removeCard(int pileNumber, int cardIndex) throws IllegalArgumentException {

    if (pileNumber < 0 || pileNumber > getNumberOfPiles() - 1) {
      throw new IllegalArgumentException("FP: Does not have foundation pile " + pileNumber);
    } else if (pileCounter[pileNumber] == 0) {
      throw new IllegalArgumentException("FP: This pile has nothing to remove");
    } else if (cardIndex != pileCounter[pileNumber] - 1) {
      throw new IllegalArgumentException("FP: Can not move card at index " + cardIndex);
    }

    int numberOfPilesCopy = getNumberOfPiles(); // make copy
    Card[][] pileCopy = new Card[numberOfPilesCopy][SIZE_OF_EACH_PILE];
    for (int i = 0; i < numberOfPilesCopy; i++) {
      System.arraycopy(pile[i], 0, pileCopy[i], 0, SIZE_OF_EACH_PILE);
    }
    int[] pileCounterCopy = new int[numberOfPilesCopy];
    System.arraycopy(pileCounter, 0, pileCounterCopy, 0, numberOfPilesCopy);

    pileCopy[pileNumber][cardIndex] = null;
    pileCounterCopy[pileNumber]--;

    return new FoundationPile(numberOfPilesCopy, pileCopy, pileCounterCopy);
  }

  @Override
  public Card getMovedCard(int pileNumber, int cardIndex) throws IllegalArgumentException {

    if (pileNumber < 0 || pileNumber > getNumberOfPiles() - 1) {
      throw new IllegalArgumentException("FP: Does not have foundation pile " + pileNumber);
    } else if (pileCounter[pileNumber] == 0) {
      throw new IllegalArgumentException("FP: This pile does not have any card");
    } else if (cardIndex != pileCounter[pileNumber] - 1) {
      throw new IllegalArgumentException("FP: Can not move card at index " + cardIndex);
    }

    return pile[pileNumber][cardIndex];
  }

  @Override
  public boolean isPileFull() {

    return pileCounter[0] == SIZE_OF_EACH_PILE && pileCounter[1] == SIZE_OF_EACH_PILE
            && pileCounter[2] == SIZE_OF_EACH_PILE && pileCounter[3] == SIZE_OF_EACH_PILE;
  }

  @Override
  public String toString() {

    StringBuilder res = new StringBuilder();

    for (int i = 0; i < getNumberOfPiles(); i++) {
      res.append(getType().getSymbol());
      res.append(i + 1);
      res.append(": ");
      if (pileCounter[i] > 0) {
        for (int j = 0; j < pileCounter[i]; j++) {
          res.append(pile[i][j].toString());
          if (j != pileCounter[i] - 1) {
            res.append(",");
          }
          res.append(" ");
        }
      }
      res.append("\n");
    }

    return res.toString();
  }
}
