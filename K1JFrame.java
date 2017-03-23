import java.awt.BorderLayout;
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;

public class K1JFrame extends JFrame implements ActionListener {
   private JButton button;
   private JTextField jTextFieldNorth;
   private JTextField jTextFieldSouth;
   private JTextField jTextFieldCenter;

   private BorderLayout layout;
   int counter = 0;
   boolean checkAnswer = true;
   K1Iterator iterator ;

   public K1JFrame(K1Iterator myIterator) {
      super ("Knowledge tool");
      iterator = myIterator;

      layout = new BorderLayout (5,5);

      setLayout(layout);

      button = new JButton((counter/2+1)+"/"+iterator.size());
      button.addActionListener(this);

      jTextFieldNorth = new JTextField();
      jTextFieldNorth.setText(iterator.getQuestion());
      jTextFieldNorth.setFont(new Font("Courier", Font.BOLD,16));

      jTextFieldSouth = new JTextField();
      jTextFieldSouth.addActionListener(this);

      jTextFieldCenter = new JTextField();

      add(jTextFieldSouth, BorderLayout.SOUTH);
      add(button, BorderLayout.EAST);
      add(jTextFieldNorth, BorderLayout.NORTH);
      add(jTextFieldCenter, BorderLayout.CENTER);
      System.out.println(iterator.size()+1); 
   }
   
   public void actionPerformed(ActionEvent event) {

      if (event.getSource() == jTextFieldSouth && counter <= iterator.size()) {

         // Logic to handle the list of question within a the JFrame
         if (iterator.hasNext() && !checkAnswer) { 
            button.setText(""+(counter+1)+"/"+iterator.size());
            iterator.next();
            jTextFieldNorth.setText(iterator.getQuestion());
            jTextFieldCenter.setText("");
            jTextFieldSouth.setText("");
            checkAnswer = true;
         } else if ( checkAnswer ){
            boolean response = iterator.checkAnswer(jTextFieldSouth.getText());
            jTextFieldCenter.setText("The answer is "+response);
            counter++;
            checkAnswer = false;
         } else if (counter == iterator.size()) {
            jTextFieldNorth.setText("");
            jTextFieldSouth.setText("");
            jTextFieldCenter.setText("The test is finished");
         }

         this.invalidate();
         this.validate();
         this.repaint();
      }

     layout.layoutContainer(getContentPane());
   } // end method actionPerformed

} // end class
