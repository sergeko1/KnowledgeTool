import javax.swing.JFrame;
import java.util.ArrayList;

public class K1Main {
   public static void main(String[] args) {

      // READS FILE TO AN ARRAYLIST 
      K1Reader reader = new K1Reader();
      reader.openFile();
      ArrayList<String> list = reader.readRecordsToArrayList();

      // Instance to get the interactions 
      K1Iterator iterator = new K1Iterator(list);

      // CREATES A FRAME WITH THE FIRST QUESTION
      K1JFrame k1JFrame = new K1JFrame(iterator);
      k1JFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      k1JFrame.setSize(600,400);
      k1JFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
      k1JFrame.setVisible(true);

   } // main end
}// Class end
