package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StrategyTwo implements PlayerStrategy {

    public static ArrayList<Card> playerHand = new ArrayList<>();
    public static ArrayList<SetMeld> setMelds = new ArrayList<>();
    public static ArrayList<RunMeld> runMelds = new ArrayList<>();
    public static ArrayList<Card> deadwood = new ArrayList<>();

    /**
     * Called by the game engine for each player at the beginning of each round to receive and
     * process their initial hand dealt.
     *
     * @param hand The initial hand dealt to the player
     */
    public void receiveInitialHand(List<Card> hand){

        playerHand.addAll(hand);

        //create an array to keep track of how many cards there are and
        //any that have 3 or 4 create a setmeld

        int[] duplicates = new int[13];
        for (int i = 0; i < duplicates.length; i++){
            duplicates[i] = 0;
        }
        for (Card aPlayerHand : playerHand) {

            int value = aPlayerHand.getRankValue();
            duplicates[value]++;
        }

        for(int i = 0; i < duplicates.length; i++){
            if(duplicates[i] >= 3){
                ArrayList<Card> tempSetMeld = new ArrayList<>();

                for (Card aPlayerHand : playerHand) {
                    if (aPlayerHand.getRankValue() == i) {
                        tempSetMeld.add(aPlayerHand);

                    }
                }
                for (Card aTempSetMeld : tempSetMeld) {
                    playerHand.remove(aTempSetMeld);
                }


                SetMeld newMeld = Meld.buildSetMeld(tempSetMeld);
                if (newMeld != null) {
                    setMelds.add(newMeld);
                }
            }
        }


        //seperates the remaining cards into arraylists by suit

        ArrayList<Card> hearts = new ArrayList<>();
        ArrayList<Card> diamonds = new ArrayList<>();
        ArrayList<Card> spades = new ArrayList<>();
        ArrayList<Card> clubs = new ArrayList<>();

        for (Card aPlayerHand : playerHand) {
            if (aPlayerHand.getSuit() == Card.CardSuit.HEARTS) {
                hearts.add(aPlayerHand);
            } else if (aPlayerHand.getSuit() == Card.CardSuit.DIAMONDS) {
                diamonds.add(aPlayerHand);
            } else if (aPlayerHand.getSuit() == Card.CardSuit.SPADES) {
                spades.add(aPlayerHand);
            } else {
                clubs.add(aPlayerHand);
            }
        }
        Collections.sort(hearts);
        Collections.sort(diamonds);
        Collections.sort(spades);
        Collections.sort(clubs);

        //goes through the sorted arraylists and finds any that are three in a row
        //and creates a run meld

        if(hearts.size() >= 3){
            for(int i = 0; i < hearts.size() - 2; i++){
                if(hearts.get(i).getRankValue() == hearts.get(i + 1).getRankValue() - 1 &&
                        hearts.get(i).getRankValue() == hearts.get(i + 2).getRankValue() - 2){

                    ArrayList<Card> runMeld = new ArrayList<>();
                    runMeld.add(hearts.get(i));
                    runMeld.add(hearts.get(i + 1));
                    runMeld.add(hearts.get(i + 2));

                    boolean stillGoing = true;
                    while(stillGoing){
                        int m = 3;
                        if(hearts.size() <= i + m){
                            stillGoing = false;
                        } else {
                            if(hearts.get(i + m).getRankValue() == hearts.get(i).getRankValue() + m){
                                runMeld.add(hearts.get(i + m));
                                m++;
                                i++;
                            } else {
                                stillGoing = false;
                            }
                        }

                    }
                    RunMeld newMeld = Meld.buildRunMeld(runMeld);
                    runMelds.add(newMeld);
                    for (Card aRunMeld : runMeld) {
                        playerHand.remove(aRunMeld);
                    }
                }
                i += 2;
            }
        }

        if(diamonds.size() >= 3){
            for(int i = 0; i < diamonds.size() - 2; i++){
                if(diamonds.get(i).getRankValue() == diamonds.get(i + 1).getRankValue() - 1 &&
                        diamonds.get(i).getRankValue() == diamonds.get(i + 2).getRankValue() - 2){

                    ArrayList<Card> runMeld = new ArrayList<>();
                    runMeld.add(diamonds.get(i));
                    runMeld.add(diamonds.get(i + 1));
                    runMeld.add(diamonds.get(i + 2));

                    boolean stillGoing = true;
                    while(stillGoing){
                        int m = 3;
                        if(diamonds.size() <= i + m){
                            stillGoing = false;
                        } else {
                            if(diamonds.get(i + m).getRankValue() == diamonds.get(i).getRankValue() + m){
                                runMeld.add(diamonds.get(i + m));
                                m++;
                                i++;
                            } else {
                                stillGoing = false;
                            }
                        }

                    }
                    RunMeld newMeld = Meld.buildRunMeld(runMeld);
                    runMelds.add(newMeld);
                    for (Card aRunMeld : runMeld) {
                        playerHand.remove(aRunMeld);
                    }
                }
                i += 2;
            }
        }

        if(spades.size() >= 3){
            for(int i = 0; i < spades.size() - 2; i++){
                if(spades.get(i).getRankValue() == spades.get(i + 1).getRankValue() - 1 &&
                        spades.get(i).getRankValue() == spades.get(i + 2).getRankValue() - 2){

                    ArrayList<Card> runMeld = new ArrayList<>();
                    runMeld.add(spades.get(i));
                    runMeld.add(spades.get(i + 1));
                    runMeld.add(spades.get(i + 2));

                    boolean stillGoing = true;
                    while(stillGoing){
                        int m = 3;
                        if(spades.size() <= i + m){
                            stillGoing = false;
                        } else {
                            if(spades.get(i + m).getRankValue() == spades.get(i).getRankValue() + m){
                                runMeld.add(spades.get(i + m));
                                m++;
                                i++;
                            } else {
                                stillGoing = false;
                            }
                        }

                    }
                    RunMeld newMeld = Meld.buildRunMeld(runMeld);
                    runMelds.add(newMeld);
                    for (Card aRunMeld : runMeld) {
                        playerHand.remove(aRunMeld);
                    }
                }
                i += 2;
            }
        }

        if(clubs.size() >= 3){
            for(int i = 0; i < clubs.size() - 2; i++){
                if(clubs.get(i).getRankValue() == clubs.get(i + 1).getRankValue() - 1 &&
                        clubs.get(i).getRankValue() == clubs.get(i + 2).getRankValue() - 2){

                    ArrayList<Card> runMeld = new ArrayList<>();
                    runMeld.add(clubs.get(i));
                    runMeld.add(clubs.get(i + 1));
                    runMeld.add(clubs.get(i + 2));

                    boolean stillGoing = true;
                    while(stillGoing){
                        int m = 3;
                        if(clubs.size() <= i + m){
                            stillGoing = false;
                        } else {
                            if(clubs.get(i + m).getRankValue() == clubs.get(i).getRankValue() + m){
                                runMeld.add(clubs.get(i + m));
                                m++;
                                i++;
                            } else {
                                stillGoing = false;
                            }
                        }

                    }
                    RunMeld newMeld = Meld.buildRunMeld(runMeld);
                    runMelds.add(newMeld);
                    for (Card aRunMeld : runMeld) {
                        playerHand.remove(aRunMeld);
                    }
                }
                i += 2;
            }
        }

        //add the rest of the cards to deadwood


        deadwood.addAll(playerHand);
    }

    /**
     * Called by the game engine to prompt the player on whether they want to take the top card
     * from the discard pile or from the deck.
     *
     * @param card The card on the top of the discard pile
     * @return whether the user takes the card on the discard pile
     */
    public boolean willTakeTopDiscard(Card card){
        return Math.random() < .5;
    }

    /**
     * Called by the game engine to prompt the player to take their turn given a
     * dealt card (and returning their card they've chosen to discard).
     *
     * @param drawnCard The card the player was dealt
     * @return The card the player has chosen to discard
     */
    public Card drawAndDiscard(Card drawnCard){

        deadwood.add(drawnCard);
        Card throwaway = deadwood.get(0);
        for(int i = 0; i < deadwood.size(); i++){
            if(deadwood.get(i).getPointValue() > throwaway.getPointValue()){
                throwaway = deadwood.get(i);
            }
        }
        deadwood.remove(throwaway);
        return throwaway;
    }

    /**
     * Called by the game engine to prompt the player is whether they would like to
     * knock.
     *
     * @return True if the player has decided to knock
     */
    public boolean knock(){
        int totalDeadwood = 0;
        for(int i = 0; i < deadwood.size(); i++){
            totalDeadwood += deadwood.get(i).getPointValue();
        }
        if(totalDeadwood <= 10){
            return true;
        }
        return false;
    }

    /**
     * Called by the game engine when the opponent has finished their turn to provide the player
     * information on what the opponent just did in their turn.
     *
     * @param drewDiscard Whether the opponent took from the discard
     * @param previousDiscardTop What the opponent could have drawn from the discard if they chose to
     * @param opponentDiscarded The card that the opponent discarded
     */
    public void opponentEndTurnFeedback(boolean drewDiscard, Card previousDiscardTop, Card opponentDiscarded){

    }

    /**
     * Called by the game engine when the round has ended to provide this player strategy
     * information about their opponent's hand and selection of Melds at the end of the round.
     *
     * @param opponentHand The opponent's hand at the end of the round
     * @param opponentMelds The opponent's Melds at the end of the round
     */
    public void opponentEndRoundFeedback(List<Card> opponentHand, List<Meld> opponentMelds){

    }

    /**
     * Called by the game engine to allow access the player's current list of Melds.
     *
     * @return The player's list of melds.
     */
    public List<Meld> getMelds(){
        ArrayList<Meld> melds = new ArrayList<Meld>();
        for(int i = 0; i < setMelds.size(); i++){
            melds.add(setMelds.get(i));
        }
        for(int i = 0; i < runMelds.size(); i++){
            melds.add(runMelds.get(i));
        }
        return melds;

    }

    /**
     * Called by the game engine to allow this player strategy to reset its internal state before
     * competing it against a new opponent.
     */
    public void reset(){

    }
}
