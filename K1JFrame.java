import java.awt.BorderLayout;
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class K1JFrame extends JFrame implements ActionListener {
   private JButton buttonEast;
   private JButton buttonWest;
   private JTextField jTextFieldNorth;
   private JTextField jTextFieldSouth;
   private JTextArea jTextAreaCenter;
   private Font myFont;

   //private BorderLayout layout;
   int counter = 0;
   boolean checkAnswer = true;
   K1Iterator iterator ;

   public K1JFrame(K1Iterator myIterator) {
      super (myIterator.getTitle());
      iterator = myIterator;
      addContent();
   }
   
   public void actionPerformed(ActionEvent event) {

      if (event.getSource() == jTextFieldSouth && counter <= iterator.size()) {

         // Logic to handle the list of question within a the JFrame
         if (iterator.hasNext() && !checkAnswer) { 
            buttonEast.setText(""+(counter+1)+"/"+iterator.size());
            iterator.next();
            jTextFieldNorth.setText(iterator.getQuestion());
            jTextAreaCenter.setText("");
            jTextFieldSouth.setText("");
            checkAnswer = true;
            setIcon("img/question.png"); // sets the question icon
         } else if ( checkAnswer ){
            boolean response = iterator.checkAnswer(jTextFieldSouth.getText());
            jTextAreaCenter.setText("The answer entered is "+response+"\nThe answer is:"+iterator.getAnswer());
            setIcon((response)?"img/OK.png":"img/NotOK.png"); // sets the correct Icon
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

  //   layout.layoutContainer(getContentPane());
   } // end method actionPerformed


   public void addContent() {

      myFont = new Font("Courier", Font.BOLD,18);

      buttonEast = new JButton((counter/2+1)+"/"+iterator.size());
      buttonEast.addActionListener(this);
      buttonEast.setFont(myFont);

      buttonWest = new JButton();
      buttonWest.addActionListener(this);

      jTextFieldNorth = new JTextField();
      jTextFieldNorth.setText(iterator.getQuestion());
      jTextFieldNorth.setFont(myFont);
      jTextFieldNorth.setEditable(false);

      jTextFieldSouth = new JTextField();
      jTextFieldSouth.addActionListener(this);

      jTextAreaCenter = new JTextArea();
      jTextAreaCenter.setFont(myFont);
      jTextAreaCenter.setEditable(false);

      add(jTextFieldSouth, BorderLayout.SOUTH);
      add(buttonEast, BorderLayout.EAST);
      add(buttonWest, BorderLayout.WEST);
      add(jTextFieldNorth, BorderLayout.NORTH);
      add(jTextAreaCenter, BorderLayout.CENTER);
      setIcon("img/question.png"); // sets the question icon
   
   }

   public void setIcon(String iconFile) {
      try {
         Image img = ImageIO.read(getClass().getResource(iconFile));
         buttonWest.setIcon(new ImageIcon(img));
      } catch (Exception ex) {
         System.out.println(ex);
      }
   }
} // end class
