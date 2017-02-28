import javax.swing.JFrame;

public class K1Main {
   public static void main(String[] args) {
      K1Layout borderLayoutFrame = new K1Layout();
      borderLayoutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      borderLayoutFrame.setSize(300,200);
      borderLayoutFrame.setVisible(true);

      Reader reader = new Reader();
      reader.openFile();
      reader.readRecords();
   }

   //reader.readRecords();
}
