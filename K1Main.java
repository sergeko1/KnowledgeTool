public class K1Main {
   public static void main(String[] args) {
      new K1JFrame(new K1Iterator(((args.length==1)?args[0]:"Files/default.txt"),true));
   }
}
