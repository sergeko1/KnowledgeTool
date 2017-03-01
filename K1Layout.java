import java.awt.BorderLayout;
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class K1Layout extends JFrame implements ActionListener {

   private JButton[] buttons;
   private JTextArea jTextArea;

   private static final String[] names = {"Knowledge", "Result", "Stats", "Answer", "Question" };
   private BorderLayout layout;
   K1Reader reader;

   public K1Layout(String question) {

       super ("Border Layout Demo");

       layout = new BorderLayout (5,5);
       reader = new K1Reader();

       setLayout(layout);

       buttons = new JButton[names.length];

       for (int count = 0; count<names.length;count++) {
          buttons[count] = new JButton(names[count]);
          buttons[count].addActionListener(this);
       }

       reader.openFile();
       jTextArea = new JTextArea();
       jTextArea.append(question);

      add(buttons[0], BorderLayout.NORTH);
      add(buttons[1], BorderLayout.SOUTH);
      add(buttons[2], BorderLayout.EAST);
      add(buttons[3], BorderLayout.WEST);
      add(jTextArea, BorderLayout.CENTER);

   }
   
   public void actionPerformed(ActionEvent event) {
      for (JButton button : buttons) {
          if (event.getSource() == button) 
            //button.setVisible(false); 
            button.setVisible(getResult()); 
          else 
            button.setVisible(true);
      }
      layout.layoutContainer(getContentPane());
   } // end method actionPerformed


   public boolean getResult() {
      return true;
   } // end getResult
   
   public boolean getBoolean() {
       return false;
   }

} // end class
