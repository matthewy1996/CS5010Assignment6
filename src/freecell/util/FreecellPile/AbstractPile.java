package freecell.util.FreecellPile;

import freecell.model.PileType;

abstract class AbstractPile implements Pile {

  private final PileType type;
  private final int numberOfPiles;

  AbstractPile(PileType typeIn, int numberIn) {

    type = typeIn;
    numberOfPiles = numberIn;
  }

  @Override
  public PileType getType() {

    return type;
  }

  @Override
  public int getNumberOfPiles() {

    return numberOfPiles;
  }
}
