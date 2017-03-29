import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class K1StatsWriter {

   int wrongAnswers = 0;
   int rightAnswers = 0;
   int totQuestions = 0;
   long startTime;
   long endTime;
   String fileName;

   K1StatsWriter(int numQuestions, String myFileName) {
       totQuestions = numQuestions;
       fileName = myFileName;
   } 

   void add(boolean result) {
      if (result)
          rightAnswers++;
      else
          wrongAnswers++;
   }

   //void setElapsedTime(double myElapsedTime) {
   //   elapsedTime=myElapsedTime;
   //}

   void setStartTime() {
      startTime=System.nanoTime();
   }

   void setEndTime() {
      endTime=System.nanoTime();
   }

   void printTotal() {
       System.out.println("Right Answers " + rightAnswers);
       System.out.println("Wrong Answers " + wrongAnswers);
       System.out.println("Answers Left " + (totQuestions-rightAnswers-wrongAnswers));
       System.out.println("Total Questions " + totQuestions);
       System.out.println("Elapsed Time " + (double)((endTime-startTime)/1000000000));

   }

   String printTotalString() {
       return "Right Answers " + rightAnswers + " \nWrong Answers " + wrongAnswers + 
           " \nAnswers Left " + (totQuestions-rightAnswers-wrongAnswers + " \nTotal Questions " + totQuestions + "\nElapsed Time "+((double)(endTime-startTime)/1000000000)+" ");
   }

   void writeToFile() {
      BufferedWriter out = null;
      try {
          String[] splitted = fileName.split(".*/");
          FileWriter fstream = new FileWriter("stats/"+splitted[1]+".stats",true); // true tells to append
          out = new BufferedWriter(fstream);
          out.write(String.format("%s;%d;%d;%d;%d;%d\n",fileName,startTime,endTime,wrongAnswers,rightAnswers,totQuestions));
      } catch (IOException e){
          System.err.println("Error: " + e.getMessage());
      } finally {
         if (out!=null) {
             try {
                out.close();
             } catch (IOException e){
             }
         }
      }
   }// end writeToFile

}
