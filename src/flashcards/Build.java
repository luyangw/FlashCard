package flashcards;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.*;

//import javax.swing.JPanel;

public class Build extends JFrame {
    private JPanel panel1; // this is panel used to hold create new card button
    private JPanel panel2; // this panel is used to hold edit card button
    private JPanel panel3; // this panel is used to hold question label and text
                           // area
    private JPanel panel4; // this panel is used to hold answer label and text
                           // area
    private JPanel panel5; // this panel is used to hold add flash card button
                           // and return button
    private JPanel panel6; // this panel is used to hold search textfield and
                           // search button.
    private JPanel panel7; // this panel is used to hold the question list that
                           // user can selected to edit.
    private JPanel panel8; // this panel is used to hold question lebal and
                           // question textarea to be edited
    private JPanel panel9; // this panel is used to hold answer label and answer
                           // textarea to be edited
    private JPanel panel10; // this panel is used to hold interval label and
                            // interval textfield
    private JPanel panel11; // this panel is used to hold difficulty label and
                            // difficulty textfield
    private JPanel panel12; // this panel is used to hold the search results
    private JButton edit; // this button is used to confirm the edition

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton openFile;
    private JButton createFile;
    private JButton searchButton;
    private ArrayList<JRadioButton> questionList;
    private ArrayList<JRadioButton> searchResultList;
    private ArrayList<Flashcards> searchResults;
    private ArrayList<Integer> index; // this array is used to store the line
                                      // number of each resulting card after
                                      // searching
    private JButton searchQuestion;
    private JButton searchCard; // this button is used to search card in the
                                // result of search page.

    private JTextArea question;
    private JTextArea answer;
    private JTextArea questionArea;
    private JTextArea answerArea;
    private JTextArea scheduleArea;
    private JTextArea intervalArea;

    private JTextField search;
    private String newQuestion;
    private String newAnswer;
    private String editQuestion;
    private String editAnswer;
    private String searchText; // this is the text the user wants to search for
                               // edition.
    private int editInterval;
    private int editSchedule;

    private int editIndex;
    private String fileName;
    BuildModel buildModel = new BuildModel();

    public Build() {

        home();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(300, 200);
        this.setVisible(true);
    }

