/**
 * An interface for control.
 * The interface represents control methods for UNO game.
 * Implementations of this interface: putCard and chooseColor and yesOrNo.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public interface Control {
    /**
     * This method give input for GameManager with given current card.
     *
     * @param currentCard the current card of UNO game.
     * @return index of player cards.
     */
    int putCard(Card currentCard);

    /**
     * This method gives input color for wild cards.
     *
     * @return color. first character of color.
     */
    char chooseColor();

    /**
     * This method gives input for penalty question.
     *
     * @return Y or N character
     */
    char yesOrNo();
}
