package com.company;

import java.util.Arrays;

//A class that consists of all the combinations of cards in Poker
public class PlayerCardRank {
    private int result;

    //A constructor to initialize the default value
    public PlayerCardRank()
    {
        result = 0;
    }

    //A function to check if the set of player cards is a Royal Flush
    public int isRoyalFlush(int[] number,char[] suit)
    {
        int checkSuit=0;
        int checkSequence=0;
        checkSuit = sameSuit(suit);
        checkSequence = highestConsecutiveOrder(number);
        if(checkSuit==1 && checkSequence==1)
            result=1;
        else
            result=0;
        return result;
    }

    //A function to check if the set of player cards is a Flush
    public int isFlush(char[] suit)
    {
        result = sameSuit(suit);
        return result;
    }

    //A function to check if the set of player cards is a Straight Flush
    public int isStraightFlush(int[] number,char[] suit)
    {
        int checkSuit=0;
        int checkSequence=0;
        checkSuit = sameSuit(suit);
        checkSequence = consecutiveOrder(number);
        if(checkSuit==1 && checkSequence==1)
            result=1;
        else
            result=0;
        return result;
    }

    //A function to check if the set of player cards is a Four of a Kind
    //It calculates the frequency of all the cards and if a card has the frequency of 4, then the result is 1
    public int isFourOfAKind(int[] frequency)
    {
        for(int i = 0; i < 4;)
        {
            if(frequency[i] == 4)
            {
                result = 1;
                break;
            }

            else
                i++;
        }
        return result;
    }

    //A function to check if the set of player cards is a Three of a Kind
    //It calculates the frequency of all the cards and if a card has the frequency of 3, then the result is 1
    public int isThreeOfAKind(int[] frequency)
    {
        for(int i = 0; i < 4;)
        {
            if(frequency[i] == 3)
            {
                result = 1;
                break;
            }
            else
                i++;
        }
        return result;
    }

    //A function to check if the set of player cards is Straight
    //It calls the consecutive order function to check if the condition for Straight is satisfied
    public int isStraight(int[] number)
    {
        result = consecutiveOrder(number);
        return result;
    }

    //A function to check if the set of player cards is a Pair
    //It calculates the frequency of all the cards and if a card has the frequency of 2, then the result is 1
    public int isPair(int[] frequency)
    {
        for(int i = 0; i < 4;)
        {
            if(frequency[i] == 2)
            {
                result = 1;
                break;
            }
            else
                i++;
        }
        return result;
    }

    //A function to check if the set of player cards is Two Pair
    //It calculates the frequency of all the cards and if two cards have the frequency of 2, then the result is 1
    public int isTwoPair(int[] frequency)
    {
        int count=0;
        for(int i = 0; i < 5;i++)
        {
            if(frequency[i] == 2)
            {
                count++;
            }
        }
        if(count==2)
            result=1;
        return result;
    }

    //A function to check if the set of player cards is a Full House
    //It checks if the cards are three of a kind and a pair, then the result is 1
    public int isFullHouse(int[] number)
    {
        Poker poker = new Poker();
        int result=0;
        int checkForThreeOfAKind = isThreeOfAKind(poker.calcFrequency(number));
        int checkForPair = isPair(poker.calcFrequency(number));
        if(checkForPair == 1 && checkForThreeOfAKind == 1)
            result = 1;
        return result;
    }

    //A function to check if the set of player cards is a High Card
    //It calculates the frequency of all the cards and if the frequency of all cards is 1, then the result is 1
    public int isHighCard(int[] frequency)
    {
        for(int i = 0; i < 5;)
        {
            if(frequency[i] == 1)
            {
                i++;
                result=1;
            }
            else
            {
                result=0;
                break;
            }
        }
        return result;
    }

    //A function to calculate if the player cards are in a consecutive order
    public int consecutiveOrder(int[] number)
    {
        Arrays.sort(number);
        for(int i=0;i<4;)
        {
            if(number[i+1] == (number[i]+1)) {
                i++;
                result = 1;
            }
            else {
                result = 0;
                break;
            }
        }
        return result;
    }

    //A function to calculate if the player cards are in a consecutive order from ten to ace being the highest
    public int highestConsecutiveOrder(int[] number)
    {
        int expectedSequence[] = {10,11,12,13,14};
        Arrays.sort(number);
        for(int i=0;i<4;)
        {
            if(number[i] == expectedSequence[i]) {
                i++;
                result = 1;
            }
            else {
                result = 0;
                break;
            }
        }
        return result;
    }

    //A function to check if all the player cards belong to the same suit or not
    public int sameSuit(char suit[])
    {
        for(int i=0;i<4;)
        {
            if(suit[i]==suit[i+1])
            {
                i++;
                result = 1;
            }
            else
            {
                result=0;
                break;
            }
        }
        return result;
    }
}