    /**
     * This is the home page. It has two buttons. One is Create new flash card,
     * the other is 'Edit Flash cards'.
     */
    public void home() {
        panel1 = new JPanel();
        panel2 = new JPanel();
        button1 = new JButton("Create new flashcard");
        button2 = new JButton("Edit Flashcards");
        panel1.add(button1);
        panel2.add(button2);
        this.setLayout(new BorderLayout());
        this.add(panel1, BorderLayout.NORTH);
        this.add(panel2, BorderLayout.SOUTH);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                openOrCreateFile();
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("aaa");
                openFile();
                editCard();
            }
        });
        this.repaint();
        this.validate();

    }

    /**
     * Load an existed file
     */
    public void openFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Load which file to add your card?");
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                if (file != null) {
                    fileName = file.getCanonicalPath();
                    // createCard();
                }
            } catch (IOException e) {
            }
        }
    }

    /**
     * create a new file.
     */
    public void createFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save file as?");
        int result = chooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                if (file != null) {
                    fileName = file.getCanonicalPath();
                    PrintWriter myPrintWriter = new PrintWriter(
                            new FileOutputStream(fileName), true);
                    createCard();
                }
            } catch (IOException e) {
            }
        }

    }

    public void openOrCreateFile() {
        this.remove(panel1);
        this.remove(panel2);

        openFile = new JButton("Open an existing file");
        createFile = new JButton("Create a new file");

        this.setLayout(new BorderLayout());
        this.add(openFile, BorderLayout.NORTH);
        this.add(createFile, BorderLayout.SOUTH);

        openFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                openFile();
                createCard();
            }
        });

        createFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                createFile();
            }
        });
        this.repaint();
        this.validate();
    }

    public void createCard() {

        this.remove(openFile);
        this.remove(createFile);

        panel3 = new JPanel(); // question text area
        panel4 = new JPanel(); // answer text area
        panel5 = new JPanel();

        button3 = new JButton("Add FlashCard"); // add new flashcard button
        button4 = new JButton("Return");

        question = new JTextArea();
        answer = new JTextArea();
        JLabel questionL = new JLabel("Question:");
        JLabel answerL = new JLabel("Answer:");
        panel3.setLayout(new BorderLayout());
        panel3.add(questionL, BorderLayout.NORTH);
        panel3.add(question, BorderLayout.SOUTH);

        panel4.setLayout(new BorderLayout());
        panel4.add(answerL, BorderLayout.NORTH);
        panel4.add(answer, BorderLayout.SOUTH);

        panel5.setLayout(new BorderLayout());
        panel5.add(button3, BorderLayout.WEST);
        panel5.add(button4, BorderLayout.EAST);

        this.setLayout(new BorderLayout());
        this.add(panel3, BorderLayout.WEST);
        this.add(panel4, BorderLayout.EAST);
        this.add(panel5, BorderLayout.SOUTH);

        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addNewCardListener(evt);
            }
        });

        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                returnListener(event);
            }
        });

        this.repaint();
        this.validate();
    }

    /**
     * This method is used to choose which card to edit.
     */
    public void editCard() {
        this.remove(panel1);
        this.remove(panel2);

        questionList = new ArrayList<JRadioButton>();
        ButtonGroup myButtonGroup = new ButtonGroup();
        String[] cardDetail;
        search = new JTextField(15);
        searchButton = new JButton("Search");
        panel6 = new JPanel();
        panel6.setLayout(new BorderLayout());
        panel6.add(search, BorderLayout.WEST);
        panel6.add(searchButton, BorderLayout.EAST);

        panel7 = new JPanel();
        cardDetail = buildModel.obtainCards(fileName);
        panel7.setLayout(new GridLayout(cardDetail.length, 1));

        for (int i = 0; i < cardDetail.length; i++) {
            questionList.add(new JRadioButton(cardDetail[i]));
            myButtonGroup.add(questionList.get(i));
            panel7.add(questionList.get(i));
        }
        searchQuestion = new JButton("Search");
        this.setLayout(new BorderLayout());
        this.add(panel6, BorderLayout.NORTH);
        this.add(panel7, BorderLayout.CENTER);
        this.add(searchQuestion, BorderLayout.SOUTH);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                searchText = search.getText();
                searchResults = buildModel.displayCards(searchText, fileName);
                index = buildModel.index;
                displayResults(searchResults);
            }
        });

        searchQuestion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int index = -1;
                ReadFile readFile = new ReadFile(fileName);
                ArrayList<Flashcards> cards = readFile.readToCard();
                for (int i = 0; i < questionList.size(); i++) {
                    if (questionList.get(i).isSelected()) {
                        editQuestion = cards.get(i).question;
                        editAnswer = cards.get(i).answer;
                        editInterval = cards.get(i).interval;
                        editSchedule = cards.get(i).schedule;
                        index = i;
                        break;
                    }
                }
                editDetails(editQuestion, editAnswer, editInterval,
                        editSchedule, index);
            }
        });

        this.repaint();
        this.validate();

    }

    /**
     * @param question
     * @param answer
     * @param interval
     * @param schedule
     * @param index
     *            This method is used to edit the specific details of a card.
     */
    public void editDetails(String question, String answer, int interval,
            int schedule, int index) {

        if (index == -1)
            JOptionPane.showMessageDialog(this,
                    "Please choose one card to edit.");
        else {

            editIndex = index;
            this.remove(panel7);
            this.remove(panel6);
            this.remove(searchQuestion);

            JLabel editQuestion = new JLabel("Question");
            questionArea = new JTextArea(question);
            panel8 = new JPanel();
            panel8.setLayout(new GridLayout(2, 1));
            panel8.add(editQuestion);
            panel8.add(questionArea);

            JLabel editAnswer = new JLabel("Answer");
            answerArea = new JTextArea(answer);
            panel9 = new JPanel();
            panel9.setLayout(new GridLayout(2, 1));
            panel9.add(editAnswer);
            panel9.add(answerArea);

            JLabel editInterval = new JLabel("Interval");
            intervalArea = new JTextArea("" + interval);
            panel10 = new JPanel();
            panel10.setLayout(new GridLayout(2, 1));
            panel10.add(editInterval);
            panel10.add(intervalArea);

            JLabel editSchedule = new JLabel("Schedule");
            scheduleArea = new JTextArea("" + schedule);
            panel11 = new JPanel();
            panel11.setLayout(new GridLayout(2, 1));
            panel11.add(editSchedule);
            panel11.add(scheduleArea);

            button5 = new JButton("Edit");
            button5.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    editListener(event);
                }
            });

            panel11.add(button5);
            this.setLayout(new GridLayout(3, 1));
            this.add(panel8);
            this.add(panel9);
            this.add(panel10);
            this.add(panel11);

            this.repaint();
            this.validate();

        }

    }

    public void displayResults(ArrayList<Flashcards> searchResults) {
        this.remove(panel7);
        this.remove(panel6);
        this.remove(searchQuestion);
        String results[] = buildModel.obtainSearchResults(searchResults);
        searchResultList = new ArrayList<JRadioButton>();
        ButtonGroup myButtonGroup = new ButtonGroup();
        panel12 = new JPanel();
        panel12.setLayout(new GridLayout(results.length, 1));

        for (int i = 0; i < searchResults.size(); i++) {
            searchResultList.add(new JRadioButton(results[i]));
            myButtonGroup.add(searchResultList.get(i));
            panel12.add(searchResultList.get(i));
        }

        searchCard = new JButton("Edit");
        this.setLayout(new FlowLayout());
        // this.add(panel6, BorderLayout.NORTH);
        this.add(panel12);
        this.add(searchCard);

        this.searchResults = searchResults;
        searchCard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                editResultListener();

            }
        });

        this.repaint();
        this.validate();
    }

    public void editResultListener() {
        int id = -1;

        for (int i = 0; i < searchResultList.size(); i++) {
            if (searchResultList.get(i).isSelected()) {
                editQuestion = searchResults.get(i).question;
                editAnswer = searchResults.get(i).answer;
                editInterval = searchResults.get(i).interval;
                editSchedule = searchResults.get(i).schedule;
                id = index.get(i);
                System.out.println("id:" + id);
                break;
            }
        }
        this.remove(panel12);
        this.remove(searchCard);
        editDetails(editQuestion, editAnswer, editInterval, editSchedule, id);
    }

    public void addNewCardListener(ActionEvent event) {

        newQuestion = question.getText();
        newAnswer = answer.getText();
        System.out.println("question:" + newQuestion);
        if (newQuestion.equals("") || newAnswer.equals("")) {
            System.out.println("blank!");
            JOptionPane.showMessageDialog(this,
                    "You need to input a question and a corresponding answer.");
        } 
        else {

            if (!buildModel.checkDuplicate(fileName, newQuestion)) {
                buildModel.createCard(fileName, newQuestion, newAnswer);
                this.remove(panel3);
                this.remove(panel4);
                this.remove(panel5);
                createCard();
            } else {
                JOptionPane
                        .showMessageDialog(this,
                                "This question has been created.\n Please create another one.");
            }
        }

    }

    public void returnListener(ActionEvent event) {
        this.remove(panel1);
        this.remove(panel2);
        this.remove(panel3);
        this.remove(panel4);
        this.remove(panel5);
        home();
        this.repaint();
        this.validate();
    }

    /**
     * @param event
     */
    public void editListener(ActionEvent event) {
        // JOptionPane.showMessageDialog(this, "call edit listener");
        String[] cards = buildModel.obtainAllcards(fileName);
        String eQuestion = questionArea.getText();
        String eAnswer = answerArea.getText();
        String eInterval = intervalArea.getText();
        String eSchedule = scheduleArea.getText();

        cards[editIndex] = eQuestion + ", " + eAnswer + ", " + eSchedule + ", "
                + eInterval + ", " + "d";

        buildModel.editUpdate(fileName, cards);
        // JOptionPane.showMessageDialog(this,questions[1]);

        this.remove(panel8);
        this.remove(panel9);
        this.remove(panel10);
        this.remove(panel11);
        editCard();

    }

    public static void main(String args[]) {
        Build build = new Build();

    }
}
