package freecell.util.FreecellCard;

import java.util.HashMap;

public enum CardValue {
  ONE("A", 1), TWO("2", 2), THREE("3", 3), FOUR("4", 4), FIVE("5", 5), SIX("6", 6), SEVEN("7",7),
  EIGHT("8", 8), NINE("9", 9), TEN("10", 10), ELEVEN("J", 11), TWELVE("Q", 12), THIRTEEN("K", 13);

  public static final HashMap<Integer, CardValue> CardValueMap = new HashMap<>();
  static {
    CardValueMap.put(1, CardValue.ONE);
    CardValueMap.put(2, CardValue.TWO);
    CardValueMap.put(3, CardValue.THREE);
    CardValueMap.put(4, CardValue.FOUR);
    CardValueMap.put(5, CardValue.FIVE);
    CardValueMap.put(6, CardValue.SIX);
    CardValueMap.put(7, CardValue.SEVEN);
    CardValueMap.put(8, CardValue.EIGHT);
    CardValueMap.put(9, CardValue.NINE);
    CardValueMap.put(10, CardValue.TEN);
    CardValueMap.put(11, CardValue.ELEVEN);
    CardValueMap.put(12, CardValue.TWELVE);
    CardValueMap.put(13, CardValue.THIRTEEN);
  }

  private final String symbol;
  private final int value;

  CardValue(String s, int v) {

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
