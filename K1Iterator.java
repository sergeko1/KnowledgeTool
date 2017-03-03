import java.util.ArrayList;

public class K1Iterator {

    private ArrayList<String> list;
    private String question;
    private String answer;   
    private int counter;

    K1Iterator(ArrayList<String> myList) {
        list = myList; 
        counter = 0;
        next();
       // String line[] = thisLine.split("---");
        ////question = line[0];
        //answer = line[1];
    }
 
    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean checkAnswer(String givenAnswer) {
       if (givenAnswer.equals(answer)) {
           return true;
       }
       return false;
    }

    public void next() {
       String[] splitted = list.get(counter).split("---");
       question = splitted[0];
       answer = splitted[1];
       counter++;
    }
}
