import java.util.Scanner;

/**
 * A class to make human control that implements Control interface.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class HumanControl implements Control{
    //player
    private Player player;
    //input socket
    private Scanner scanner;

    /**
     * Create a new HumanControl with given player and input.
     *
     * @param player player.
     * @param scanner input socket.
     */
    public HumanControl(Player player, Scanner scanner) {
        this.player = player;
        this.scanner = scanner;
    }

    /**
     * An override method that implements putCard method of Control interface for human control.
     *
     */
    @Override
    public int putCard(Card currentCard) {
        System.out.println(player.getName() + "! Choose a number.(for example 3)");
        int number;
        try {
            number = scanner.nextInt();
        } catch (Exception InputMismatchException) {
            scanner.next();
            number = -1;
        }
        return number - 1;
    }

    /**
     * An override method that implements chooseColor method of Control interface for human control.
     *
     */
    @Override
    public char chooseColor() {
        System.out.println(player.getName() +"! Choose a color between Red and Blue and Green and Yellow.(Enter first character of color for example R)");
        return scanner.next().trim().charAt(0);
    }

    /**
     * An override method that implements yesOrNo method of Control interface for human control.
     *
     */
    @Override
    public char yesOrNo() {
        System.out.println(player.getName() + "! Do you want to be fined?(Enter Y or N)");
        return scanner.next().trim().charAt(0);
    }
}
