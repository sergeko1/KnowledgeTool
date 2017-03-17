import java.util.ArrayList;

public class K1Iterator 
{
    private ArrayList<String> list;
    private String question;
    private String answer;   
    private int counter;

    // Constructor
    K1Iterator(ArrayList<String> myList) 
    {
        list = myList; 
        counter = 0;
        next();
    }
 
    // To be used to get the current question.
    public String getQuestion()
    {
        return question;
    }
  
    // To be used to get the current answer.
    public String getAnswer() 
    {
        return answer;
    }

    // To be used to obtain a boolean with the outcome of the supplied answer. 
    public boolean checkAnswer(String givenAnswer) 
    {
       if (givenAnswer.equals(answer)) 
           return true;
       else
           return false;
    }

    // Checks for last question
    public boolean hasNext() 
    {
       if (counter<(list.size()))
          return true;
       else 
          return false; 
    }

    // The Iterator moves to the next answer.
    public void next() 
    {
       String[] splitted = list.get(counter).split("@");
       question = splitted[0];
       answer = splitted[1];
       counter++;
    }
}
