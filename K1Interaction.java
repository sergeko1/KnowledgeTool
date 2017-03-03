
public class K1Interaction {
    private String question;
    private String answer;   
    K1Interaction(String thisLine) {
        String line[] = thisLine.split("---");
        question = line[0];
        answer = line[1];
    }
 
    public String getQuestion() {
        return question;
    }

    public boolean checkAnswer(String givenAnswer) {
       if (givenAnswer.equals(answer)) {
           return true;
       }
       return false;
    }
}
