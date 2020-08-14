/**
 * A class for Wild Card that inherits ActionCard abstract class.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class WildCard extends ActionCard {

    /**
     * Create a new WildCard with given symbol and color and set score to 50.
     *
     * @param symbol symbol of Card.
     * @param color color of Card.
     */
    public WildCard(String symbol, Color color) {
        super(symbol, color);
        score = 50;
    }

    /**
     * Set config of WildCard to given color.
     *
     * @param color recent state of UNO game.
     */
    public void setConfig(char color) {
        if (color == 'R') {
            this.setColor(Color.RED);
        }
        if (color == 'B') {
            this.setColor(Color.BLUE);
        }
        if (color == 'Y') {
            this.setColor(Color.YELLOW);
        }
        if (color == 'G') {
            this.setColor(Color.GREEN);
        }
    }
}
