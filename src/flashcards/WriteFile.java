package flashcards;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteFile {
   
    BufferedWriter bufferedWriter;
   // PrintWriter printWriter;
    WriteFile(String fileName){
        
        FileWriter outFile;
        try {
            outFile = new FileWriter(fileName, true);
         //   printWriter = new PrintWriter(outFile);
            bufferedWriter = new BufferedWriter(outFile);
        }
        catch(Exception e) {
            System.err.println("Flash card cannot"+"use output file:" +fileName);
        }
        
    }
    WriteFile(String fileName,Boolean edit){
        FileWriter outFile;
        try {
            outFile = new FileWriter(fileName, edit);
         //   printWriter = new PrintWriter(outFile);
            bufferedWriter = new BufferedWriter(outFile);
        }
        catch(Exception e) {
            System.err.println("Flash card cannot"+"use output file:" +fileName);
        }
    }
    
    void writeLine(String line) {
        try {
            
            bufferedWriter.write(line);
            bufferedWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    void writeCardToFile(ArrayList<Flashcards> flashCards) {
    try {
        for (int i= 0; i< flashCards.size();i++) {
            String line = flashCards.get(i).id + ", "+ flashCards.get(i).question +", "+flashCards.get(i).answer +
                    ", "+flashCards.get(i).schedule+", "+flashCards.get(i).interval+
                    ", "+flashCards.get(i).level+"\n"; 
       
            bufferedWriter.write(line);
        }
        bufferedWriter.close();
    }catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
       
    }
    
    void writeAllcards(String[] allCards) {
        try {
            for(int a = 0; a < allCards.length; a++) {
                bufferedWriter.write(allCards[a]+"\n");
            }
            bufferedWriter.close();
                
        }catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
    
    
/*   
    public static void main(String args[]) { 
        WriteFile wf = new WriteFile("/Users/mac/Documents/workspace/Flashcards/src/flashcards/example.txt");
        Flashcards f1 = new Flashcards("Capital of NY", "NYC", 2, 10, "e");
        Flashcards f2 = new Flashcards("Capital of CA", "Sacramento", 2, 10, "d");
        ArrayList<Flashcards> fl = new ArrayList<Flashcards>();
        fl.add(f1);
        fl.add(f2);
        wf.writeCardToFile(fl);
        wf = new WriteFile("/Users/mac/Documents/workspace/Flashcards/src/flashcards/example.txt");
        wf.writeLine("abc");
    } */
    
}
