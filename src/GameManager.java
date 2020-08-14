import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * A class to manage UNO game.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class GameManager {
    //list of players
    private ArrayList<Player> players;
    //list of cards
    private ArrayList<Card> cards;
    //list of controls
    private ArrayList<Control> controls;
    //state of game turning and penalty
    private State state;
    //current card
    private Card currentCard;
    //the status of game for continue.
    private boolean gameStatus;

    /**
     * Create a new GameManager with given number of players and number of pc players and names and input.
     *
     * @param numberOfPlayers number of game players.
     * @param numberOfPcPlayers number of game PcPlayers.
     * @param names an array for players' name.
     * @param scanner input socket.
     */
    public GameManager(int numberOfPlayers, int numberOfPcPlayers, String[] names, Scanner scanner) {
        gameStatus = true;
        state = new State(numberOfPlayers);
        cards = new ArrayList<>();
        //call make card depend on number of player
        for (int i = 0; i <= (numberOfPlayers-1)/10; i++) {
            makeCards();
        }
        //make instances of player and control.
        players = new ArrayList<>();
        controls = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            ArrayList<Card> playerCards = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                playerCards.add(cards.get(0));
                cards.remove(0);
            }
            players.add(new Player(names[i], playerCards));
            if (i < numberOfPcPlayers) {
                controls.add(new PcControl(players.get(i),state));
            }
            else {
                controls.add(new HumanControl(players.get(i), scanner));
            }
        }
        //put first current card
        putCurrentCard();
    }

    /**
     * make cards.
     * The method make 108 cards for UNO game.
     */
    private void makeCards() {
        //make number cards(76 cards)
        for (int i = 0; i < 10; i++) {
            String s = i + "";
            cards.add(new NumberCard(s, Color.RED, i));
            cards.add(new NumberCard(s, Color.GREEN, i));
            cards.add(new NumberCard(s, Color.BLUE, i));
            cards.add(new NumberCard(s, Color.YELLOW, i));
            if(i > 0) {
                cards.add(new NumberCard(s, Color.RED, i));
                cards.add(new NumberCard(s, Color.GREEN, i));
                cards.add(new NumberCard(s, Color.BLUE, i));
                cards.add(new NumberCard(s, Color.YELLOW, i));
            }
        }

        //make wild cards(8 cards)⨁
        for (int i = 0; i < 4; i++) {
            cards.add(new WildCard("W", Color.BLACK));
            cards.add(new WildDrawCard("W+4", Color.BLACK));
        }

        //make reverse cards(8 cards)⤤⤦
        for (int i = 0; i < 2; i++) {
            cards.add(new ReverseCard("Rev", Color.RED));
            cards.add(new ReverseCard("Rev", Color.GREEN));
            cards.add(new ReverseCard("Rev", Color.BLUE));
            cards.add(new ReverseCard("Rev", Color.YELLOW));
        }

        //make draw cards(8 cards)⧉
        for (int i = 0; i < 2; i++) {
            cards.add(new Draw2Card("D+2", Color.RED));
            cards.add(new Draw2Card("D+2", Color.GREEN));
            cards.add(new Draw2Card("D+2", Color.BLUE));
            cards.add(new Draw2Card("D+2", Color.YELLOW));
        }

        //make skip cards(8 cards)🚫
        for (int i = 0; i < 2; i++) {
            cards.add(new SkipCard("Ski", Color.RED));
            cards.add(new SkipCard("Ski", Color.GREEN));
            cards.add(new SkipCard("Ski", Color.BLUE));
            cards.add(new SkipCard("Ski", Color.YELLOW));
        }
        //Shuffling cards list
        Collections.shuffle(cards);
    }

    /**
     * put first current card.
     * The method choose first current card and puts it down.
     */
    private void putCurrentCard() {
        int temp = 0;
        while (true) {
            if(!(cards.get(temp) instanceof WildCard) ) {
                currentCard = cards.get(temp);
                cards.remove(temp);
                break;
            }
            temp++;
        }
        if (!(currentCard instanceof NumberCard)) {
            show();
        }
        //Reverse
        if (currentCard instanceof ReverseCard) {
            ((ReverseCard) currentCard).changeState(state);
        }
        //Skip
        if (currentCard instanceof SkipCard) {
            ((SkipCard) currentCard).changeState(state);
        }
        //Draw2
        if (currentCard instanceof Draw2Card) {
            System.out.println("The player was fined.");
            for (int i = 0; i < ((Draw2Card) currentCard).getForcedCards(); i++) {
                players.get(state.getTurn()).addCard(cards.get(0));
                cards.remove(0);
            }
            state.nextTurn();
        }
    }

    /**
     * Change current card of game to given new card and put the last current card between the repository cards.
     *
     * @param newCurrentCard new current card.
     */
    private void changeCurrentCard(Card newCurrentCard) {
        if (currentCard instanceof WildCard) {
            currentCard.setColor(Color.BLACK);
        }
        cards.add(new Random().nextInt(cards.size()) ,this.currentCard);
        this.currentCard = newCurrentCard;
    }

    /**
     * set The status of game to stop game.
     */
    private void setGameStatus() {
        this.gameStatus = false;
    }

    /**
     * Determine status of game finishing.
     * if game was finished, use setGameStatus and show result of game.
     */
    private void checkFinish() {
        for (Player temp: players) {
            if (temp.getNumberOfCards() == 0) {
                System.out.println("...................................................................................................................................................................");
                System.out.println("Game finished.\n");
                System.out.println(Color.PURPLE.getPrintColor() + temp.getName() + " is winner.\n" + Color.RESET.getPrintColor());
                setGameStatus();
            }
        }
        if (!gameStatus) {
            System.out.println(Color.RESET.getPrintColor() + "Results:\n");
            for (Player temp: players) {
                if (temp.getNumberOfCards() != 0) {
                    System.out.println(Color.CYAN.getPrintColor() + temp.toString());
                    System.out.print(temp.handToString() + Color.RESET.getPrintColor());
                }
            }
        }
    }

    /**
     * Show game's area into the terminal.
     */
    private void show() {
        System.out.println("...................................................................................................................................................................");
        System.out.print(Color.RESET.getPrintColor() + "║");
        for(int i = 0; i < players.size(); i++) {
            if (state.getTurn() == i) {
                System.out.print(Color.PURPLE.getPrintColor());
                System.out.print((i+1) + "- " + players.get(i).toString());
                System.out.print(Color.RESET.getPrintColor() + "║");
            }
            else {
                System.out.print(Color.CYAN.getPrintColor());
                System.out.print((i+1) + "- " + players.get(i).toString());
                System.out.print(Color.RESET.getPrintColor() + "║");
            }
        }
        System.out.println();
        System.out.print("Direction: ");
        if (state.getDirection() == +1) {
            System.out.println(Color.PURPLE.getPrintColor() + "clockwise ↻");
        }
        else {
            System.out.println(Color.PURPLE.getPrintColor() + "anticlockwise ↺");
        }
        System.out.println(Color.RESET.getPrintColor() + "Turn: " + Color.PURPLE.getPrintColor() + players.get(state.getTurn()).getName() + Color.RESET.getPrintColor());
        System.out.println(currentCard.currToString());
        if (controls.get(state.getTurn()) instanceof PcControl) {
            System.out.print(players.get(state.getTurn()).backHandToString() + Color.RESET.getPrintColor());
        }
        else {
            System.out.print(players.get(state.getTurn()).handToString() + Color.RESET.getPrintColor());
        }
    }

    /**
     * Play game.
     * The method play game.
     */
    public void playGame() {
        show();
        while (gameStatus) {
            if (canPut(players.get((state.getTurn())))) {
                int choice = -1;
                while (gameStatus) {
                    choice = controls.get(state.getTurn()).putCard(currentCard);
                    if (validInput(players.get((state.getTurn())), choice)) {
                        System.out.println(players.get(state.getTurn()).getCards().get(choice).toString());
                        try{Thread.sleep(1000);}catch(InterruptedException ignored){}
                        break;
                    }
                }
                decide(players.get(state.getTurn()), players.get(state.getTurn()).getCards().get(choice));
            }
            try{Thread.sleep(500);}catch(InterruptedException ignored){}
            checkFinish();
            if (gameStatus) {
                state.nextTurn();
                show();
            }
        }
    }

    /**
     * decide to do witch movement with given player and choice card.
     *
     * @param player The player whose turn it is.
     * @param choiceCard the turn of game.
     */
    private void decide(Player player, Card choiceCard) {
        player.removeCard(choiceCard);
        //Wild Draw
        if (choiceCard instanceof WildDrawCard) {
            //set wildDraw color
            while (true) {
                char color = controls.get(state.getTurn()).chooseColor();
                if (color == 'R' || color == 'G' || color == 'Y' || color == 'B') {
                    ((WildDrawCard) choiceCard).setConfig(color);
                    break;
                }
                System.out.println("Invalid color input.");
            }
            //set wildDraw penalty
            char ch = 0;
            if(canChooseWildDrawCardPenalty(players.get(state.getNextTurn()))) {
                while (true) {
                    ch = controls.get(state.getNextTurn()).yesOrNo();
                    if (ch == 'Y' || ch == 'N'){
                        break;
                    }
                    System.out.println("Invalid input.");
                }
            }
            if (ch == 'N') {
                state.increaseWildDrawPenalty();
            }
            else {
                ((WildDrawCard) choiceCard).changeState(state);
                for (int i = 0; i < ((WildDrawCard) choiceCard).getForcedCards()*state.getWildDrawPenalty(); i++) {
                    players.get(state.getTurn()).addCard(cards.get(0));
                    cards.remove(0);
                }
                state.doWildDrawPenalty();
            }
        }

        //Wild
        else if (choiceCard instanceof WildCard) {
            while (true) {
                char color = controls.get(state.getTurn()).chooseColor();
                if (color == 'R' || color == 'G' || color == 'Y' || color == 'B') {
                    ((WildCard) choiceCard).setConfig(color);
                    break;
                }
                System.out.println("Invalid color input.");
            }
        }

        //Reverse
        if (choiceCard instanceof ReverseCard) {
            ((ReverseCard) choiceCard).changeState(state);
        }

        //Skip
        if (choiceCard instanceof SkipCard) {
            ((SkipCard) choiceCard).changeState(state);
        }

        //Draw2
        if (choiceCard instanceof Draw2Card) {
            char ch = 0;
            if(canChooseDrawCardPenalty(players.get(state.getNextTurn()))) {
                while (true) {
                    ch = controls.get(state.getNextTurn()).yesOrNo();
                    if (ch == 'Y' || ch == 'N'){
                        break;
                    }
                    System.out.println("Invalid input.");
                }
            }
            if (ch == 'N') {
                state.increaseDrawPenalty();
            }
            else {
                ((Draw2Card) choiceCard).changeState(state);
                for (int i = 0; i < ((Draw2Card) choiceCard).getForcedCards()*state.getDrawPenalty(); i++) {
                    players.get(state.getTurn()).addCard(cards.get(0));
                    cards.remove(0);
                }
                state.doDrawPenalty();
            }
        }
        changeCurrentCard(choiceCard);
    }

    /**
     * Determine that player can put a card with given player.
     *
     * @param player The player whose turn it is.
     * @return true if the player can put a card or can after giving one card, false otherwise.
     */
    private boolean canPut(Player player) {
        boolean haveValidCart = false;
        boolean can = false;
        for (Card temp: player.getCards()) {
            if(temp.isValidCard(currentCard) || temp instanceof WildCard) {
                haveValidCart = true;
                can = true;
                break;
            }
        }
        if (!haveValidCart) {
            player.addCard(cards.get(0));
            cards.remove(0);
            if (player.getCards().get(player.getCards().size()-1).isValidCard(currentCard)) {
                show();
                System.out.println(player.getName() + "! You were given a card because you didn't have any valid choice.");
                can = true;
            }
            else  {
                show();
                System.out.println(player.getName() + "! You were given a card but you don't have any valid choice yet.");
            }
        }
        return can;
    }

    /**
     * Determine validity of input with given player and choice.
     *
     * @param player player who choose.
     * @param choice index of hand cards.
     * @return true if the choice is valid for put, false otherwise.
     */
    private boolean validInput(Player player, int choice) {
        if (0 <= choice && choice < player.getCards().size()) {
            if(state.getWildDrawPenalty() != 1 && player.getCards().get(choice) instanceof WildDrawCard) {
                return true;
            }
            if( state.getWildDrawPenalty() != 1 && !(player.getCards().get(choice) instanceof WildDrawCard)) {
                System.out.println("You should put WildDraw card.");
                return false;
            }
            if( player.getCards().get(choice) instanceof WildDrawCard){
                boolean validWildDraw = true;
                for (Card temp: player.getCards()) {
                    if(temp.isValidCard(currentCard)) {
                        validWildDraw = false;
                    }
                }
                if (validWildDraw) {
                    return true;
                }
                else {
                    System.out.println("You can't put WildDraw card.");
                    return false;
                }
            }
            if(state.getDrawPenalty() != 1 && player.getCards().get(choice) instanceof  Draw2Card) {
                return true;
            }
            if (state.getDrawPenalty() != 1 && !(player.getCards().get(choice) instanceof  Draw2Card)) {
                System.out.println("You should put Draw2 card.");
                return false;
            }
            if ( player.getCards().get(choice).isValidCard(currentCard)) {
                return true;
            }
            System.out.println("Enter valid card.");
            return false;
        }
        System.out.println("Enter a number between 1-" + player.getCards().size() );
        return false;
    }

    /**
     * Determine that next player can choose draw+2 penalty with given next player.
     *
     * @param nextPlayer The next player whose turn it is.
     * @return true if the player can choose giving draw+2 penalty, false otherwise.
     */
    private boolean canChooseDrawCardPenalty(Player nextPlayer) {
        boolean can = false;
        for (Card temp : nextPlayer.getCards()) {
            if (temp instanceof Draw2Card) {
                can = true;
                break;
            }
        }
        if (!can) {
            System.out.println("The next player was fined.");
        }
        return can;
    }

    /**
     * Determine that next player can choose wildDraw penalty with given next player.
     *
     * @param nextPlayer The next player whose turn it is.
     * @return true if the player can choose giving wildDraw penalty, false otherwise.
     */
    private boolean canChooseWildDrawCardPenalty(Player nextPlayer) {
        boolean can = false;
        for (Card temp : nextPlayer.getCards()) {
            if (temp instanceof WildDrawCard) {
                can = true;
                break;
            }
        }
        if (!can) {
            System.out.println("The next player was fined.");
        }
        return can;
    }
}