package freecell.util.FreecellPile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import freecell.model.PileType;
import freecell.util.FreecellCard.Card;

public final class CascadePile extends AbstractPile {

  private static final int SIZE_OF_DECK = 52;

  private final List<List<Card>> pile;
  private final int[] pileCounter;

  public CascadePile(int numberOfPiles, List<Card> deck) {

    super(PileType.CASCADE, numberOfPiles);
    pile = new ArrayList<>();
    pileCounter = new int[numberOfPiles];
    for (int i = 0; i < numberOfPiles; i++) {
      pile.add(new ArrayList<>());
      pileCounter[i] = 0;
    }

    for (int i = 0; i < deck.size(); i++) {
      pile.get(i % numberOfPiles).add(deck.get(i));
      pileCounter[i % numberOfPiles]++;
    }
  }

  private CascadePile(int numberOfPilesCopy, List<List<Card>> pileCopy, int[] pileCounterCopy) {

    super(PileType.CASCADE, numberOfPilesCopy);
    pile = pileCopy;
    pileCounter = pileCounterCopy;
  }

  private boolean isValidCardToAdd(int pileNumber, Card cardToAdd) {

    if (pileCounter[pileNumber] == 0) {
      return true;
    } else {
      Card topCard = pile.get(pileNumber).get(pileCounter[pileNumber] - 1);
      return cardToAdd.isOneSmaller(topCard) && cardToAdd.isDiffColor(topCard);
    }
  }

  @Override
  public Pile addCard(int pileNumber, Card cardToAdd) throws IllegalArgumentException {

    if (pileNumber < 0 || pileNumber > getNumberOfPiles() - 1) {
      throw new IllegalArgumentException("CP: Does not have cascade pile " + pileNumber);
    } else if (!isValidCardToAdd(pileNumber, cardToAdd)) {
      throw new IllegalArgumentException("CP: Not a valid card to add to pile " + pileNumber);
    }

    int numberOfPilesCopy = getNumberOfPiles(); // make copy
    List<List<Card>> pileCopy = new ArrayList<>();
    for (int i = 0; i < numberOfPilesCopy; i++) {
      pileCopy.add(new ArrayList<>(pile.get(i)));
    }
    int[] pileCounterCopy = new int[numberOfPilesCopy];
    System.arraycopy(pileCounter, 0, pileCounterCopy, 0, getNumberOfPiles());

    pileCopy.get(pileNumber).add(cardToAdd);
    pileCounterCopy[pileNumber]++;

    return new CascadePile(numberOfPilesCopy, pileCopy, pileCounterCopy);
  }

  @Override
  public Pile removeCard(int pileNumber, int cardIndex) throws IllegalArgumentException {

    if (pileNumber < 0 || pileNumber > getNumberOfPiles() - 1) {
      throw new IllegalArgumentException("CP: Does not have cascade pile " + pileNumber);
    } else if (pileCounter[pileNumber] == 0) {
      throw new IllegalArgumentException("CP: This pile has nothing to remove");
    } else if (cardIndex != pileCounter[pileNumber] - 1) {
      throw new IllegalArgumentException("CP: Can not move card at index " + cardIndex);
    }

    int numberOfPilesCopy = getNumberOfPiles(); // make copy
    List<List<Card>> pileCopy = new ArrayList<>();
    for (int i = 0; i < numberOfPilesCopy; i++) {
      pileCopy.add(new ArrayList<>(pile.get(i)));
    }
    int[] pileCounterCopy = new int[numberOfPilesCopy];
    System.arraycopy(pileCounter, 0, pileCounterCopy, 0, getNumberOfPiles());

    pileCopy.get(pileNumber).remove(cardIndex);
    pileCounterCopy[pileNumber]--;

    return new CascadePile(numberOfPilesCopy, pileCopy, pileCounterCopy);
  }

  @Override
  public Card getMovedCard(int pileNumber, int cardIndex) throws IllegalArgumentException {

    if (pileNumber < 0 || pileNumber > getNumberOfPiles() - 1) {
      throw new IllegalArgumentException("CP: Does not have cascade pile " + pileNumber);
    } else if (pileCounter[pileNumber] == 0) {
      throw new IllegalArgumentException("CP: This pile does not have any card");
    } else if (cardIndex != pileCounter[pileNumber] - 1) {
      throw new IllegalArgumentException("CP: Can not move card at index " + cardIndex);
    }

    return pile.get(pileNumber).get(cardIndex);
  }

  @Override
  public boolean isPileFull() {

    return Arrays.stream(pileCounter).sum() >= SIZE_OF_DECK;
  }

  @Override
  public String toString() {

    StringBuilder res = new StringBuilder();

    for (int i = 0; i < getNumberOfPiles(); i++) {
      res.append(getType().getSymbol());
      res.append(i + 1);
      res.append(":");
      if (pileCounter[i] > 0) {
        res.append(" ");
        for (int j = 0; j < pileCounter[i]; j++) {
          res.append(pile.get(i).get(j).toString());
          if (j != pileCounter[i] - 1) {
            res.append(", ");
          }
        }
      }
      if (i != getNumberOfPiles() - 1) {
        res.append("\n");
      }
    }

    return res.toString().trim();
  }
}
