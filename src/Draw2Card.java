/**
 * A class for Draw2 Card that inherits ActionCard abstract class.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class Draw2Card extends ActionCard {

    /**
     * Create a new Draw2Card with given symbol and color.
     *
     * @param symbol symbol of Card.
     * @param color color of Card.
     */
    public Draw2Card(String symbol, Color color) {
        super(symbol, color);
    }

    /**
     * An override method that inherits isValidCard method of Card class.
     *
     */
    @Override
    public boolean isValidCard(Card currentCard) {
        return currentCard.getColor() == this.getColor() || currentCard instanceof Draw2Card;
    }

    /**
     * An override method that inherits changeState method of ActionCard class.
     *
     */
    @Override
    public void changeState(State state) {
        state.nextTurn();
    }

    /**
     * An override method that inherits getForcedCards method of ActionCard class.
     *
     */
    @Override
    public int getForcedCards() {
        return 2;
    }
}
