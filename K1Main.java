import javax.swing.JFrame;
import java.util.ArrayList;

public class K1Main {
   public static void main(String[] args) {

      // READS FILE TO AN ARRAYLIST 
      K1Reader reader = new K1Reader();
      reader.openFile();
      ArrayList<String> list = reader.readRecordsToArrayList();

      // GETS THE FIRST QUESTION
      K1Iterator iterator = new K1Iterator(list);

      // CREATES A FRAME WITH THE FIRST QUESTION
      K1Layout borderLayoutFrame = new K1Layout(iterator);
      borderLayoutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      borderLayoutFrame.setSize(600,400);
      borderLayoutFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
      borderLayoutFrame.setVisible(true);

   } // main end
}// Class end
