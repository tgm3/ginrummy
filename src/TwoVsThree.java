package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TwoVsThree {

    public static void twoVsThree(int numberOfGames){
        int playerTwoWins = 0;
        int playerThreeWins = 0;
        int gameCount = 0;

        while(gameCount < numberOfGames){
            int playerTwoScore = 0;
            int playerThreeScore = 0;

            boolean isPlaying = true;
            while (isPlaying){

                ArrayList<Card> discardPile = new ArrayList<>();
                ArrayList<Card> deck = new ArrayList<>(Card.getAllCards());

                Collections.shuffle(deck);

                ArrayList<Card> playerTwoHand = new ArrayList<>();
                ArrayList<Card> playerThreeHand = new ArrayList<>();
                discardPile.add(deck.get(0));
                deck.remove(0);

                for (int i = 0; i < 10; i++){
                    playerTwoHand.add(deck.get(0));
                    deck.remove(0);
                }

                for (int i = 0; i < 10; i++){
                    playerThreeHand.add(deck.get(0));
                    deck.remove(0);
                }
                StrategyTwo playerTwo = new StrategyTwo();
                StrategyThree playerThree = new StrategyThree();
                playerTwo.receiveInitialHand(playerTwoHand);
                playerThree.receiveInitialHand(playerThreeHand);

                boolean sameRound = true;



                while(sameRound) {


                    if(playerTwo.willTakeTopDiscard(discardPile.get(0))){

                        discardPile.add(playerTwo.drawAndDiscard(discardPile.get(0)));
                        discardPile.remove(0);
                    } else {

                        discardPile.add(playerTwo.drawAndDiscard(deck.get(0)));
                        deck.remove(0);
                    }

                    if(playerTwo.knock()){
                        int playerTwoDeadwood = 0;
                        for(int i = 0; i < StrategyTwo.deadwood.size(); i++){
                            playerTwoDeadwood += StrategyTwo.deadwood.get(i).getPointValue();
                        }
                        int playerThreeDeadwood = 0;
                        for(int i = 0; i < StrategyThree.deadwood.size(); i++){
                            playerThreeDeadwood += StrategyThree.deadwood.get(i).getPointValue();
                        }
                        if(playerTwoDeadwood == 0){
                            playerTwoScore += (25 + playerThreeDeadwood);

                        } else if (playerTwoDeadwood <= playerThreeDeadwood){
                            playerTwoScore += (playerThreeDeadwood - playerTwoDeadwood);

                        } else {
                            playerThreeScore += (25 + playerTwoDeadwood);

                        }
                        sameRound = false;
                        if(playerTwoScore >= 50){
                            isPlaying = false;
                            playerTwoWins++;
                            gameCount++;
                        }
                        if(playerThreeScore >= 50){
                            isPlaying = false;
                            playerThreeWins++;
                            gameCount++;
                        }
                    }


                    if(discardPile.size() == 0){
                        discardPile.add(deck.get(0));
                        deck.remove(0);
                    }
                    if(playerThree.willTakeTopDiscard(discardPile.get(0))){

                        discardPile.add(playerThree.drawAndDiscard(discardPile.get(0)));
                        discardPile.remove(0);
                    } else {

                        discardPile.add(playerThree.drawAndDiscard(deck.get(0)));
                        deck.remove(0);
                    }

                    if(playerThree.knock()){
                        int playerTwoDeadwood = 0;
                        for(int i = 0; i < StrategyTwo.deadwood.size(); i++){
                            playerTwoDeadwood += StrategyTwo.deadwood.get(i).getPointValue();
                        }
                        int playerThreeDeadwood = 0;
                        for(int i = 0; i < StrategyThree.deadwood.size(); i++){
                            playerThreeDeadwood += StrategyThree.deadwood.get(i).getPointValue();
                        }
                        if(playerThreeDeadwood == 0){
                            playerThreeScore += (25 + playerTwoDeadwood);

                        } else if (playerThreeDeadwood <= playerTwoDeadwood){
                            playerThreeScore += (playerTwoDeadwood - playerThreeDeadwood);

                        } else {
                            playerTwoScore += (25 + playerThreeDeadwood);

                        }
                        sameRound = false;
                        if(playerTwoScore >= 50){
                            isPlaying = false;
                            playerTwoWins++;
                            gameCount++;
                        }
                        if(playerThreeScore >= 50){
                            isPlaying = false;
                            playerThreeWins++;
                            gameCount++;
                        }
                    }
                    ArrayList<Card> playerTwoCards = new ArrayList<>();
                    ArrayList<Card> playerThreeCards = new ArrayList<>();

                    for(int i = 0; i < StrategyTwo.setMelds.size(); i++){
                        playerTwoCards.addAll(Arrays.asList(StrategyTwo.setMelds.get(i).getCards()));
                    }
                    for(int i = 0; i < StrategyThree.setMelds.size(); i++){
                        playerThreeCards.addAll(Arrays.asList(StrategyThree.setMelds.get(i).getCards()));
                    }
                    for(int i = 0; i < StrategyTwo.runMelds.size(); i++){
                        playerTwoCards.addAll(Arrays.asList(StrategyTwo.runMelds.get(i).getCards()));
                    }
                    for(int i = 0; i < StrategyThree.runMelds.size(); i++){
                        playerThreeCards.addAll(Arrays.asList(StrategyThree.runMelds.get(i).getCards()));
                    }
                    playerTwoCards.addAll(StrategyTwo.deadwood);
                    playerThreeCards.addAll(StrategyThree.deadwood);

                    StrategyTwo.deadwood.clear();
                    StrategyTwo.setMelds.clear();
                    StrategyTwo.runMelds.clear();
                    StrategyTwo.playerHand.clear();
                    StrategyThree.deadwood.clear();
                    StrategyThree.setMelds.clear();
                    StrategyThree.runMelds.clear();
                    StrategyThree.playerHand.clear();

                    playerTwo.receiveInitialHand(playerTwoCards);
                    playerThree.receiveInitialHand(playerThreeCards);

                    if(deck.size() == 0 || deck.size() == 1){
                        sameRound = false;
                    }

                }



                //playerOne.reset();
                //playerTwo.reset();
            }
        }
        System.out.println("p2 wins: " + playerTwoWins);
        System.out.println("p3 wins: " + playerThreeWins);
    }
}
