import java.util.ArrayList;

public class K1Main 
{
   public static void main(String[] args) 
   {
      // READS FILE TO AN ARRAYLIST 
      K1Reader reader = new K1Reader();
      reader.openFile();
      ArrayList<String> list = reader.readRecordsToArrayList();

      // Instance to get the interactions 
      K1Iterator iterator = new K1Iterator(list);

      // Build the JFrame with the K1Iterator to process the interactions 
      buildFrame(iterator) ;
   } // main end

   // Method to build the main program JFrame
   static void buildFrame(K1Iterator iterator) 
   {
       K1JFrame k1JFrame = new K1JFrame(iterator);
       k1JFrame.setDefaultCloseOperation(K1JFrame.EXIT_ON_CLOSE);
       k1JFrame.setSize(600,400);
       k1JFrame.setExtendedState(K1JFrame.MAXIMIZED_BOTH); 
       k1JFrame.setVisible(true);
   } // end of buildFrame method

}// Class end
