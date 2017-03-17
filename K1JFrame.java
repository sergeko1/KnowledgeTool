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
   K1Iterator iterator ;

   public K1JFrame(K1Iterator myIterator) {
      super ("Knowledge tool");
      iterator = myIterator;

      layout = new BorderLayout (5,5);

      setLayout(layout);

      button = new JButton("Verify Button");
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
   }
   
   public void actionPerformed(ActionEvent event) {
      if (event.getSource() == jTextFieldSouth) {
         boolean response = iterator.checkAnswer(jTextFieldSouth.getText());
         System.out.println("--"+jTextFieldSouth.getText()+"--");
         System.out.println("--"+iterator.getAnswer()+"--");
         System.out.println(jTextFieldSouth.getText());
         jTextFieldCenter.setText("The answer is "+response);

         if (iterator.hasNext()) { 
            iterator.next();
            jTextFieldNorth.setText(iterator.getQuestion());
         } else {
            jTextFieldNorth.setText("The test is finished");
         }

         this.invalidate();
         this.validate();
         this.repaint();
      }

      if (event.getSource() == button) 
         //button.setVisible(getResult()); 
         System.out.println("Check Answer");
      //else  
       //  button.setVisible(true);

     layout.layoutContainer(getContentPane());
   } // end method actionPerformed

} // end class
