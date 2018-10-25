package freecell.util.FreecellDeck;

import java.util.List;

import freecell.util.FreecellCard.Card;

public interface Deck {

  List<Card> getDeck();

  List<Card> getShuffledDeck();
}
