import java.util.ArrayList;

public class K1Main {

   public static void main(String[] args) {
      // Build the JFrame with the K1Iterator to process the interactions 
      if (args.length == 1)
        buildFrame(new K1Iterator(readFile(args[0]), args[0])) ;
      else 
        buildFrame(new K1Iterator(readFile("know/default.txt"), "know/default.txt")) ;
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
