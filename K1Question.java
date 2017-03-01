public class K1Question {
   String question;
   String answer;   
   K1Question(String thisLine) {
       String line[] = thisLine.split("---");
       question = line[0];
       answer = line[1];

   }
}

