package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class OneVsThree {

    public static void oneVsThree(int numberOfGames){
        int playerOneWins = 0;
        int playerThreeWins = 0;
        int gameCount = 0;

        while(gameCount < numberOfGames){
            int playerOneScore = 0;
            int playerThreeScore = 0;

            boolean isPlaying = true;
            while (isPlaying){

                ArrayList<Card> discardPile = new ArrayList<>();
                ArrayList<Card> deck = new ArrayList<>(Card.getAllCards());

                Collections.shuffle(deck);

                ArrayList<Card> playerOneHand = new ArrayList<>();
                ArrayList<Card> playerThreeHand = new ArrayList<>();
                discardPile.add(deck.get(0));
                deck.remove(0);

                for (int i = 0; i < 10; i++){
                    playerOneHand.add(deck.get(0));
                    deck.remove(0);
                }

                for (int i = 0; i < 10; i++){
                    playerThreeHand.add(deck.get(0));
                    deck.remove(0);
                }
                StrategyOne playerOne = new StrategyOne();
                StrategyThree playerThree = new StrategyThree();
                playerOne.receiveInitialHand(playerOneHand);
                playerThree.receiveInitialHand(playerThreeHand);

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
                        int playerThreeDeadwood = 0;
                        for(int i = 0; i < StrategyThree.deadwood.size(); i++){
                            playerThreeDeadwood += StrategyThree.deadwood.get(i).getPointValue();
                        }
                        if(playerOneDeadwood == 0){
                            playerOneScore += (25 + playerThreeDeadwood);

                        } else if (playerOneDeadwood <= playerThreeDeadwood){
                            playerOneScore += (playerThreeDeadwood - playerOneDeadwood);

                        } else {
                            playerThreeScore += (25 + playerOneDeadwood);

                        }
                        sameRound = false;
                        if(playerOneScore >= 50){
                            isPlaying = false;
                            playerOneWins++;
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
                        int playerOneDeadwood = 0;
                        for(int i = 0; i < StrategyOne.deadwood.size(); i++){
                            playerOneDeadwood += StrategyOne.deadwood.get(i).getPointValue();
                        }
                        int playerThreeDeadwood = 0;
                        for(int i = 0; i < StrategyThree.deadwood.size(); i++){
                            playerThreeDeadwood += StrategyThree.deadwood.get(i).getPointValue();
                        }
                        if(playerThreeDeadwood == 0){
                            playerThreeScore += (25 + playerOneDeadwood);

                        } else if (playerThreeDeadwood <= playerOneDeadwood){
                            playerThreeScore += (playerOneDeadwood - playerThreeDeadwood);

                        } else {
                            playerOneScore += (25 + playerThreeDeadwood);

                        }
                        sameRound = false;
                        if(playerOneScore >= 50){
                            isPlaying = false;
                            playerOneWins++;
                            gameCount++;
                        }
                        if(playerThreeScore >= 50){
                            isPlaying = false;
                            playerThreeWins++;
                            gameCount++;
                        }
                    }
                    ArrayList<Card> playerOneCards = new ArrayList<>();
                    ArrayList<Card> playerThreeCards = new ArrayList<>();

                    for(int i = 0; i < StrategyOne.setMelds.size(); i++){
                        playerOneCards.addAll(Arrays.asList(StrategyOne.setMelds.get(i).getCards()));
                    }
                    for(int i = 0; i < StrategyThree.setMelds.size(); i++){
                        playerThreeCards.addAll(Arrays.asList(StrategyThree.setMelds.get(i).getCards()));
                    }
                    for(int i = 0; i < StrategyOne.runMelds.size(); i++){
                        playerOneCards.addAll(Arrays.asList(StrategyOne.runMelds.get(i).getCards()));
                    }
                    for(int i = 0; i < StrategyThree.runMelds.size(); i++){
                        playerThreeCards.addAll(Arrays.asList(StrategyThree.runMelds.get(i).getCards()));
                    }
                    playerOneCards.addAll(StrategyOne.deadwood);
                    playerThreeCards.addAll(StrategyThree.deadwood);

                    StrategyOne.deadwood.clear();
                    StrategyOne.setMelds.clear();
                    StrategyOne.runMelds.clear();
                    StrategyOne.playerHand.clear();
                    StrategyThree.deadwood.clear();
                    StrategyThree.setMelds.clear();
                    StrategyThree.runMelds.clear();
                    StrategyThree.playerHand.clear();

                    playerOne.receiveInitialHand(playerOneCards);
                    playerThree.receiveInitialHand(playerThreeCards);

                    if(deck.size() == 0 || deck.size() == 1){
                        sameRound = false;
                    }

                }

            }
        }
        System.out.println("p2 wins: " + playerOneWins);
        System.out.println("p3 wins: " + playerThreeWins);
    }
}
