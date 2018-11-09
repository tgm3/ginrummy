
import com.example.Card;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {

    private static Card cardOne;
    private static Card cardTwo;
    private static ArrayList<Card> cardSet = new ArrayList<>();

    @org.junit.Before
    public void setUp() {

        cardOne = new Card(Card.CardSuit.HEARTS, Card.CardRank.ACE);
        cardTwo = new Card(Card.CardSuit.HEARTS, Card.CardRank.TWO);
        cardSet.add(cardOne);
        cardSet.add(cardTwo);

    }

    @org.junit.Test
    public void getRankValue() {
        assertEquals(0, cardOne.getRankValue());
    }

    @org.junit.Test
    public void getRank() {
        assertEquals(Card.CardRank.ACE, cardOne.getRank());
    }

    @org.junit.Test
    public void getSuit() {
        assertEquals(Card.CardSuit.HEARTS, cardOne.getSuit());
    }

    @org.junit.Test
    public void getPointValue() {
        assertEquals(1, cardOne.getPointValue());
    }

    @org.junit.Test
    public void getCardToString() {
        assertEquals("ACE HEARTS", cardOne.toString());
    }

    @org.junit.Test
    public void getAllCards() {
        ArrayList<Card> testCards = new ArrayList<>();
        testCards.add(cardOne);
        testCards.add(cardTwo);
        assertArrayEquals(testCards.toArray(), cardSet.toArray());
    }

}
