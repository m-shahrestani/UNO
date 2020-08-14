/**
 * An Enum for set code of colors.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public enum Color {
    //colors
    RESET("Reset"),
    PURPLE("Purple"),
    CYAN("Cyan"),
    RED("Red"),
    GREEN("Green"),
    BLUE("Blue"),
    YELLOW("Yellow"),
    BLACK("Black");
    //print color code
    private final String printColor;

    /**
     * set code of colors to printColor.
     *
     * @param color name of color.
     */
    Color(String color)
    {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_PURPLE = "\u001B[35m";
        String ANSI_CYAN = "\u001B[36m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_BLACK = "\u001B[30m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_YELLOW = "\u001B[33m";
        switch (color)
        {
            case "Reset":
                printColor = ANSI_RESET;
                break;
            case "Purple":
                printColor = ANSI_PURPLE;
                break;
            case "Cyan":
                printColor = ANSI_CYAN;
                break;
            case "Red":
                printColor = ANSI_RED;
                break;
            case "Green":
                printColor = ANSI_GREEN;
                break;
            case "Blue":
                printColor = ANSI_BLUE;
                break;
            case "Yellow":
                printColor = ANSI_YELLOW;
                break;
            default:
                printColor = ANSI_BLACK;
                break;
        }
    }

    /**
     * get the print color code.
     *
     * @return printColor. a string of color code.
     */
    public String getPrintColor()
    {
        return printColor;
    }
}