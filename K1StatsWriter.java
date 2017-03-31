import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

public class K1StatsWriter {

   int wrongAnswers = 0;
   int rightAnswers = 0;
   int totQuestions = 0;
   long startTime;
   long endTime;
   String fileName;
   float timeHighestScore = 0;
   int answersHighestScore = 0;
   String highScoreString = "";
   String oldHighScoreString = "";

   K1StatsWriter(int numQuestions, String myFileName) {
       totQuestions = numQuestions;
       fileName = getStatsFileName(myFileName);
       System.out.println(fileName);
   } 

   void add(boolean result) {
      if (result)
          rightAnswers++;
      else
          wrongAnswers++;
   }

   void setStartTime() {
      startTime=System.nanoTime();
   }

   void setEndTime() {
      endTime=System.nanoTime();
   }

   void setEndTimeAsStartTime() {
       endTime=startTime;
   }

   void printTotal() {
       System.out.println("Right Answers " + rightAnswers);
       System.out.println("Wrong Answers " + wrongAnswers);
       System.out.println("Answers Left " + (totQuestions-rightAnswers-wrongAnswers));
       System.out.println("Total Questions " + totQuestions);
       System.out.println("Elapsed Time " + (double)((endTime-startTime)/1000000000));

   }

   String printTotalString() {
       setHighestScoresString();
       return "Right Answers " + rightAnswers + " \nWrong Answers " + wrongAnswers + 
           " \nAnswers Left " + (totQuestions-rightAnswers-wrongAnswers + " \nTotal Questions " + totQuestions + "\nElapsed Time "+String.format("%.3f",((double)(endTime-startTime))/1000000000)+" "+oldHighScoreString+highScoreString);
   }

   void writeToFile() {
      BufferedWriter out = null;
      try {
          FileWriter fstream = new FileWriter(fileName,true); // true tells to append
          out = new BufferedWriter(fstream);
          out.write(String.format("%s;%d;%d;%d;%d;%d;%d;%.3f\n",fileName,startTime,endTime,wrongAnswers,rightAnswers,totQuestions,answersHighestScore,timeHighestScore));
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
   }


   // gets the last record from the stats File
   public void highestScoreFromStatFile() {
       float sessionTime = ((float)(endTime-startTime))/1000000000; 
       File myFile = new File(fileName);
       if (myFile.exists()) {
           ArrayList<String> arrayList = K1Iterator.readFileArrayList(fileName);
           String myRecord = arrayList.get(arrayList.size()-1);
           String[] fields = myRecord.split(";"); 
           answersHighestScore = Integer.parseInt(fields[6]);
           timeHighestScore = Float.parseFloat(fields[7]);
           if (rightAnswers>answersHighestScore) {
               answersHighestScore = rightAnswers;
               timeHighestScore = sessionTime; 
           } else if (rightAnswers == answersHighestScore) {
               if (timeHighestScore>sessionTime) {
                  timeHighestScore=sessionTime;
               }
           }
       } else {
          answersHighestScore = rightAnswers;
          timeHighestScore = sessionTime; 
       }
       highScoreString = "\n"+"New High Score right answers:"+rightAnswers+"\nTime: "+sessionTime;
   }

   public void setHighestScoresString() {
       File myFile = new File(fileName);
       if (myFile.exists()) {
           ArrayList<String> arrayList = K1Iterator.readFileArrayList(fileName);
           String[] fields = arrayList.get(arrayList.size()-1).split(";"); 
           int answersHighestScore = Integer.parseInt(fields[6]);
           float timeHighestScore = Float.parseFloat(fields[7]);
           oldHighScoreString = "\nMax Answers "+answersHighestScore+"\nLowest Time "+timeHighestScore;
       } else {
           oldHighScoreString = "\nMax Answers "+answersHighestScore+"\nLowest Time "+timeHighestScore;
       }
   }

   // builds the stats filename String
   public String getStatsFileName(String fileName) {
       String[] splitted = fileName.split(".*/");
       return "stats/"+splitted[1]+".stats";
   } 
   
}
