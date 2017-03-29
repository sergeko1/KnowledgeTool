import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;

public class K1Iterator {

    private ArrayList<String> list;
    private String question;
    private String answer;   
    private String title;   
    private int counter;
    boolean random = false;

    // Constructor
    K1Iterator(String fileName) {
        readFile(fileName); 
        title = fileName;
        if (random) 
           randomize();
        counter = 0;
        next();
    }

    K1Iterator(String fileName, boolean myRandom) {
       this(fileName);
       random = myRandom;
    }

    // To be used to get the Title of the JFrame
    public String getTitle() {
        return title;
    }
    public void randomize() {
        Collections.shuffle(list);
    }
    // To be used to get the current question.
    public String getQuestion() {
        return question;
    }
  
    
    // To be used to get the current answer.
    public String getAnswer() {
        return answer;
    }

    // To be used to obtain a boolean with the outcome of the supplied answer. 
    public boolean checkAnswer(String givenAnswer) {
       if (givenAnswer.equals(answer)) 
           return true;
       else
           return false;
    }

    // Checks for last question
    public boolean hasNext() {
       if (counter<(list.size()))
          return true;
       else 
          return false; 
    }

    protected int size() {
       return list.size();
    }



   // Opens the file for reading
   void readFile(String file) {
      Scanner input;
      try {
         input = new Scanner(new File(file));
         list = new ArrayList<String>();
         while (input.hasNextLine()) {
            list.add(input.nextLine());
         }
      }
      catch (FileNotFoundException fileNotFoundException) {
          System.err.println("Error opening File");
          System.exit(1);
      }
   }

   
    // The Iterator moves to the next answer.
    public void next() {
       String[] splitted = list.get(counter).split("@");
       question = splitted[0];
       answer = splitted[1];
       counter++;
    } // end of next()
} // end of Class

