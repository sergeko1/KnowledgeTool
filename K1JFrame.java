import java.awt.BorderLayout;
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;

public class K1JFrame extends JFrame implements ActionListener {
   private JButton buttonEast;
   private JButton buttonWest;
   private JTextField jTextFieldNorth;
   private JTextField jTextFieldSouth;
   private JTextArea jTextAreaCenter;

   private BorderLayout layout;
   int counter = 0;
   boolean checkAnswer = true;
   K1Iterator iterator ;

   public K1JFrame(K1Iterator myIterator) {
      super ("Knowledge tool");
      iterator = myIterator;

      layout = new BorderLayout (5,5);

      setLayout(layout);

      buttonWest = new JButton((counter/2+1)+"/"+iterator.size());
      buttonWest.addActionListener(this);

      buttonEast = new JButton();
      buttonEast.addActionListener(this);

      jTextFieldNorth = new JTextField();
      jTextFieldNorth.setText(iterator.getQuestion());
      jTextFieldNorth.setFont(new Font("Courier", Font.BOLD,16));

      jTextFieldSouth = new JTextField();
      jTextFieldSouth.addActionListener(this);

      jTextAreaCenter = new JTextArea();
      jTextAreaCenter.setFont(new Font("Courier", Font.BOLD,16));

      add(jTextFieldSouth, BorderLayout.SOUTH);
      add(buttonEast, BorderLayout.EAST);
      add(buttonWest, BorderLayout.WEST);
      add(jTextFieldNorth, BorderLayout.NORTH);
      add(jTextAreaCenter, BorderLayout.CENTER);
   }
   
   public void actionPerformed(ActionEvent event) {

      if (event.getSource() == jTextFieldSouth && counter <= iterator.size()) {

         // Logic to handle the list of question within a the JFrame
         if (iterator.hasNext() && !checkAnswer) { 
            buttonWest.setText(""+(counter+1)+"/"+iterator.size());
            iterator.next();
            jTextFieldNorth.setText(iterator.getQuestion());
            jTextAreaCenter.setText("");
            jTextFieldSouth.setText("");
            checkAnswer = true;
         } else if ( checkAnswer ){
            boolean response = iterator.checkAnswer(jTextFieldSouth.getText());
            jTextAreaCenter.setText("The answer entered is "+response+"\nThe answer is:"+iterator.getAnswer());
            counter++;
            checkAnswer = false;
         } else if (counter == iterator.size()) {
            jTextFieldNorth.setText("");
            jTextFieldSouth.setText("");
            jTextAreaCenter.setText("The test is finished");
         }

         this.invalidate();
         this.validate();
         this.repaint();
      }

     layout.layoutContainer(getContentPane());
   } // end method actionPerformed

} // end class
