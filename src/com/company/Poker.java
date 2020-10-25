package com.company;
import java.util.Arrays;

public class Poker {
    private int winner=0;
    private String[] Player1 = new String[5];
    private String[] Player2 = new String[5];
    int[] number = new int[5];
    char[] suit = new char[5];
    private String[] Rank = {"High Card", "Pair", "Two Pairs", "Three Of A Kind", "Straight", "Flush", "Full House",
            "Four Of A Kind", "Straight Flush", "Royal Flush"};
    PlayerCardRank playerCardRank = new PlayerCardRank();
    public int game(String[] allHands)
    {

        int winnerOfTie= 0;
        int i=0,j=0;
        for(i=0,j=0;i<allHands.length;i++)
        {
            if(i<5)
                Player1[i] = allHands[i];
            else if(i>=5 && i< allHands.length) {
                Player2[j] = allHands[i];
                j++;
            }
        }

        String RankOfPlayer1="";
        String RankOfPlayer2="";
        int indexOfRankOfPlayer1=0;
        int indexOfRankOfPlayer2=0;

        RankOfPlayer1=checkRank(Player1);
        RankOfPlayer2=checkRank(Player2);
        indexOfRankOfPlayer1 = indexOfRank(RankOfPlayer1);
        indexOfRankOfPlayer2 = indexOfRank(RankOfPlayer2);

        if(indexOfRankOfPlayer1>indexOfRankOfPlayer2)
        {
            winner = 1;
        }
        if(indexOfRankOfPlayer1<indexOfRankOfPlayer2)
        {
            winner = 2;
        }
        if(indexOfRankOfPlayer1==indexOfRankOfPlayer2)
        {
            winnerOfTie= checkTie(Player1,Player2,indexOfRankOfPlayer1);
            if(winnerOfTie==1)
                winner = 1;
            else if(winnerOfTie==2)
                winner = 2;
        }
        return winner;
    }

