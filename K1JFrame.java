import java.awt.BorderLayout;
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;

public class K1JFrame extends JFrame implements ActionListener {

   private JButton button;
   private JTextField jTextFieldCenter;
   private JTextField jTextFieldSouth;

   private BorderLayout layout;
   private String answer;
   K1Iterator iterator ;

   public K1JFrame(K1Iterator myIterator) 
   {
       super ("Knowledge tool");
       iterator = myIterator;

       layout = new BorderLayout (5,5);

       setLayout(layout);

       button = new JButton("Verify Button");
       button.addActionListener(this);

       jTextFieldCenter = new JTextField();
       jTextFieldCenter.setText(iterator.getQuestion());
       jTextFieldCenter.setFont(new Font("Courier", Font.BOLD,12));
       jTextFieldSouth = new JTextField();
       jTextFieldSouth.addActionListener(this);

      add(jTextFieldSouth, BorderLayout.SOUTH);
      add(button, BorderLayout.EAST);
      add(jTextFieldCenter, BorderLayout.CENTER);
   }
   

   public void actionPerformed(ActionEvent event) 
   {
      if (event.getSource() == jTextFieldSouth) {
         answer = jTextFieldSouth.getText().trim();      
         iterator.next();
         jTextFieldCenter.setText(iterator.getQuestion());
         this.invalidate();
         this.validate();
         this.repaint();
      }

      if (event.getSource() == button) 
         button.setVisible(getResult()); 
      else  
         button.setVisible(true);

     layout.layoutContainer(getContentPane());
   } // end method actionPerformed


   public String getAnswer() 
   {
       return answer;
   }

   public boolean getResult() 
   {
      return true;
   } // end getResult
   
   public boolean getBoolean() 
   {
       return false;
   }

} // end class
