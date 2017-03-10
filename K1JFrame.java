import java.awt.BorderLayout;
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;

public class K1JFrame extends JFrame implements ActionListener {

   private JButton[] buttons;
   private JTextField jTextFieldCenter;
   private JTextField jTextFieldSouth;

   private static final String[] names = {"Knowledge", "Result", "Stats", "Answer", "Question" };
   private BorderLayout layout;
   private String answer;
   K1Reader reader;
   K1Iterator iterator ;


   public K1JFrame(K1Iterator myIterator) {

       super ("Knowledge tool");
       iterator = myIterator;

       layout = new BorderLayout (5,5);
       reader = new K1Reader();

       setLayout(layout);

       buttons = new JButton[names.length];

       for (int count = 0; count<names.length;count++) {
          buttons[count] = new JButton(names[count]);
          buttons[count].addActionListener(this);
       }

       reader.openFile();
       jTextFieldCenter = new JTextField();
       jTextFieldCenter.setText(iterator.getQuestion());
       jTextFieldCenter.setFont(new Font("Courier", Font.BOLD,12));
       jTextFieldSouth = new JTextField();
       jTextFieldSouth.addActionListener(this);

      add(buttons[0], BorderLayout.NORTH);
      add(jTextFieldSouth, BorderLayout.SOUTH);
      add(buttons[2], BorderLayout.EAST);
      add(buttons[3], BorderLayout.WEST);
      add(jTextFieldCenter, BorderLayout.CENTER);

   }
   

   public void actionPerformed(ActionEvent event) {
      if (event.getSource() == jTextFieldSouth) {
          answer = jTextFieldSouth.getText().trim();      
          iterator.next();
          jTextFieldCenter.setText(iterator.getQuestion());
          this.invalidate();
          this.validate();
          this.repaint();
      }

      for (JButton button : buttons) {
          if (event.getSource() == button) 
            //button.setVisible(false); 
            button.setVisible(getResult()); 
          else 
            button.setVisible(true);
      }
      layout.layoutContainer(getContentPane());
   } // end method actionPerformed


   public String getAnswer() {
       return answer;
   }

   public boolean getResult() {
      return true;
   } // end getResult
   
   public boolean getBoolean() {
       return false;
   }

} // end class
