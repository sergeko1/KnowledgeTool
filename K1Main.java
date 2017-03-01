import javax.swing.JFrame;
import java.util.ArrayList;

public class K1Main {
   public static void main(String[] args) {

      // READS FILE TO AN ARRAYLIST 
      K1Reader reader = new K1Reader();
      reader.openFile();
      ArrayList<String> list = reader.readRecordsToArrayList();

      // GETS THE FIRST QUESTION
      K1Question question;
      question = new K1Question(list.get(0));

      // CREATES A FRAME WITH THE FIRST QUESTION
      K1Layout borderLayoutFrame = new K1Layout(question.getQuestion());
      borderLayoutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      borderLayoutFrame.setSize(600,400);
      borderLayoutFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
      borderLayoutFrame.setVisible(true);

      // ITERATE THROUGH QUESTIONS
      for (int i=0; i<list.size(); i++) {
         System.out.println(list.get(i));
      //   question = new K1Question(list.get(i));
      } // for end

   } // main end

}
