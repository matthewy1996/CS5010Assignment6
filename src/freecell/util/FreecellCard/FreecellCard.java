package freecell.util.FreecellCard;

public final class FreecellCard implements Card {

  private final CardSuit cardSuit;
  private final CardColor cardColor;
  private final CardValue cardValue;

  public FreecellCard(CardSuit cardSuitIn, CardColor cardColorIn, CardValue cardValueIn) {

    cardSuit = cardSuitIn;
    cardColor = cardColorIn;
    cardValue = cardValueIn;
  }

  public FreecellCard(CardSuit cardSuitIn, CardColor cardColorIn, int cardValueIn) {

    cardSuit = cardSuitIn;
    cardColor = cardColorIn;
    cardValue = CardValue.CardValueMap.get(cardValueIn);
  }

  @Override
  public CardSuit getCardSuit() {

    return cardSuit;
  }

  @Override
  public CardColor getCardColor() {

    return cardColor;
  }

  @Override
  public CardValue getCardValue() {

    return cardValue;
  }

  @Override
  public boolean isDiffColor(Card o) {

    return cardColor != o.getCardColor();
  }

  @Override
  public boolean isSameSuit(Card o) {

    return cardSuit == o.getCardSuit();
  }

  @Override
  public boolean isOneGreater(Card o) {

    return cardValue.toInteger() - o.getCardValue().toInteger() == 1;
  }

  @Override
  public boolean isOneSmaller(Card o) {

    return o.getCardValue().toInteger() - cardValue.toInteger() == 1;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }
    if (!(o instanceof Card)) {
      return false;
    }
    Card card = (Card) o;
    return cardSuit == card.getCardSuit()
            && cardColor == card.getCardColor()
            && cardValue == card.getCardValue();
  }

  @Override
  public int hashCode() {

    return getCardColor().toInteger()
            + getCardSuit().toInteger() * 100
            + getCardValue().toInteger() * 10000;
  }

  @Override
  public int compareTo(Card o) {

    return cardValue.toInteger() - o.getCardValue().toInteger();
  }

  @Override
  public String toString() {

    return cardValue.getSymbol() + cardSuit.getSymbol();
  }
}
