package freecell.util.FreecellDeck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import freecell.util.FreecellCard.Card;
import freecell.util.FreecellCard.CardColor;
import freecell.util.FreecellCard.CardSuit;
import freecell.util.FreecellCard.FreecellCard;

public final class FreecellDeck implements Deck {

  private final List<FreecellCard> deck;
  private static final int MIN_VALUE = 1;
  private static final int SIZE_OF_EACH_SUIT = 13;
  private static final int SIZE_OF_DECK = 52;

  public FreecellDeck() {

    deck = new ArrayList<>();
    addCard();
  }

  private void addOneTypeOfCard(CardSuit cardSuit, CardColor cardColor) {

    for (int cardValue = MIN_VALUE; cardValue <= SIZE_OF_EACH_SUIT; cardValue++) {
      deck.add(new FreecellCard(cardSuit, cardColor, cardValue));
    }
  }

  private void addCard() {

    addOneTypeOfCard(CardSuit.DIAMONDS, CardColor.RED);
    addOneTypeOfCard(CardSuit.CLUBS, CardColor.BLACK);
    addOneTypeOfCard(CardSuit.HEARTS, CardColor.RED);
    addOneTypeOfCard(CardSuit.SPADES, CardColor.BLACK);
  }

  @Override
  public List<Card> getDeck() {

    return new ArrayList<>(deck);
  }

  @Override
  public List<Card> getShuffledDeck() {

    Random shuffleSeed = new Random();
    List<Card> shuffledDeck = new ArrayList<>(deck);

    for (int i = 0; i < SIZE_OF_DECK; i++) {
      int j = shuffleSeed.nextInt(i);
      Card temp = shuffledDeck.get(i);
      shuffledDeck.set(i, shuffledDeck.get(j));
      shuffledDeck.set(j, temp);
    }

    return shuffledDeck;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }
    if (!(o instanceof Deck)) {
      return false;
    }
    Deck another = (Deck) o;
    List<Card> anotherDeck = another.getDeck();
    return anotherDeck.size() == SIZE_OF_DECK && anotherDeck.containsAll(deck);
  }

  @Override
  public int hashCode() {

    return getDeck().stream().mapToInt(Object::hashCode).sum();
  }
}
