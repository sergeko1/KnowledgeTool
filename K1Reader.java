import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class K1Reader {

   private Scanner input;

   public void openFile() {
      try {
         input = new Scanner(new File("know/test.txt")); 
      }  
      catch (FileNotFoundException fileNotFoundException) {
          System.err.println("Error opening File");
          System.exit(1); 
      }
   }

   public void readRecords() {
      while (input.hasNext()) {
        System.out.println(input.next());
      }
   }

   public ArrayList<String> readRecordsToArrayList() {
//      String string = new String();
      //input.useDelimiter("//");
      ArrayList<String> lines = new ArrayList<String>();
      while (input.hasNextLine()) {
        //string = string + " " +  input.next();
        lines.add(input.nextLine());
      }
      return lines;
   } 

   public void print() {
      System.out.println("record");
   }
}

