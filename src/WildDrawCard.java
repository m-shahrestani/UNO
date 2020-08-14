/**
 * A class for WildDraw Card that inherits WildCard class.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class WildDrawCard extends WildCard {

    /**
     * Create a new WildDrawCard with given symbol and color.
     *
     * @param symbol symbol of Card.
     * @param color color of Card.
     */
    public WildDrawCard(String symbol, Color color) {
        super(symbol, color);
    }

    /**
     * An override method that inherits isValidCard method of Card class.
     *
     */
    @Override
    public boolean isValidCard(Card currentCard) {
        return false;
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
        return 4;
    }
}
