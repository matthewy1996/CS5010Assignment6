package freecell.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import freecell.util.FreecellCard.Card;
import freecell.util.FreecellDeck.Deck;
import freecell.util.FreecellDeck.FreecellDeck;
import freecell.util.FreecellPile.CascadePile;
import freecell.util.FreecellPile.FoundationPile;
import freecell.util.FreecellPile.OpenPile;
import freecell.util.FreecellPile.Pile;

public final class FreecellModel implements FreecellOperations {

  private static final Deck validFreecellDeck = new FreecellDeck();
  private static final int NUMBER_OF_FOUNDATION_PILES = 4;

  private final int numberOfOpenPile;
  private final int numberOfCascadePile;
  private Pile openPile;
  private Pile foundationPile;
  private Pile cascadePile;
  private GameState state;

  public static FreecellModelBuilder getBuilder() {

    return new FreecellModelBuilder();
  }

  private FreecellModel(int numberOfOpenPileIn, int numberOfCascadePileIn)
          throws IllegalArgumentException {

    if (numberOfOpenPileIn < 1 || numberOfCascadePileIn < 4) {
      throw new IllegalArgumentException("Minimum 1 open pile, and 4 cascade piles required");
    }

    numberOfOpenPile = numberOfOpenPileIn;
    numberOfCascadePile = numberOfCascadePileIn;

    openPile = new OpenPile(numberOfOpenPile);
    foundationPile = new FoundationPile(NUMBER_OF_FOUNDATION_PILES);
    cascadePile = new CascadePile(numberOfCascadePile, new ArrayList<>());

    state = GameState.WAITING_INITIALIZATION;
  }

  public static class FreecellModelBuilder implements FreecellOperationsBuilder {

    private int numberOfOpenPile;      // openPile: >= 1
    private int numberOfCascadePile;   // cascadePile: >= 4

    private FreecellModelBuilder() {

      numberOfOpenPile = 4;
      numberOfCascadePile = 8;
    }

    @Override
    public FreecellOperationsBuilder cascades(int numberOfCascadePileIn) {

      numberOfCascadePile = numberOfCascadePileIn;
      return this;
    }

    @Override
    public FreecellOperationsBuilder opens(int numberOfOpenPileIn) {

      numberOfOpenPile = numberOfOpenPileIn;
      return this;
    }

    @Override
    public <K> FreecellOperations<K> build() {

      return new FreecellModel(numberOfOpenPile, numberOfCascadePile);
    }
  }

  @Override
  public List<Card> getDeck() {

    return validFreecellDeck.getDeck();
  }

  private List<Card> shuffle(List<Card> deck) {

    Random shuffleSeed = new Random();
    List<Card> shuffledDeck = new ArrayList<>(deck);

    for (int i = 0; i < deck.size(); i++) {
      int j = shuffleSeed.nextInt(i + 1);
      Card temp = shuffledDeck.get(i);
      shuffledDeck.set(i, shuffledDeck.get(j));
      shuffledDeck.set(j, temp);
    }

    return shuffledDeck;
  }

  @Override
  public void startGame(List deck, boolean shuffle) throws IllegalArgumentException {

    if (deck.size() != validFreecellDeck.getDeck().size()
            || !deck.containsAll(validFreecellDeck.getDeck())) {
      throw new IllegalArgumentException("Deck is invalid");
    }

    if (shuffle) {
      cascadePile = new CascadePile(numberOfCascadePile, shuffle(deck));
    } else {
      cascadePile = new CascadePile(numberOfCascadePile, deck);
    }

    openPile = new OpenPile(numberOfOpenPile);
    foundationPile = new FoundationPile(NUMBER_OF_FOUNDATION_PILES);

    state = GameState.WAITING_NEXT_MOVE;
  }

  private Card getMovedCard(PileType source, int pileNumber, int cardIndex)
          throws IllegalArgumentException {

    switch (source) {
      case OPEN:
        return openPile.getMovedCard(pileNumber, cardIndex);
      case CASCADE:
        return cascadePile.getMovedCard(pileNumber, cardIndex);
      case FOUNDATION:
        return foundationPile.getMovedCard(pileNumber, cardIndex);
      default:
        return null;
    }
  }

  private void addCard(PileType destination, int pileNumber, Card cardToAdd)
          throws IllegalArgumentException {

    switch (destination) {
      case OPEN:
        openPile = openPile.addCard(pileNumber, cardToAdd);
        break;
      case CASCADE:
        cascadePile = cascadePile.addCard(pileNumber, cardToAdd);
        break;
      case FOUNDATION:
        foundationPile = foundationPile.addCard(pileNumber, cardToAdd);
        break;
    }
  }

  private void removeCard(PileType source, int pileNumber, int cardIndex)
          throws IllegalArgumentException {

    switch (source) {
      case OPEN:
        openPile = openPile.removeCard(pileNumber, cardIndex);
        break;
      case CASCADE:
        cascadePile = cascadePile.removeCard(pileNumber, cardIndex);
        break;
      case FOUNDATION:
        foundationPile = foundationPile.removeCard(pileNumber, cardIndex);
        break;
    }
  }

  private boolean noMoreMoves() { // check GAME_OVER_STALEMATE

    return false;
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                   int destPileNumber) throws IllegalArgumentException, IllegalStateException {

    if (state != GameState.WAITING_NEXT_MOVE) {
      throw new IllegalStateException("A move is attempted before the game has starts");
    }

    Card cardToAdd = getMovedCard(source, pileNumber, cardIndex);
    addCard(destination, destPileNumber, cardToAdd);
    removeCard(source, pileNumber, cardIndex);

    if (noMoreMoves()) {
      state = GameState.GAME_OVER_STALEMATE;
    } else if (foundationPile.isPileFull()) {
      state = GameState.GAME_OVER_WIN;
    }
  }

  @Override
  public boolean isGameOver() {

    return state == GameState.GAME_OVER_WIN || state == GameState.GAME_OVER_STALEMATE;
  }

  @Override
  public String getGameState() {

    if (state == GameState.WAITING_INITIALIZATION) {
      return "";
    } else {
      return foundationPile.toString() + openPile.toString() + cascadePile.toString();
    }
  }
}
