package freecell.util.FreecellCard;

public enum CardSuit {
  DIAMONDS("♦", 1), CLUBS("♣", 2), HEARTS("♥", 3), SPADES("♠", 4);

  private final String symbol;
  private final int value;

  CardSuit(String s, int v) {

    symbol = s;
    value = v;
  }

  public String getSymbol() {

    return symbol;
  }

  public int toInteger() {

    return value;
  }
}
