/**
 * An abstract class for action cards that inherits Card class.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public abstract class ActionCard extends Card{

    /**
     * Create a new ActionCard with given symbol and color and set score to 20.
     *
     * @param symbol symbol of Card.
     * @param color color of Card.
     */
    public ActionCard(String symbol, Color color) {
        super(symbol, color);
        score = 20;
    }

    /**
     * Change state of turning depend on type of ActionCard.
     *
     * @param state recent state of UNO game.
     */
    public void changeState(State state) {
    }

    /**
     * get the forced number of penalty Card for next player.
     *
     * @return number.
     */
    public int getForcedCards(){
        return 0;
    }

}
