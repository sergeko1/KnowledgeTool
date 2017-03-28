import java.awt.*;
import java.awt.event.*; 
import javax.imageio.ImageIO;
import javax.swing.*;

public class K1JFrame extends JFrame implements ActionListener {
  // private JButton buttonEast;
   private JTextArea jTextAreaEast;
   private JButton buttonWest;
   private JTextField jTextFieldNorth;
   private JTextField jTextFieldSouth;
   private JTextArea jTextAreaWest;
   private Font myFont;
   private K1StatsWriter statsWriter;

   //private BorderLayout layout;
   int counter = 0;
   boolean checkAnswer = true;
   K1Iterator iterator ;

   public K1JFrame(K1Iterator myIterator) {
      super (myIterator.getTitle()); // Gets The window title from the Iterator
      iterator = myIterator; // myIterator assigned to iterator in instance 
      statsWriter = new K1StatsWriter(iterator.size(), myIterator.getTitle());
      addContent(); // adds JButtons and JTextFields to the JFrame

   }
   
   public void actionPerformed(ActionEvent event) {

      if (event.getSource() == jTextFieldSouth && counter <= iterator.size()) {

         // Logic to handle the list of question within a the JFrame
         if (iterator.hasNext() && !checkAnswer) { 
            //buttonEast.setText(""+(counter+1)+"/"+iterator.size()+"\n"+statsWriter.printTotalString());
            iterator.next();
            jTextFieldNorth.setText(iterator.getQuestion());
            jTextAreaWest.setText("");
            jTextFieldSouth.setText("");
            checkAnswer = true;
            setIcon("img/question.png"); // sets the question icon
            jTextAreaEast.setText(statsWriter.printTotalString()+"\n"+"Progression:"+(counter+1)+"/"+iterator.size()+"\n");
         } else if ( checkAnswer ){
            boolean response = iterator.checkAnswer(jTextFieldSouth.getText());
            jTextAreaWest.setText("User Answer :"+jTextFieldSouth.getText()+"\nRight Answer:"+iterator.getAnswer());
            setIcon((response)?"img/OK.png":"img/NotOK.png"); // sets the correct Icon
            statsWriter.add(response); // adds the answer 
            jTextAreaEast.setText(statsWriter.printTotalString()+"\n"+"Progression:"+(counter+1)+"/"+iterator.size()+"\n");
            counter++;
            checkAnswer = false;
         } else if (counter == iterator.size()) {
            jTextFieldNorth.setText("");
            jTextFieldSouth.setText("");
            jTextAreaWest.setText("The test is finished");
            statsWriter.writeToFile();
            jTextFieldSouth.removeActionListener(this);
            setIcon("img/End.png");
            buttonWest.addActionListener(this);
         }

         // statsWriter.printTotal(); // sets the correct Icon
         // reload JFrame
         this.invalidate();
         this.validate();
         this.repaint();
      }

      // allows closing the window with the button a the end of the test
      if (event.getSource() == buttonWest) { 
         System.exit(0);
      }

   } // end method actionPerformed


   public void addContent() {

      myFont = new Font("Courier", Font.BOLD,14);

      //jTextAreaEast = new JTextArea("1"+"/"+iterator.size());
      jTextAreaEast = new JTextArea(statsWriter.printTotalString()+"\n"+"Progression:"+(counter+1)+"/"+iterator.size()+"\n",40,20);
      jTextAreaEast.setFont(new Font("Courier", Font.BOLD,14));
      jTextAreaEast.setEditable(false);
      jTextAreaEast.setLineWrap(true);

      buttonWest = new JButton();

      jTextFieldNorth = new JTextField();
      jTextFieldNorth.setText(iterator.getQuestion());
      jTextFieldNorth.setFont(myFont);
      jTextFieldNorth.setEditable(false);

      jTextFieldSouth = new JTextField();
      jTextFieldSouth.addActionListener(this);

      jTextAreaWest = new JTextArea(40,80);
      jTextAreaWest.setFont(myFont);
      jTextAreaWest.setEditable(false);
      jTextAreaWest.setLineWrap(true);


      add(jTextFieldSouth, BorderLayout.SOUTH);
      add(jTextAreaEast, BorderLayout.CENTER);
      add(buttonWest, BorderLayout.EAST);
      add(jTextFieldNorth, BorderLayout.NORTH);
      add(jTextAreaWest, BorderLayout.WEST);
      setIcon("img/question.png"); // sets the question icon
   
   }

   public void setIcon(String iconFile) {
      try {
         buttonWest.setIcon(new ImageIcon(iconFile));
      } catch (Exception ex) {
         System.out.println(ex);
      }
   }
} // end class
