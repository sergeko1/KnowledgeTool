public class K1Question {
   private String question;
   private String answer;   
   K1Question(String thisLine) {
       String line[] = thisLine.split("---");
       question = line[0];
       answer = line[1];

   }

   public String getQuestion() {
       return question;
   }
}

