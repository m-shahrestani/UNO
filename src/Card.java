/**
 * A class to hold card and its fields.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class Card
{
    //symbol of Card
    private String symbol;
    //color of Card
    private Color color;
    //score of Card
    protected int score;

    /**
     * Create a new Card with given symbol and color.
     *
     * @param symbol symbol of Card.
     * @param color color of Card.
     */
    public Card(String symbol, Color color)
    {
        this.symbol = symbol;
        this.color = color;
    }

    /**
     * Determine that the card can put on current card with given current card.
     *
     * @param currentCard current card of UNO.
     * @return true if the card can put on the current card, false otherwise.
     */
    public boolean isValidCard(Card currentCard) {
        return true;
    }

    /**
     * get the symbol of Card.
     *
     * @return symbol.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * get the color of Card.
     *
     * @return color.
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * get the score of Card.
     *
     * @return score.
     */
    public int getScore() {
        return score;
    }

    /**
     * set the color of Card to given color.
     *
     * @param color new color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * get a String for show Card.
     *
     * @return a String.
     */
    @Override
    public String toString()
    {
        StringBuilder tCard = new StringBuilder();
        String printColor = color.getPrintColor();
        tCard.append(printColor);
        tCard.append("╔═══════╗\n");
        tCard.append(printColor);
        tCard.append("║       ║\n");
        tCard.append(printColor);
        if (symbol.equals("W+4")||symbol.equals("Rev")||symbol.equals("D+2")||symbol.equals("Ski")) {
            tCard.append("║  ").append(symbol).append("  ║\n");
        }
        else {
            tCard.append("║   ").append(symbol).append("   ║\n");
        }
        tCard.append(printColor);
        tCard.append("║       ║\n");
        tCard.append(printColor);
        tCard.append("╚═══════╝");
        tCard.append(Color.RESET.getPrintColor());
        return tCard.toString();
    }

    /**
     * get a String for show repository cards and current card.
     *
     * @return a String.
     */
    public String currToString()
    {
        StringBuilder tCard = new StringBuilder();
        String printColor = color.getPrintColor();
        tCard.append(Color.BLACK.getPrintColor());
        tCard.append("                  ╔═══════╗ ");
        tCard.append(printColor);
        tCard.append("╔═══════╗\n");
        tCard.append(Color.BLACK.getPrintColor());
        tCard.append("                  ║#######║ ");
        tCard.append(printColor);
        tCard.append("║       ║\n");
        tCard.append(Color.BLACK.getPrintColor());
        tCard.append("                  ║#######║ ");
        if (symbol.equals("W+4")||symbol.equals("Rev")||symbol.equals("D+2")||symbol.equals("Ski")) {
            tCard.append(printColor).append("║  ").append(symbol).append("  ║\n");
        }
        else {
            tCard.append(printColor).append("║   ").append(symbol).append("   ║\n");
        }
        tCard.append(Color.BLACK.getPrintColor());
        tCard.append("                  ║#######║ ");
        tCard.append(printColor);
        tCard.append("║       ║\n");
        tCard.append(Color.BLACK.getPrintColor());
        tCard.append("                  ╚═══════╝ ");
        tCard.append(printColor);
        tCard.append("╚═══════╝\n");
        tCard.append(Color.BLACK.getPrintColor());
        return tCard.toString();
    }

    /**
     * get a String for show behind of Card.
     *
     * @return a String.
     */
    public String backToString()
    {
        return Color.BLACK.getPrintColor() +
                "╔═══════╗\n" +
                "║#######║\n" +
                "║#######║\n" +
                "║#######║\n" +
                "╚═══════╝" +
                Color.RESET.getPrintColor();
    }
}
