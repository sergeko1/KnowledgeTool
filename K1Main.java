import java.util.ArrayList;

public class K1Main {

   public static void main(String[] args) {

      // Build the JFrame with the K1Iterator to process the interactions 
      boolean random = true; // randomize questions
      String fileName = (args.length==1)?args[0]:"Files/default.txt";
      buildFrame(new K1Iterator(readFile(fileName), fileName, random)) ;
   } // main end

   // Method to build the main program JFrame
   static void buildFrame(K1Iterator iterator) {

       K1JFrame k1JFrame = new K1JFrame(iterator);
       k1JFrame.setDefaultCloseOperation(K1JFrame.EXIT_ON_CLOSE);
       k1JFrame.setSize(800,500);
       //k1JFrame.setExtendedState(K1JFrame.MAXIMIZED_BOTH); 
       k1JFrame.setVisible(true);
   } // end of buildFrame method

   static ArrayList<String> readFile(String file) {
      K1Reader reader = new K1Reader();
      reader.openFile(file);
      return reader.readRecordsToArrayList();
   }

}// Class end
