
import java.security.SecureRandom;
import java.util.Arrays;

public class Deck {

    private Card[] deck;
    private int value;
    private int currentCard;
    private static final int NUMBER_OF_CARDS = 52;

    private static final SecureRandom rand = new SecureRandom();

    //constructor
    public Deck() {
        String[] faces = {"Ace", "Deuce", "Three", "Four", "Five", "Six",
            "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

        deck = new Card[NUMBER_OF_CARDS];
        //value = new int[NUMBER_OF_CARDS];
        currentCard = 0;

        for (int count = 0; count < deck.length; count++) {
            
            switch (faces[count % 13])
            {
                case "Ace":
                    value = 1;
                    break;
                    case "Deuce":
                    value = 2;
                    break;
                    case "Three":
                    value = 3;
                    break;
                    case "Four":
                    value = 4;
                    break;
                    case "Five":
                    value = 5;
                    break;
                    case "Six":
                    value = 6;
                    break;
                    case "Seven":
                    value = 7;
                    break;
                    case "Eight":
                    value = 8;
                    break;
                    case "Nine":
                    value = 9;
                    break;
                    case "Ten":
                    value = 10;
                    break;
                    case "Jack": case "Queen": case "King":
                    value = 10;
                    break;
            }
            deck[count] = new Card(faces[count % 13], suits[count / 13],value);
            

        }
    }

    public void shuffleDeck() {
        currentCard = 0;

        for (int first = 0; first < deck.length; first++) {
            int second = rand.nextInt(NUMBER_OF_CARDS);
            
            
            Card temp = deck[first];
            deck[first] = deck[second];
            deck[second] = temp;
            
//            int tempNum = value[first];
//            value[first] = value[second];
//            value[second] = tempNum;
        }
    }

    public Card dealCard(int card) {
        if (card < deck.length) {
            return deck[card];
        } else {
            return null;
        }
    }
    
    public int getValue(int card)
    {
        return deck[card].getValue();
    }
    

    public Card getCard(int i) {
        return deck[i];
    }
}
