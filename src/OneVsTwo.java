package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class OneVsTwo {

    public static void oneVsTwo(int numberOfGames){
        int playerOneWins = 0;
        int playerTwoWins = 0;
        int gameCount = 0;

        while(gameCount < numberOfGames){
            int playerOneScore = 0;
            int playerTwoScore = 0;

            boolean isPlaying = true;
            while (isPlaying){

                ArrayList<Card> discardPile = new ArrayList<>();
                ArrayList<Card> deck = new ArrayList<>(Card.getAllCards());

                Collections.shuffle(deck);

                ArrayList<Card> playerOneHand = new ArrayList<>();
                ArrayList<Card> playerTwoHand = new ArrayList<>();
                discardPile.add(deck.get(0));
                deck.remove(0);

                for (int i = 0; i < 10; i++){
                    playerOneHand.add(deck.get(0));
                    deck.remove(0);
                }

                for (int i = 0; i < 10; i++){
                    playerTwoHand.add(deck.get(0));
                    deck.remove(0);
                }
                StrategyOne playerOne = new StrategyOne();
                StrategyTwo playerTwo = new StrategyTwo();
                playerOne.receiveInitialHand(playerOneHand);
                playerTwo.receiveInitialHand(playerTwoHand);

                boolean sameRound = true;



                while(sameRound) {


                    if(playerOne.willTakeTopDiscard(discardPile.get(0))){

                        discardPile.add(playerOne.drawAndDiscard(discardPile.get(0)));
                        discardPile.remove(0);
                    } else {

                        discardPile.add(playerOne.drawAndDiscard(deck.get(0)));
                        deck.remove(0);
                    }

                    if(playerOne.knock()){
                        int playerOneDeadwood = 0;
                        for(int i = 0; i < StrategyOne.deadwood.size(); i++){
                            playerOneDeadwood += StrategyOne.deadwood.get(i).getPointValue();
                        }
                        int playerTwoDeadwood = 0;
                        for(int i = 0; i < StrategyTwo.deadwood.size(); i++){
                            playerTwoDeadwood += StrategyTwo.deadwood.get(i).getPointValue();
                        }
                        if(playerOneDeadwood == 0){
                            playerOneScore += (25 + playerTwoDeadwood);

                        } else if (playerOneDeadwood <= playerTwoDeadwood){
                            playerOneScore += (playerTwoDeadwood - playerOneDeadwood);

                        } else {
                            playerTwoScore += (25 + playerOneDeadwood);

                        }
                        sameRound = false;
                        if(playerOneScore >= 50){
                            isPlaying = false;
                            playerOneWins++;
                            gameCount++;
                        }
                        if(playerTwoScore >= 50){
                            isPlaying = false;
                            playerTwoWins++;
                            gameCount++;
                        }
                    }

                    //playerTwo.opponentEndTurnFeedback();

                    if(playerTwo.willTakeTopDiscard(discardPile.get(0))){

                        discardPile.add(playerTwo.drawAndDiscard(discardPile.get(0)));
                        discardPile.remove(0);
                    } else {

                        discardPile.add(playerTwo.drawAndDiscard(deck.get(0)));
                        deck.remove(0);
                    }

                    if(playerTwo.knock()){
                        int playerOneDeadwood = 0;
                        for(int i = 0; i < StrategyOne.deadwood.size(); i++){
                            playerOneDeadwood += StrategyOne.deadwood.get(i).getPointValue();
                        }
                        int playerTwoDeadwood = 0;
                        for(int i = 0; i < StrategyTwo.deadwood.size(); i++){
                            playerTwoDeadwood += StrategyTwo.deadwood.get(i).getPointValue();
                        }
                        if(playerTwoDeadwood == 0){
                            playerTwoScore += (25 + playerOneDeadwood);

                        } else if (playerTwoDeadwood <= playerOneDeadwood){
                            playerTwoScore += (playerOneDeadwood - playerTwoDeadwood);

                        } else {
                            playerOneScore += (25 + playerTwoDeadwood);

                        }
                        sameRound = false;
                        if(playerOneScore >= 50){
                            isPlaying = false;
                            playerOneWins++;
                            gameCount++;
                        }
                        if(playerTwoScore >= 50){
                            isPlaying = false;
                            playerTwoWins++;
                            gameCount++;
                        }
                    }
                    ArrayList<Card> playerOneCards = new ArrayList<>();
                    ArrayList<Card> playerTwoCards = new ArrayList<>();

                    for(int i = 0; i < StrategyOne.setMelds.size(); i++){
                        playerOneCards.addAll(Arrays.asList(StrategyOne.setMelds.get(i).getCards()));
                    }
                    for(int i = 0; i < StrategyTwo.setMelds.size(); i++){
                        playerTwoCards.addAll(Arrays.asList(StrategyTwo.setMelds.get(i).getCards()));
                    }
                    for(int i = 0; i < StrategyOne.runMelds.size(); i++){
                        playerOneCards.addAll(Arrays.asList(StrategyOne.runMelds.get(i).getCards()));
                    }
                    for(int i = 0; i < StrategyTwo.runMelds.size(); i++){
                        playerTwoCards.addAll(Arrays.asList(StrategyTwo.runMelds.get(i).getCards()));
                    }
                    playerOneCards.addAll(StrategyOne.deadwood);
                    playerTwoCards.addAll(StrategyTwo.deadwood);

                    StrategyOne.deadwood.clear();
                    StrategyOne.setMelds.clear();
                    StrategyOne.runMelds.clear();
                    StrategyOne.playerHand.clear();
                    StrategyTwo.deadwood.clear();
                    StrategyTwo.setMelds.clear();
                    StrategyTwo.runMelds.clear();
                    StrategyTwo.playerHand.clear();

                    playerOne.receiveInitialHand(playerOneCards);
                    playerTwo.receiveInitialHand(playerTwoCards);

                    if(deck.size() == 0 || deck.size() == 1){
                        sameRound = false;
                    }

                }



                //playerOne.reset();
                //playerTwo.reset();
            }
        }
        System.out.println("p1 wins: " + playerOneWins);
        System.out.println("p2 wins: " + playerTwoWins);
    }
}
