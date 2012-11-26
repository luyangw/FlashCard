package flashcards;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class FlashCardTest {

    @Test
    public void testReadLine() {
        ReadFile rf = new ReadFile("/Users/mac/Documents/workspace/Flashcards/src/flashcards/example.txt");
        
        String firstLine = "9x6, 54, 12, 5, m";
        assertEquals(firstLine, rf.readLine());
        String secondLine = "Capital of PA, Harrisburg, 2, 4, d";
        assertEquals(secondLine, rf.readLine());
    }
    
    @Test
    public void testReadToCards() {
        ReadFile rf = new ReadFile("/Users/mac/Documents/workspace/Flashcards/src/flashcards/example.txt");
       
        Flashcards f1 = new Flashcards("9x6", "54", 12, 5, "m");
        Flashcards f2 = new Flashcards("Capital of PA", "Harrisburg", 2, 4, "d");
        
        ArrayList<Flashcards> fl = new ArrayList<Flashcards>();
        fl.add(f1);
        fl.add(f2);
        
        assertEquals(fl.get(0).answer, rf.readToCard().get(0).answer);
        
        
    }
    
  
    
    @Test
    public void testWriteToCards() {
        WriteFile wf = new WriteFile("example.txt");
        Flashcards f1 = new Flashcards("Capital of NY", "NYC", 2, 10, "e");
        Flashcards f2 = new Flashcards("Capital of CA", "Sacramento", 2, 10, "d");
        ArrayList<Flashcards> fl = new ArrayList<Flashcards>();
        fl.add(f1);
        fl.add(f2);
        wf.writeCardToFile(fl);
      }
    
   

}


