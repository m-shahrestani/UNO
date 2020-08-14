/**
 * A class for Number Card that inherits Card class.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class NumberCard extends Card {
    //number
    private  int number;

    /**
     * Create a new NumberCard with given symbol and color and number.
     *
     * @param symbol symbol of Card.
     * @param color color of Card.
     * @param number number of Card.
     */
    public NumberCard(String symbol,Color color ,int  number) {
        super(symbol, color);
        this. number = number;
        score = number;
    }

    /**
     * get the number of Card.
     *
     * @return number.
     */
    public int getNumber() {
        return number;
    }

    /**
     * An override method that inherits isValidCard method of Card class.
     *
     */
    @Override
    public boolean isValidCard(Card currentCard) {
        return (currentCard instanceof NumberCard && this.number == ((NumberCard) currentCard).getNumber())
                || this.getColor() == currentCard.getColor();
    }
}