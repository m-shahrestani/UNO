import java.util.ArrayList;
import java.util.Collections;
import static java.lang.Integer.max;

/**
 * A class to make PC control that implements Control interface.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class PcControl implements Control {
    //player
    private Player player;
    //state
    private State state;

    /**
     * Create a new PcControl with given player and state.
     *
     * @param player player.
     * @param state recent state of game.
     */
    public PcControl(Player player, State state) {
        this.player = player;
        this.state = state;
    }

    /**
     * An override method that implements putCard method of Control interface for PC control.
     *
     */
    @Override
    public int putCard(Card currentCard) {
        int number = pcDecidePut(currentCard);
//        System.out.println(number+1);
        try{Thread.sleep(1000);}catch(InterruptedException ignored){}
        return number;
    }

    /**
     * An override method that implements chooseColor method of Control interface for PC control.
     *
     */
    @Override
    public char chooseColor() {
        char color = pcDecideChooseColor();
//        System.out.println(color);
        try{Thread.sleep(1000);}catch(InterruptedException ignored){}
        return color;
    }

    /**
     * An override method that implements yesOrNo method of Control interface for PC control.
     *
     */
    @Override
    public char yesOrNo() {
        System.out.println(player.getName() + "! Do you want to be fined?(Enter Y or N)");
        System.out.println('N');
        try{Thread.sleep(1000);}catch(InterruptedException ignored){}
        return 'N';
    }

    /**
     * decide to do best putting.
     *
     * @param currentCard the current card of UNO.
     * @return index of player cards.
     */
    private int pcDecidePut(Card currentCard) {
        ArrayList<Integer> wildCards = new ArrayList<>();
        ArrayList<Integer> wildDrawCards = new ArrayList<>();
        ArrayList<Integer> draw2Cards = new ArrayList<>();
        ArrayList<Integer> reverseCards = new ArrayList<>();
        ArrayList<Integer> skipCards = new ArrayList<>();
        ArrayList<Integer> numberCards = new ArrayList<>();
        ArrayList<Integer> colorCards = new ArrayList<>();
        for (int i = 0; i < player.getCards().size(); i++) {
            if (player.getCards().get(i).getColor() == currentCard.getColor()) {
                colorCards.add(i);
            }
            if (player.getCards().get(i) instanceof WildDrawCard) {
                wildDrawCards.add(i);
            }
            else if(player.getCards().get(i) instanceof WildCard) {
                wildCards.add(i);
            }
            if(player.getCards().get(i) instanceof Draw2Card) {
                draw2Cards.add(i);
            }
            if(player.getCards().get(i) instanceof ReverseCard) {
                reverseCards.add(i);
            }
            if(player.getCards().get(i) instanceof SkipCard) {
                skipCards.add(i);
            }
            if( player.getCards().get(i) instanceof NumberCard && player.getCards().get(i).getSymbol().equals(currentCard.getSymbol())) {
                numberCards.add(i);
            }
        }
        Collections.sort(numberCards);
        if (currentCard instanceof WildDrawCard && state.getWildDrawPenalty() != 1 ) {
            return wildDrawCards.get(0);
        }
        if (currentCard instanceof Draw2Card && draw2Cards.size() > 0) {
            return draw2Cards.get(0);
        }
        if(currentCard instanceof ReverseCard && reverseCards.size() > 0) {
            return reverseCards.get(0);
        }
        if(currentCard instanceof SkipCard && skipCards.size() > 0) {
            return skipCards.get(0);
        }
        if(colorCards.size() > 0) {
            return colorCards.get(0);
        }
        if (numberCards.size() > 0) {
            return numberCards.get(numberCards.size()-1);
        }
        if(wildCards.size() > 0) {
            return wildCards.get(0);
        }
        if (wildDrawCards.size() > 0) {
            return wildDrawCards.get(0);
        }
        return 1;
    }

    /**
     * decide to choose best color.
     *
     * @return color. first character of color.
     */
    private char pcDecideChooseColor() {
        int countR = 0;
        int countB = 0;
        int countG = 0;
        int countY = 0;
        for (Card temp : player.getCards()) {
            if (temp.getColor() == Color.RED) {
                countR++;
            }
            if (temp.getColor() == Color.BLUE) {
                countB++;
            }
            if (temp.getColor() == Color.GREEN) {
                countG++;
            }
            if (temp.getColor() == Color.YELLOW) {
                countY++;
            }
        }
        int max = max( max(countR, countB), max(countG, countY));
        if (max == countB) {
            return 'B';
        }
        if (max == countG) {
            return 'G';
        }
        if (max == countY) {
            return 'Y';
        }
        return 'R';
    }
}
