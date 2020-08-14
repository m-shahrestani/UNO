/**
 * A class for Skip Card that inherits ActionCard abstract class.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class SkipCard extends ActionCard {

    /**
     * Create a new SkipCard with given symbol and color.
     *
     * @param symbol symbol of Card.
     * @param color color of Card.
     */
    public SkipCard(String symbol,Color color) {
        super(symbol, color);
    }

    /**
     * An override method that inherits isValidCard method of Card class.
     *
     */
    @Override
    public boolean isValidCard(Card currentCard) {
        return currentCard.getColor() == this.getColor() || currentCard instanceof SkipCard;
    }

    /**
     * An override method that inherits changeState method of ActionCard class.
     *
     */
    @Override
    public void changeState(State state) {
        state.nextTurn();
    }
}
