import java.util.Random;

/**
 * A class to hold state of UNO game.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class State {
    //a counter for turn
    private int turn;
    //number of players
    private int numberOfPlayers;
    //direction of turning
    private int direction;
    //a counter for penalty progression penalty of Draw2Card
    private int drawPenalty;
    //a counter for penalty progression of WildDrawCard
    private int wildDrawPenalty;

    /**
     * Create a new State with given number of players.
     *
     * @param numberOfPlayers number of Players.
     */
    public State(int numberOfPlayers){
        turn = 1000 + new Random().nextInt(numberOfPlayers);
        this.numberOfPlayers = numberOfPlayers;
        direction = 1;
        drawPenalty = 1;
        wildDrawPenalty = 1;
    }

    /**
     * get the turn of UNO player.
     *
     * @return turn. index of player list.
     */
    public int getTurn() {
        return (turn % numberOfPlayers);
    }

    /**
     * get the turn of UNO next player.
     *
     * @return turn. index of player list.
     */
    public int getNextTurn() {
        return ((turn + direction )% numberOfPlayers);
    }

    /**
     * get the direction of turning.
     *
     * @return direction. 1 for clockwise and -1 for anticlockwise.
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Change turn.
     *
     */
    public void nextTurn() {
        turn += direction;
    }

    /**
     * Change direction of turning.
     *
     */
    public void changeDirection() {
        direction = (-1)*direction;
    }

    /**
     * get count of penalty progression for Draw2Card.
     *
     * @return drawPenalty. counter of drawPenalty.
     */
    public int getDrawPenalty() {
        return drawPenalty;
    }

    /**
     * get count of penalty progression for WildDrawCard.
     *
     * @return wildDrawPenalty. counter of wildDrawPenalty.
     */
    public int getWildDrawPenalty() {
        return wildDrawPenalty;
    }

    /**
     * Increase drawPenalty counter.
     *
     */
    public void increaseDrawPenalty() {
        drawPenalty++;
    }

    /**
     * Increase wildDrawPenalty counter.
     *
     */
    public void increaseWildDrawPenalty() {
        wildDrawPenalty++;
    }

    /**
     * set drawPenalty counter to 1 after fine.
     *
     */
    public void doDrawPenalty() {
        drawPenalty = 1;
    }

    /**
     * set wildDrawPenalty counter to 1 after fine.
     *
     */
    public void doWildDrawPenalty() {
        wildDrawPenalty = 1;
    }

}