    /*Returns 1 if Player 1 Wins
      Returns 2 if Player 2 Wins
      Returns 3 if it is a Draw
    */
    public int checkTie(String[] Player1, String[] Player2, int indexRank)
    {
        int result=0;
        if(indexRank==1)
        {
            result = highCardComparison(Player1,Player2);
        }
        else if(indexRank==2)
        {
            int[] tempArrPlayer1 = new int[5];
            int[] tempArrPlayer2 = new int[5];
            int cardOfPairPlayer1=0;
            int cardOfPairPlayer2=0;
            splitArray(Player1);
            Arrays.sort(number);
            for(int i=0;i<4;)
            {
                if(number[i]==number[i+1])
                {
                    cardOfPairPlayer1=number[i];
                    break;
                }
                else
                    i++;
            }

            for(int i=0;i<5;i++)
            {
                if(calcFrequency(number)[i]==2 || calcFrequency(number)[i] == -1)
                    tempArrPlayer1[i]=0;
                else
                    tempArrPlayer1[i]=number[i];
            }

            Arrays.sort(tempArrPlayer1);
            splitArray(Player2);
            Arrays.sort(number);
            for(int i=0;i<4;)
            {
                if(number[i]==number[i+1])
                {
                    cardOfPairPlayer2=number[i];
                    break;
                }
                else
                    i++;
            }
            for(int i=0;i<5;i++)
            {
                if(calcFrequency(number)[i]==2 || calcFrequency(number)[i] == -1)
                    tempArrPlayer2[i]=0;
                else
                    tempArrPlayer2[i]=number[i];
            }
            Arrays.sort(tempArrPlayer2);
            if(cardOfPairPlayer1>cardOfPairPlayer2)
                result = 1;
            else if((cardOfPairPlayer1<cardOfPairPlayer2))
                result = 2;
            else if(cardOfPairPlayer1==cardOfPairPlayer2)
            {
                for(int i=4;i>1;)
                {
                    if(tempArrPlayer1[i]>tempArrPlayer2[i]) {
                        result = 1;
                        break;
                    }
                    else if (tempArrPlayer1[i]<tempArrPlayer2[i]){
                        result = 2;
                        break;
                    }
                    else if(tempArrPlayer1[i]==tempArrPlayer2[i])
                    {
                        result = 3;
                        i--;
                    }
                }
            }
        }
        else if(indexRank==3)
        {
            int highCardPairPlayer1=0;
            int highCardPairPlayer2=0;
            int secondCardPairPlayer1=0;
            int secondCardPairPlayer2=0;
            int lastCardPlayer1=0;
            int lastCardPlayer2=0;

            splitArray(Player1);
            Arrays.sort(number);
            for(int i=4;i>1;i--)
            {
                if(calcFrequency(number)[i]==-1)
                {
                    highCardPairPlayer1=number[i];
                    break;
                }
            }

            for(int i=0;i<5;i++)
            {
                if(calcFrequency(number)[i]==2)
                {
                    secondCardPairPlayer1=number[i];
                    break;
                }
            }

            for(int i=0;i<5;i++)
            {
                if(calcFrequency(number)[i]==1)
                {
                    lastCardPlayer1=number[i];
                    break;
                }
            }

            splitArray(Player2);
            Arrays.sort(number);
            for(int i=4;i>1;i--)
            {
                if(calcFrequency(number)[i]==-1)
                {
                    highCardPairPlayer2=number[i];
                    break;
                }
            }

            for(int i=0;i<5;i++)
            {
                if(calcFrequency(number)[i]==2)
                {
                    secondCardPairPlayer2=number[i];
                    break;
                }
            }


            for(int i=0;i<5;i++)
            {
                if(calcFrequency(number)[i]==1)
                {
                    lastCardPlayer2=number[i];
                    break;
                }
            }

            if(highCardPairPlayer1>highCardPairPlayer2)
                result = 1;
            else if(highCardPairPlayer1<highCardPairPlayer2)
                result = 2;
            else if(highCardPairPlayer1==highCardPairPlayer2)
            {
                if(secondCardPairPlayer1>secondCardPairPlayer2)
                    result = 1;
                else if(secondCardPairPlayer1<secondCardPairPlayer2)
                    result = 2;
                else if(secondCardPairPlayer1==secondCardPairPlayer2)
                {
                    if(lastCardPlayer1>lastCardPlayer2)
                        result = 1;
                    else if(lastCardPlayer1<lastCardPlayer2)
                        result = 2;
                    else if(lastCardPlayer1==lastCardPlayer2)
                        result = 3;
                }
            }
        }
        else if(indexRank==4)
        {
            int threeOfAKindCardPlayer1=0;
            int threeOfAKindCardPlayer2=0;
            splitArray(Player1);
            Arrays.sort(number);
            threeOfAKindCardPlayer1= number[2];
            splitArray(Player2);
            Arrays.sort(number);
            threeOfAKindCardPlayer2= number[2];

            if(threeOfAKindCardPlayer1>threeOfAKindCardPlayer2)
                result = 1;
            else
                result = 2;
        }
        else if(indexRank==5)
        {
            result = highCardComparison(Player1,Player2);
        }
        else if(indexRank==6)
        {
            result = highCardComparison(Player1,Player2);        }
        else if(indexRank==7)
        {
            int threeOfAKindCardPlayer1=0;
            int threeOfAKindCardPlayer2=0;
            splitArray(Player1);
            Arrays.sort(number);
            threeOfAKindCardPlayer1= number[2];
            splitArray(Player2);
            Arrays.sort(number);
            threeOfAKindCardPlayer2= number[2];

            if(threeOfAKindCardPlayer1>threeOfAKindCardPlayer2)
                result = 1;
            else
                result = 2;
        }
        else if(indexRank==8)
        {
            int fourOfAKindCardPlayer1=0;
            int fourOfAKindCardPlayer2=0;
            splitArray(Player1);
            Arrays.sort(number);
            fourOfAKindCardPlayer1= number[2];
            splitArray(Player2);
            Arrays.sort(number);
            fourOfAKindCardPlayer2= number[2];

            if(fourOfAKindCardPlayer1>fourOfAKindCardPlayer2)
                result = 1;
            else
                result = 2;
        }
        else if(indexRank==9)
        {
            int highCardPLayer1=0;
            int highCardPLayer2=0;
            splitArray(Player1);
            Arrays.sort(number);
            highCardPLayer1= number[4];
            splitArray(Player2);
            Arrays.sort(number);
            highCardPLayer2= number[4];
            if(highCardPLayer1>highCardPLayer2)
                result = 1;
            else if(highCardPLayer1<highCardPLayer2)
                result = 2;
            else
                result = 3;
        }
        else if(indexRank==10)
        {
            result=3;
        }
        return result;
    }

    public int indexOfRank(String rank)
    {
        int index=0;
        for(int i=0;i<10;i++)
        {
            if(Rank[i].equals(rank))
            {
                index=i+1;
                break;
            }
        }
        return index;
    }

    public void splitArray(String[] handPlayer)
    {
        for (int i=0;i<5;i++) {
            String num = handPlayer[i].substring(0,1);
            if(num.equals("T"))
                number[i]=10;
            else if(num.equals("J"))
                number[i]=11;
            else if(num.equals("Q"))
                number[i]=12;
            else if(num.equals("K"))
                number[i]=13;
            else if(num.equals("A"))
                number[i]=14;
            else
                number[i] = Integer.parseInt(num);
            suit[i] = handPlayer[i].charAt(1);
        }
    }

