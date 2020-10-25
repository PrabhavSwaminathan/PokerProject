package com.company;

import java.io.*;
public class ReadWrite
{
    int winner=0;
    int countOfWinsPlayer1=0;
    int countOfWinsPlayer2=0;
    String output="";
    public void readFile()
    {
        String[] allHands;
        try
        {
            File file=new File("poker-hands.txt");    //creates a new file instance
            FileReader fr=new FileReader(file);   //reads the file
            BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
            StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters
            String line;

            while((line=br.readLine())!=null)
            {
                Poker poker = new Poker();
                allHands = line.split(" ");
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
        System.out.println("Player 1: "+countOfWinsPlayer1+" hands");
        System.out.println("Player 2: "+countOfWinsPlayer2+" hands");
    }

    public void writeToFile(String output) {
        BufferedWriter bw = null;
        try {

            //Specify the file name and path here
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
