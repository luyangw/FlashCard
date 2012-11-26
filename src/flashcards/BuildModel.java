package flashcards;

import java.util.ArrayList;

public class BuildModel {
    ArrayList<Flashcards> flashCards;
    ArrayList<Integer> index = new ArrayList<Integer>(); //this index stores the indexes of the results of cards after searching.
    public BuildModel() {
        
    }
    public void createCard(String fileName, String question, String answer) {
        ReadFile readFile = new ReadFile(fileName);
        int lineNum = readFile.countLine();
              
        flashCards = new ArrayList<Flashcards>();
        Flashcards flashCard = new Flashcards(lineNum + 1, question, answer, lineNum + 1, 0, "d");
        flashCards.add(flashCard);
        
        WriteFile wf = new WriteFile(fileName);
        wf.writeCardToFile(flashCards);
    }
    
    public boolean checkDuplicate(String fileName, String question) {
        ArrayList<Flashcards> existCards;
        ReadFile readFile = new ReadFile(fileName);
        existCards = readFile.readToCard();
        for (int i = 0; i < existCards.size(); i++) {
            if (existCards.get(i).question.equals(question)) {
                return true;
            }
        }
        return false;
    }
    
    public String[] obtainCards(String fileName) { 
        System.out.println("enter");
        ArrayList<Flashcards> cards = new ArrayList<Flashcards>();  
        ReadFile readFile = new ReadFile(fileName);
        cards = readFile.readToCard();
        String[] cardDetail = new String[cards.size()];
       
        for(int i = 0; i < cards.size(); i++) {
            cardDetail[i] = "Question: "+ cards.get(i).question + ", Answer: "+ cards.get(i).answer + 
                    ", schedule: "+cards.get(i).schedule + ", interval: " + cards.get(i).interval;
            System.out.println("card details:"+cardDetail[i]);
        }
        
        return cardDetail;
    }
    
    public String[] obtainSearchResults(ArrayList<Flashcards> searchResults) {
        String[] results = new String[searchResults.size()];
        for(int i = 0; i < searchResults.size(); i++) {
            results[i] = "Question: "+ searchResults.get(i).question + ", Answer: "+ searchResults.get(i).answer + 
                    ", schedule: "+searchResults.get(i).schedule + ", interval: " + searchResults.get(i).interval;
       
        }
        return results;
    }
    
    public void editUpdate(String fileName,String[] allcards) {
       
        for(int i=0;i<allcards.length;i++)
            System.out.println(allcards[i]);
        
        WriteFile wf = new WriteFile(fileName, false);
        wf.writeAllcards(allcards);
        
        
    }
    
    public String[] obtainAllcards(String fileName) {
        ArrayList<Flashcards> cards = new ArrayList<Flashcards>();  
        ReadFile readFile = new ReadFile(fileName);
        cards = readFile.readToCard();
        String[] allCards = new String[cards.size()];
        for(int i = 0; i < cards.size(); i++) {
            allCards[i] = cards.get(i).id + ", "+ cards.get(i).question+", "+cards.get(i).answer+", "+cards.get(i).schedule+", "+cards.get(i).interval+", "+cards.get(i).level;
        }
        return allCards;
    }
    
    public ArrayList<Flashcards> displayCards(String searchText, String fileName) {
        
        ArrayList<Flashcards> cards = new ArrayList<Flashcards>();
        ArrayList<Flashcards> results = new ArrayList<Flashcards>();
        ReadFile readFile = new ReadFile(fileName);
        cards = readFile.readToCard();
        
        for(int i = 0; i < cards.size(); i++) {
            if(cards.get(i).question.indexOf(searchText) != -1 || cards.get(i).answer.indexOf(searchText) != -1 ||
                    //cards.get(i).schedule == Integer.parseInt(searchText) || 
                    //cards.get(i).interval == Integer.parseInt(searchText) ||
                    cards.get(i).level.indexOf(searchText) != -1 ) {
                index.add(i);
                results.add(cards.get(i));
            }
          
        }
        return results;
    }
    
}
