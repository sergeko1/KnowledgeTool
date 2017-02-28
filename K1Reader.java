import java.io.File;
import java.io.FileNotFoundException;
import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Reader {

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

   public String readRecordsString() {
      String string = new String();
      while (input.hasNext()) {
        string = string + " " +  input.next();
      }
      return string;
   }


   public void print() {
      System.out.println("record");
   }
}

