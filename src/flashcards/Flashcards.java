package flashcards;

public class Flashcards {
    String question;
    String answer;
    String level;
    int schedule;
    int interval;
    int id;
    public Flashcards(int id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.level = "d";
        this.schedule = 0;
        this.interval = 0;
      
    }
    
    public Flashcards(int id, String question, String answer, int schedule, int interval, String level) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.level = level;
        this.schedule = schedule;
        this.interval = interval;
    }
}
