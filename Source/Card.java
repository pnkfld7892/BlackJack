
public class Card {

    private final String face;
    private final String suit;
    private final int value;

    public Card(String cardFace, String cardSuit,int cardValue) {
        face = cardFace;
        suit = cardSuit;
        value = cardValue;
    }

    @Override
    public String toString() {
        return face + " of " + suit;
    }
    
    public int getValue()
    {
        return value;
    }
}
