import java.awt.*;
import java.awt.event.*; 
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;


public class K1JFrame extends JFrame implements ActionListener {
  // private JButton buttonEast;
   private JTextArea jTextAreaCenter;
   private JButton buttonEast1;
   private JButton buttonEast2;
   private JTextField jTextFieldNorth;
   private JTextField jTextFieldSouth;
   private JTextArea jTextAreaWest;
   private Font myFont;
   private K1StatsWriter statsWriter;
   boolean response = false;

   //private BorderLayout layout;
   //private GridLayout gridLayout;
   int counter = 0;
   boolean checkAnswer = true;
   K1Iterator iterator ;

   public K1JFrame(K1Iterator myIterator) {
      super (myIterator.getTitle()); // Gets The window title from the Iterator
      this.setDefaultCloseOperation(K0JFrame.EXIT_ON_CLOSE);
      this.setSize(1200,500);
      this.setVisible(true);
      iterator = myIterator; // myIterator assigned to iterator in instance 
      statsWriter = new K1StatsWriter(iterator.size(), myIterator.getTitle());
      statsWriter.setStartTime();
      statsWriter.setEndTimeAsStartTime();
      addContent(); // adds JButtons and JTextFields to the JFrame
   }
   
   public void actionPerformed(ActionEvent event) {

      if (event.getSource() == buttonEast2 && checkAnswer==true && buttonEast2.getText().equals("Next") && counter<=iterator.size()) {
           System.out.println("inside next");
           event=new ActionEvent(jTextFieldSouth,1,"");
       }

        if (event.getSource() == jTextFieldSouth && counter <= iterator.size()) {
         if (iterator.hasNext() && !checkAnswer) { 
            iterator.next();
            generateQuestion();
         } else if ( checkAnswer ){
            generateResponse();
         } else if (counter == iterator.size()) {
            finalPage();
         }

      }


      // repeat a question with the repeat button
      if (event.getSource() == buttonEast2 && checkAnswer==false && counter<=iterator.size() && buttonEast2.getText().equals("Repeat")) { 

          System.out.println("response " + response);
          if (response)
             statsWriter.rightAnswers--;  
           else
             statsWriter.wrongAnswers--;  
           counter--;
          generateQuestion();
          event=new ActionEvent(jTextFieldSouth,1,"");
      }

      // allows closing the window with the button a the end of the test
      if (event.getSource() == buttonEast1 || event.getSource() == buttonEast2) { 
         System.exit(0);
      }

      this.invalidate();
      this.validate();
      this.repaint();
   } // end method actionPerformed


   public void addContent() {
      JPanel eastPanel = new JPanel(new GridLayout(2,1));

      myFont = new Font("Courier", Font.BOLD,14);

      //jTextAreaCenter = new JTextArea("1"+"/"+iterator.size());
      jTextAreaCenter = new JTextArea();
      jTextAreaCenter.setFont(new Font("Courier", Font.BOLD,14));
      jTextAreaCenter.setEditable(false);
      jTextAreaCenter.setLineWrap(true);

      buttonEast1 = new JButton();
      buttonEast2 = new JButton("Repeat");

      jTextFieldNorth = new JTextField();
      jTextFieldNorth.setFont(myFont);
      jTextFieldNorth.setEditable(false);

      jTextFieldSouth = new JTextField();
      jTextFieldSouth.addActionListener(this);

      jTextAreaWest = new JTextArea(40,90);
      jTextAreaWest.setFont(myFont);
      jTextAreaWest.setEditable(false);
      jTextAreaWest.setLineWrap(true);

      eastPanel.add(buttonEast1);
      eastPanel.add(buttonEast2);
      buttonEast2.addActionListener(this);

      add(jTextFieldSouth, BorderLayout.SOUTH);
      add(jTextAreaCenter, BorderLayout.CENTER);
      add(eastPanel, BorderLayout.EAST);
      add(jTextFieldNorth, BorderLayout.NORTH);
      add(jTextAreaWest, BorderLayout.WEST);

      generateQuestion();
   
   }


   public void generateQuestion() {
       System.out.println("Generate Question");
       buttonEast2.setText("Next");
       jTextFieldNorth.setText(iterator.getQuestion());
       jTextAreaWest.setText("");
       jTextFieldSouth.setText("");
       checkAnswer = true;
       setIcon("img/question.png"); // sets the question icon
       statsWriter.setEndTime();
       jTextAreaCenter.setText(statsWriter.printTotalString()+"\n"+"\nProgression:"+(counter+1)+"/"+iterator.size()+"\n");
   }


   public void generateResponse() {
       System.out.println("Generate Response");
       buttonEast2.setText("Repeat");
       response = iterator.checkAnswer(jTextFieldSouth.getText());
       jTextAreaWest.setText("User Answer :"+jTextFieldSouth.getText()+"\nRight Answer:"+iterator.getAnswer());
       setIcon((response)?"img/OK.png":"img/NotOK.png"); // sets the correct Icon
       play((response)?"snd/OK.wav":"snd/NotOK.wav"); // sets the correct Icon
       statsWriter.add(response); // adds the answer 
       statsWriter.setEndTime();
       jTextAreaCenter.setText(statsWriter.printTotalString()+"\n"+"\nProgression:"+(counter+1)+"/"+iterator.size()+"\n");
       checkAnswer = false;
       counter++;
   }

   public void finalPage() {
       buttonEast2.setText("End");
       jTextFieldNorth.setText("");
       jTextFieldSouth.setText("");
       jTextAreaWest.setText("The test is finished");
       jTextFieldSouth.removeActionListener(this);
       setIcon("img/End.png");
       buttonEast1.addActionListener(this);
       statsWriter.highestScoreFromStatFile();
       jTextAreaCenter.setText(statsWriter.printTotalString()+"\n"+"\nProgression:"+iterator.size()+"/"+iterator.size()+"\n");
       statsWriter.writeToFile();
   }

   public void setIcon(String iconFile) {
      try {
         buttonEast1.setIcon(new ImageIcon(iconFile));
      } catch (Exception ex) {
         System.out.println(ex);
      }
   }


    //public void play(String file) throws LineUnavailableException, UnsupportedAudioFileException, IOException
    public void play(String file) 
    {

    try 
        {   
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(file));
            AudioFormat format = inputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip)AudioSystem.getLine(info);
            clip.open(inputStream);
            clip.start();
        }

    catch (IOException | LineUnavailableException | UnsupportedAudioFileException e1)
        {
            e1.printStackTrace();
        }
    }

} // end class
