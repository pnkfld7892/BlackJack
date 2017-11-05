
import java.util.Scanner;
import java.util.ArrayList;
import java.security.SecureRandom;

public class Blackjack {

    //class variables that will get passed around many methods
    public static final int NUM_CARDS = 52;
    public static final int DECK_SHOE = 4;
    public static SecureRandom rand = new SecureRandom();
    public static ArrayList<Card> playerHand = new ArrayList<>();
    public static ArrayList<Card> dealerHand = new ArrayList<>();
    public static Deck[] shoe = new Deck[DECK_SHOE];
    public static Scanner in = new Scanner(System.in);
    public static boolean pBust = false;
    public static boolean dBust = false;
    public static boolean pJack = false;
    public static boolean dJack = false;

    public static void main(String[] args) {

        int card = 0, handTotal = 0;
        char cont = 'Y';

        for (int i = 0; i < shoe.length; i++) {
            shoe[i] = new Deck();
            shoe[i].shuffleDeck();
        }

        while (cont == 'Y') {
            initalDeal();
            playerTurn();
            if (pBust == true) {
                System.out.println("The House Wins!");
            } else {
                dealerTurn();
                if (dBust == true) {
                    System.out.println("The Dealer has bust! You Win");
                } else {
                    Showdown();
                }
            }

            System.out.print("Do you wish to play another hand?(Y/N) ");
            cont = in.next().toUpperCase().charAt(0);
            if (cont == 'Y') {
                reset();
            }
        }

//        playerHand = hit(playerHand);
//        
//        for (int i = 0; i < playerHand.size(); i++) {
//           System.out.printf("%s\t%s\n",playerHand.get(i),playerHand.get(i).getValue()); 
//        }
    }

    public static int hit(ArrayList<Card> hand, int total) {
        Card card = new Card("Will", "This Work", 0);
        card = shoe[rand.nextInt(DECK_SHOE)].dealCard(rand.nextInt(NUM_CARDS));
        hand.add(card);
        total += card.getValue();
        System.out.printf("%s\n", card);
        return total;
    }

    private static void initalDeal() {
        for (int i = 0; i < 2; i++) {
            playerHand.add(shoe[rand.nextInt(DECK_SHOE)].dealCard(rand.nextInt(NUM_CARDS)));
            dealerHand.add(shoe[rand.nextInt(DECK_SHOE)].dealCard(rand.nextInt(NUM_CARDS)));
        }
        System.out.printf("The Cards are dealt\n\nThe Dealer's Face card is: %s\n\n",
                dealerHand.get(0));

        checkBJack(dealerHand, 'D');

//        System.out.printf("Your Cards are\n%s\n%s\nYour Total Value is: %d\n"
//        ,playerHand.get(0),playerHand.get(1),(playerHand.get(0).getValue() + playerHand.get(1).getValue()));
        checkBJack(playerHand, 'P');

    }

    private static void playerTurn() {
        char hS = ' ';
        int total = 0;
        System.out.printf("Your Cards are\n");
        for (Card ph1 : playerHand) {
            System.out.printf("%s\n", ph1.toString());
            total += ph1.getValue();
        }
        System.out.printf("Your Total is: %d\n", total);

        while (hS != 'S' && pBust != true) {
            System.out.print("It is your turn will you hit or stay(H/S): ");
            hS = in.next().trim().toUpperCase().charAt(0);
            if (hS == 'H') {
                System.out.print("You hit and are dealt ");
                total = hit(playerHand, total);
                //System.out.printf("\n");

                System.out.printf("Your New Total is: %d\n", total);

            } else {
                System.out.println("\nYou elect to stay, it is now the dealers turn.\n");
            }

            checkBust(playerHand, 'P');
            checkBJack(playerHand, 'P');
            if (pBust == true) {
                System.out.println("\nSorry you bust!");
            }
        }
    }

    private static void checkBust(ArrayList<Card> hand, char play) {
        int total = 0;
        for (Card hand1 : hand) {
            total += hand1.getValue();
        }

        if (total > 21 && play == 'P') {
            pBust = true;
        } else if (total > 21 && play == 'D') {
            dBust = true;
        }
    }

    private static void checkBJack(ArrayList<Card> hand, char play) {
        int total = 0;
        for (Card hand1 : hand) {
            total += hand1.getValue();
        }

        if (total == 21 && play == 'P') {
            pJack = true;
        } else if (total == 21 && play == 'D') {
            dJack = true;
        }
    }

    //clears hands and reshuffles the shoe
    public static void reset() {
        playerHand.clear();
        dealerHand.clear();
        
        for (int i = 0; i < shoe.length; i++) {
            shoe[i] = new Deck();
            shoe[i].shuffleDeck();
        }
    }

    private static void dealerTurn() {
        int total = 0;
        //get initial total for dealer
        for (Card card : dealerHand) {
            total += card.getValue();
        }
        //messages and stuff
        System.out.println("It is Now the Dealer's Turn");
        System.out.printf("The Dealer's Hole Card is the: %s\n"
                + "The Dealer's Inital Total is: %d\n", dealerHand.get(1), total);
        
        //the dealer must hit if below card total of 16
        if (total <= 16) {
            while (total <= 16) {
                System.out.print("The Dealer Hits and is dealt: ");
                total = hit(dealerHand, total);
                System.out.printf("The Dealer's new total is: %d\n", total);
                checkBust(dealerHand, 'D');
            }
        }

    }

    private static void Showdown() {
        int dTotal = 0, pTotal = 0;
        for (Card card : playerHand) {
            pTotal += card.getValue();

        }
        for (Card card : dealerHand) {
            dTotal += card.getValue();

        }

        if (pTotal > dTotal) {
            System.out.println("\nYou Win!");
        } else if (dTotal > pTotal) {
            System.out.println("\nThe House Wins!");
        } else if (pTotal == dTotal) {
            if (playerHand.size() < dealerHand.size()) {
                System.out.println("\nYou Win!");
            } else if (playerHand.size() == dealerHand.size()) {
                System.out.println("\nPush, Neither wins this hand!");
            } else {
                System.out.println("\nThe House Wins!");
            }
        }
    }

}
