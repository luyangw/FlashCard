package flashcards;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {
    BufferedReader bufferedReader;
    ReadFile(String fileName){
        FileReader fileReader = null;
        try {
               fileReader = new FileReader(fileName);
        }
        catch(FileNotFoundException e) {
            System.err.println("LineReader cannot find input file:"+fileName);
            e.printStackTrace();
        }
        bufferedReader = new BufferedReader(fileReader);
    }
    String readLine() {
       try {
      
           return bufferedReader.readLine();
       }
       catch(IOException e) {
           e.printStackTrace();
       }
       return null;
    }
    
    ArrayList<Flashcards> readToCard() {
        ArrayList<Flashcards> flashCards = new ArrayList<Flashcards>();
        String[] record = new String[5]; 
        String line = this.readLine();
        while(line != null) {
            record = line.split(", ");
            Flashcards flashCard = new Flashcards(record[0],record[1],Integer.parseInt(record[2]),Integer.parseInt(record[3]),record[4]);
            flashCards.add(flashCard);
            line = this.readLine();
        }
        System.out.println("size:"+flashCards.size());
        return flashCards;
    }
    
    int countLine() {
        int count = 0; 
        String value = this.readLine(); 
        if(value != null) 

        while(value !=null){ 
            count++; 
            value =this.readLine(); 
        } 
        return count;
    }
   
    void close() {
        try {
            bufferedReader.close();
        }
        catch(IOException e) {
            
        }
    }
}
