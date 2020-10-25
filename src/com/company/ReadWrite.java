package com.company;

import java.io.*;
public class ReadWrite
{
    private int winner; //Winner of the round of Poker
    private int countOfWinsPlayer1; //Number of Rounds Won by Player 1
    private int countOfWinsPlayer2; //Number of Rounds Won by Player 2
    private String output; //Output to be written to the file
    private String[] allHands; //Array to store input of all the hands

    //A constructor to define the default values of all the attributes
    public ReadWrite()
    {
        winner=0;
        countOfWinsPlayer1=0;
        countOfWinsPlayer2=0;
        output="";
        allHands = new String[10];
    }

    //A function to using Buffered Reader to read input file
    public void readFile()
    {
        try
        {
            File file=new File("poker-hands.txt");    //creates a new file instance
            FileReader fr=new FileReader(file);   //reads the file
            BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
            StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters
            String line;

            //A loop that runs line by line till the end of file
            while((line=br.readLine())!=null)
            {
                Poker poker = new Poker(); //An Object of the class Poker
                allHands = line.split(" "); //Splits the line by space and adds all the elements to an Array
                winner = poker.game(allHands);
                if(winner==1)
                    countOfWinsPlayer1++;
                else if(winner==2)
                    countOfWinsPlayer2++;
            }
            fr.close();    //closes the stream and release the resources
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        output = "Player 1: "+countOfWinsPlayer1+" hands"+"\n"+"Player 2: "+countOfWinsPlayer2+" hands";
        writeToFile(output);
        System.out.println("Player 1: "+countOfWinsPlayer1+" hands"); //Displays Player 1 Wins
        System.out.println("Player 2: "+countOfWinsPlayer2+" hands"); //Displays Player 2 Wins
    }

    public void writeToFile(String output) {
        BufferedWriter bw = null;
        try {

            //The output file name and path is specified here
            File file = new File("poker-hands-output.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(output);
            System.out.println("File written Successfully");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally
        {
            try{
                if(bw!=null)
                    bw.close();
            }catch(Exception ex){
                System.out.println("Error in closing the BufferedWriter"+ex);
            }
        }
    }
}
