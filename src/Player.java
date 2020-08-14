import java.util.ArrayList;

/**
 * A class to hold player and its fields.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class Player {
    //name of Player
    private String name;
    //an ArrayList for hand cards of Player
    private ArrayList<Card> hand;
    //score of Player
    private int score;

    /**
     * Create a new Player with a given name and 7cards.
     *
     * @param name name of Player.
     * @param hand arrayList of 7 cards.
     */
    public Player(String name, ArrayList<Card> hand) {
        this.name = name;
        this.hand = hand;
        calculateScore();
    }

    /**
     * get the name of player.
     *
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * get the cards collection.
     *
     * @return hand.
     */
    public ArrayList<Card> getCards() {
        return hand;
    }

    /**
     * get the number of player cards.
     *
     * @return number.
     */
    public int getNumberOfCards() {
        return hand.size();
    }

    /**
     * Add a card to the cards collection.
     *
     * @param card The card to be added.
     */
    public void addCard(Card card) {
        hand.add(card);
        calculateScore();
    }

    /**
     * Remove a card from the cards collection.
     *
     * @param card The card to be removed.
     */
    public void removeCard(Card card) {
        hand.remove(card);
        calculateScore();
    }

    /**
     * Calculate score of player.
     */
    private void calculateScore() {
        int count = 0;
        for (Card temp: hand) {
             count += temp.getScore();
        }
        this.score = count;
    }

    /**
     * get a String for print of name and number of cards and scores.
     *
     * @return a String.
     */
    @Override
    public String toString() {
        return name + ": "+ getNumberOfCards() + " cards, " + score + " scores ";
    }

    /**
     * get a String for show player cards.
     *
     * @return a String.
     */
    public String handToString() {
        StringBuilder
                tHand = new StringBuilder(),
                top = new StringBuilder(),
                midTop1 = new StringBuilder(),
                midTop2 = new StringBuilder(),
                midBot = new StringBuilder(),
                bot = new StringBuilder();

        tHand.append(Color.BLACK.getPrintColor());
        for(int i = 1; i <= hand.size() && i <= 15; i++) {
            if(i <= 9) {
                tHand.append("    ").append(i).append("    ");
            }
            else {
                tHand.append("   ").append(i).append("    ");
            }
        }
        tHand.append("\n");
        for(int i = 1; i <= hand.size(); i++)
        {
            Card c = hand.get(i-1);
            String[] cs = c.toString().split("\n");
            top.append(cs[0]);
            midTop1.append(cs[1]);
            midTop2.append(cs[2]);
            midBot.append(cs[3]);
            bot.append(cs[4]);
            if(i % 15 == 0 && i != 0)
            {
                bot.append("\n");
                tHand.append(top
                        .append("\n")
                        .append(midTop1.append("\n"))
                        .append(midTop2.append("\n"))
                        .append(midBot.append("\n"))
                        .append(bot.append(Color.BLACK.getPrintColor()))
                );
                top = new StringBuilder();
                midTop1 = new StringBuilder();
                midTop2 = new StringBuilder();
                midBot = new StringBuilder();
                bot = new StringBuilder();
                for(int j = 1 + i; j <= hand.size() && j  < 15; j++)
                    tHand.append("   ").append(j).append("    ");
                tHand.append("\n");
            }
        }
        tHand.append(top
                .append("\n")
                .append(midTop1.append("\n"))
                .append(midTop2.append("\n"))
                .append(midBot.append("\n"))
                .append(bot.append("\n"))
        );
        tHand.append(Color.BLACK.getPrintColor()).append("\n");
        return tHand.toString();
    }

    /**
     * get a String for show behind of cards.
     *
     * @return a String.
     */
    public String backHandToString() {
        StringBuilder
                tHand = new StringBuilder(),
                top = new StringBuilder(),
                midTop1 = new StringBuilder(),
                midTop2 = new StringBuilder(),
                midBot = new StringBuilder(),
                bot = new StringBuilder();

        tHand.append(Color.BLACK.getPrintColor());
        tHand.append("\n");
        for(int i = 1; i <= hand.size(); i++)
        {
            Card c = hand.get(i-1);
            String[] cs = c.backToString().split("\n");
            top.append(cs[0]);
            midTop1.append(cs[1]);
            midTop2.append(cs[2]);
            midBot.append(cs[3]);
            bot.append(cs[4]);
            if(i % 15 == 0 && i != 0)
            {
                bot.append("\n");
                tHand.append(top
                        .append("\n")
                        .append(midTop1.append("\n"))
                        .append(midTop2.append("\n"))
                        .append(midBot.append("\n"))
                        .append(bot.append(Color.BLACK.getPrintColor()))
                );
            }
        }
        tHand.append(top
                .append("\n")
                .append(midTop1.append("\n"))
                .append(midTop2.append("\n"))
                .append(midBot.append("\n"))
                .append(bot.append("\n"))
        );
        tHand.append(Color.BLACK.getPrintColor()).append("\n");
        return tHand.toString();
    }
}
