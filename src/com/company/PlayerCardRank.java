package com.company;

import java.util.Arrays;

public class PlayerCardRank {
    public int isRoyalFlush(int[] number,char[] suit)
    {
        int result = 0;
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

    public int isFlush(char[] suit)
    {
        int result = 0;
        result = sameSuit(suit);
        return result;
    }

    public int isStraightFlush(int[] number,char[] suit)
    {
        int result = 0;
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

    public int isFourOfAKind(int[] frequency)
    {
        int result=0;
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

    public int isThreeOfAKind(int[] frequency)
    {
        int result=0;
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

    public int isStraight(int[] number)
    {
        int result = 0;
        result = consecutiveOrder(number);
        return result;
    }

    public int isPair(int[] frequency)
    {
        int result=0;
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

    public int isTwoPair(int[] frequency)
    {
        int result=0;
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

    public int isHighCard(int[] frequency)
    {
        int result=0;
        int count=0;
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

    public int consecutiveOrder(int[] number)
    {
        int result = 0;
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


    public int highestConsecutiveOrder(int[] number)
    {
        int result = 0;
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

    public int sameSuit(char suit[])
    {
        int result=0;
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
