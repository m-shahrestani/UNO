import java.util.Scanner;

/**
 * A class to drive program.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //take game mode
        int numberOfPlayers;
        int numberOfPcPlayers = 0;
        while (true) {
            System.out.println("Enter mode:");
            System.out.println("3- 3 Players (2 PC)");
            System.out.println("4- 4 Players (3 PC)");
            System.out.println("5- 5 Players (4 PC)");
            System.out.println("6- n Players");
              numberOfPlayers = scanner.next().trim().charAt(0)-'0';
            if ( 3 <= numberOfPlayers && numberOfPlayers <= 6) {
                if(numberOfPlayers == 3) {
                    numberOfPcPlayers = 2;
                }
                if(numberOfPlayers == 4) {
                    numberOfPcPlayers = 3;
                }
                if(numberOfPlayers == 5) {
                    numberOfPcPlayers = 4;
                }
                if (numberOfPlayers == 6){
                    while (true) {
                        System.out.println("How many players do you want? (2-n)");
                        try {
                            numberOfPlayers = scanner.nextInt();
                        } catch (Exception InputMismatchException) {
                            scanner.next();
                            numberOfPlayers = -1;
                        }
                        if( 2 <= numberOfPlayers) {
                            break;
                        }
                        System.out.println("Please enter a number between 2-n");
                    }
                    while (true) {
                        System.out.println("How many PC players do you want? (0-" + numberOfPlayers + ")");
                        try {
                            numberOfPcPlayers = scanner.nextInt();
                        } catch (Exception InputMismatchException) {
                            scanner.next();
                            numberOfPcPlayers = -1;
                        }
                        if( 0 <= numberOfPcPlayers && numberOfPcPlayers <= numberOfPlayers) {
                            break;
                        }
                        System.out.println("Please enter a number between 0-"+ numberOfPlayers);
                    }
                }
                break;
            }
            System.out.println("Please enter a number between 3-6");
        }
        //take players name
        String[] names = new String[numberOfPlayers];
        if (numberOfPcPlayers > 0) {
            System.out.println("Enter name of  PC players.");
        }
        for(int i =0; i < numberOfPcPlayers; i++) {
            System.out.print( (i+1) + "th PC player name: ");
            names[i] = scanner.next().trim() + "(PC)";
        }
        if ((numberOfPlayers - numberOfPcPlayers) > 0) {
            System.out.println("Enter name of Human players.");
        }
        for(int i = 0; i < (numberOfPlayers - numberOfPcPlayers); i++) {
            System.out.print( (i+1) + "th Human player name: ");
            names[numberOfPcPlayers+i] = scanner.next().trim();
        }
        //make UNO game
        GameManager UNO = new GameManager(numberOfPlayers, numberOfPcPlayers, names, scanner);
        UNO.playGame();
    }
}