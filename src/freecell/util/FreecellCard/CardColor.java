package freecell.util.FreecellCard;

public enum CardColor {
  RED(1), BLACK(2);

  private final int value;

  CardColor(int v) {

    value = v;
  }

  public int toInteger() {

    return value;
  }
}
