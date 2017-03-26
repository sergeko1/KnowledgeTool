public class K1StatsWriter {

   int wrongAnswers = 0;
   int rightAnswers = 0;
   int totQuestions = 0;

   K1StatsWriter(int numQuestions) {
       totQuestions = numQuestions;
   } 

   void add(boolean result) {
      if (result)
          rightAnswers++;
      else
          wrongAnswers++;
   }

   
   void printTotal() {
       System.out.println("Right Answers " + rightAnswers);
       System.out.println("Wrong Answers " + wrongAnswers);
       System.out.println("Answers Left " + (totQuestions-rightAnswers-wrongAnswers));
       System.out.println("Total Questions " + totQuestions);

   }

   String printTotalString() {
       return "Right Answers " + rightAnswers + " \nWrong Answers " + wrongAnswers + " \nAnswers Left " + (totQuestions-rightAnswers-wrongAnswers + " \nTotal Questions " + totQuestions + " ");
   }


}
