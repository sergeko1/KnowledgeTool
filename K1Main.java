public class K1Main {
   public static void main(String[] args) {
      // Build the JFrame with the K1Iterator to process the interactions 
      new K1JFrame(new K1Iterator(((args.length==1)?args[0]:"Files/default.txt"),true));
   } // main end
}// Class end
