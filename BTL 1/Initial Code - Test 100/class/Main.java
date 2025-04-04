//javac -cp class -d class source/*.java util/*.java class/*.java
//java -cp class Main
import java.util.Arrays;

public class Main {
   public Main() {
   }

   public static void main(String[] var0) {

       for (int i = 1; i <= 100; i++) {
           System.out.println("\n ---Testcase " + i + "--- \n");
           Battle.setSeed(i);
           TeamMaker.setSeed(i);

        //    System.out.println("Battle Begin.");
           Battle.moveRandomGround();
           Combatable[] var1 = TeamMaker.makeTeam1();
           Combatable[] var2 = TeamMaker.makeTeam2();
        //    System.out.println("Team 1 is " + Arrays.toString(var1));
        //    System.out.println("Team 2 is " + Arrays.toString(var2));
           Battle var3 = new Battle(var1, var2);
           var3.combat();
        //    System.out.println("Battle End. \n");
       }
      System.out.println("Made by Trung4n");
   }
}