    public int[] calcFrequency(int[] number)
    {
        int visited = -1;
        int frequency[] = new int[5];
        for(int i=0;i<5;i++)
        {
            int count = 1;
            for(int j = i+1; j < 5; j++) {
                if (number[i] == number[j]) {
                    count++;
                    //To avoid counting same element again
                    frequency[j] = visited;
                }
            }
            if(frequency[i] != visited)
                frequency[i] = count;
        }
        return frequency;
    }




    public String checkRank(String[] PlayerCards)
    {
        int check=0;
        String rank="";
        splitArray(PlayerCards);
        do {
            if(playerCardRank.isRoyalFlush(number,suit)==1)
            {
                rank="Royal Flush";
                check=1;
            }
            else if(playerCardRank.isStraightFlush(number,suit)==1)
            {
                rank="Straight Flush";
                check=1;
            }
            else if(playerCardRank.isFourOfAKind(calcFrequency(number))==1)
            {
                rank="Four Of A Kind";
                check=1;
            }
            else if(playerCardRank.isFullHouse(number)==1)
            {
                rank="Full House";
                check=1;
            }
            else if(playerCardRank.isFlush(suit)==1)
            {
                rank="Flush";
                check=1;
            }
            else if(playerCardRank.isStraight(number)==1)
            {
                rank="Straight";
                check=1;
            }
            else if(playerCardRank.isThreeOfAKind(calcFrequency(number))==1)
            {
                rank="Three Of A Kind";
                check=1;
            }
            else if(playerCardRank.isTwoPair(calcFrequency(number))==1)
            {
                rank="Two Pairs";
                check=1;
            }
            else if(playerCardRank.isPair(calcFrequency(number))==1)
            {
                rank="Pair";
                check=1;
            }
            else if(playerCardRank.isHighCard(calcFrequency(number))==1)
            {
                rank="High Card";
                check=1;
            }
        }while(check!=1);

        return rank;
    }

    public int highCardComparison(String[] Player1, String[] Player2)
    {
        int result=0;
        int firstHighCardPlayer1=0;
        int secondHighCardPlayer1=0;
        int thirdHighCardPlayer1=0;
        int fourthHighCardPlayer1=0;
        int fifthHighCardPlayer1=0;
        int firstHighCardPlayer2=0;
        int secondHighCardPlayer2=0;
        int thirdHighCardPlayer2=0;
        int fourthHighCardPlayer2=0;
        int fifthHighCardPlayer2=0;
        splitArray(Player1);
        Arrays.sort(number);
        firstHighCardPlayer1=number[4];
        secondHighCardPlayer1=number[3];
        thirdHighCardPlayer1=number[2];
        fourthHighCardPlayer1=number[1];
        fifthHighCardPlayer1=number[0];

        splitArray(Player2);
        Arrays.sort(number);
        firstHighCardPlayer2=number[4];
        secondHighCardPlayer2=number[3];
        thirdHighCardPlayer2=number[2];
        fourthHighCardPlayer2=number[1];
        fifthHighCardPlayer2=number[0];

        if(firstHighCardPlayer1>firstHighCardPlayer2)
            result=1;
        else if(firstHighCardPlayer1<firstHighCardPlayer2)
            result = 2;
        else if(firstHighCardPlayer1==firstHighCardPlayer2)
        {
            if(secondHighCardPlayer1>secondHighCardPlayer2)
                result = 1;
            else if(secondHighCardPlayer1<secondHighCardPlayer2)
                result = 2;
            else if(secondHighCardPlayer1==secondHighCardPlayer2)
            {
                if(thirdHighCardPlayer1>thirdHighCardPlayer2)
                    result = 1;
                else if(thirdHighCardPlayer1<thirdHighCardPlayer2)
                    result = 2;
                else if(thirdHighCardPlayer1==thirdHighCardPlayer2)
                {
                    if(fourthHighCardPlayer1>fourthHighCardPlayer2)
                        result = 1;
                    else if(fourthHighCardPlayer1<fourthHighCardPlayer2)
                        result = 2;
                    else if(fourthHighCardPlayer1==fourthHighCardPlayer2)
                    {
                        if(fifthHighCardPlayer1<fifthHighCardPlayer2)
                            result = 1;
                        else if(fifthHighCardPlayer1<fifthHighCardPlayer2)
                            result = 2;
                        else if(fifthHighCardPlayer1==fifthHighCardPlayer2)
                            result = 3;
                    }
                }
            }
        }
        return result;
    }
}

