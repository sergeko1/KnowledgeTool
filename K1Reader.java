import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class K1Reader {

   private Scanner input;
 
   // Opens the file for reading
   public void openFile(String file) {
      try {
         input = new Scanner(new File(file)); 
      }  
      catch (FileNotFoundException fileNotFoundException) {
          System.err.println("Error opening File");
          System.exit(1); 
      }
   }

   // Reads the files to tan ArrayList<String>
   public ArrayList<String> readRecordsToArrayList() {
      ArrayList<String> lines = new ArrayList<String>();
      while (input.hasNextLine()) {
        lines.add(input.nextLine());
      }
      return lines;
   } 
}
