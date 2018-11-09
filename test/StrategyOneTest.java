import com.example.*;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StrategyOneTest {

    @org.junit.Test
    public void getSetMeldOfThree() {
        StrategyOne playerOne = new StrategyOne();
        ArrayList<Card> deck = new ArrayList<>(Card.getAllCards());
        Collections.sort(deck);
        System.out.println(deck.toString());
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(deck.get(0));
        hand.add(deck.get(1));
        hand.add(deck.get(2));
        playerOne.receiveInitialHand(hand);
        SetMeld setMeld = Meld.buildSetMeld(hand);
        assertArrayEquals(setMeld.getCards(), StrategyOne.setMelds.get(0).getCards());


    }

    @org.junit.Test
    public void getSetMeldOfFour() {
        StrategyOne playerOne = new StrategyOne();
        ArrayList<Card> deck = new ArrayList<>(Card.getAllCards());
        Collections.sort(deck);
        System.out.println(deck.toString());
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(deck.get(0));
        hand.add(deck.get(1));
        hand.add(deck.get(2));
        hand.add(deck.get(3));
        playerOne.receiveInitialHand(hand);
        SetMeld setMeld = Meld.buildSetMeld(hand);
        assertArrayEquals(setMeld.getCards(), StrategyOne.setMelds.get(0).getCards());


    }

    @org.junit.Test
    public void getSetMeldOfTwo() {
        StrategyOne playerOne = new StrategyOne();
        ArrayList<Card> deck = new ArrayList<>(Card.getAllCards());
        Collections.sort(deck);
        System.out.println(deck.toString());
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(deck.get(0));
        hand.add(deck.get(1));
        playerOne.receiveInitialHand(hand);
        assertEquals(0, StrategyOne.setMelds.size());


    }

    @org.junit.Test
    public void getRunMeldOfThree() {
        StrategyOne playerOne = new StrategyOne();
        ArrayList<Card> deck = new ArrayList<>(Card.getAllCards());
        Collections.sort(deck);
        System.out.println(deck.toString());
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(deck.get(0));
        hand.add(deck.get(7));
        hand.add(deck.get(9));
        playerOne.receiveInitialHand(hand);
        RunMeld runMeld = Meld.buildRunMeld(hand);
        assertArrayEquals(runMeld.getCards(), StrategyOne.runMelds.get(0).getCards());


    }

    @org.junit.Test
    public void getRunMeldOfFour() {
        StrategyOne playerOne = new StrategyOne();
        ArrayList<Card> deck = new ArrayList<>(Card.getAllCards());
        Collections.sort(deck);
        System.out.println(deck.toString());
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(deck.get(0));
        hand.add(deck.get(7));
        hand.add(deck.get(9));
        hand.add(deck.get(13));
        playerOne.receiveInitialHand(hand);
        RunMeld runMeld = Meld.buildRunMeld(hand);
        assertArrayEquals(runMeld.getCards(), StrategyOne.runMelds.get(0).getCards());


    }

    @org.junit.Test
    public void getRunMeldOfTwo() {
        StrategyOne playerOne = new StrategyOne();
        ArrayList<Card> deck = new ArrayList<>(Card.getAllCards());
        Collections.sort(deck);
        System.out.println(deck.toString());
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(deck.get(0));
        hand.add(deck.get(7));
        playerOne.receiveInitialHand(hand);
        assertEquals(0, StrategyOne.runMelds.size());


    }

    @org.junit.Test
    public void getRunMeldOfDifferentSuits() {
        StrategyOne playerOne = new StrategyOne();
        ArrayList<Card> deck = new ArrayList<>(Card.getAllCards());
        Collections.sort(deck);
        System.out.println(deck.toString());
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(deck.get(0));
        hand.add(deck.get(4));
        hand.add(deck.get(8));
        playerOne.receiveInitialHand(hand);
        assertEquals(0, StrategyOne.runMelds.size());


    }

    @org.junit.Test
    public void addToDeadwood() {
        StrategyOne playerOne = new StrategyOne();
        ArrayList<Card> deck = new ArrayList<>(Card.getAllCards());
        Collections.sort(deck);
        System.out.println(deck.toString());
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(deck.get(0));
        playerOne.receiveInitialHand(hand);
        assertEquals(1, StrategyOne.deadwood.size());


    }

}
