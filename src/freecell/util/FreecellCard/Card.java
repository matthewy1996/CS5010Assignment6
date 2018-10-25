package freecell.util.FreecellCard;

public interface Card extends Comparable<Card> {

  CardSuit getCardSuit();

  CardColor getCardColor();

  CardValue getCardValue();

  boolean isDiffColor(Card o);

  boolean isSameSuit(Card o);

  boolean isOneGreater(Card o);

  boolean isOneSmaller(Card o);
}
