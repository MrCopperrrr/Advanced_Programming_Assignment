// Source code is decompiled from a .class file using FernFlower decompiler.
import java.util.Random;

public class Battle {
   private static final double RATE_WIN = 0.5;
   private static final int GROUND_BOUND = 999;
   public static int GROUND = 1;
   private Combatable[] mTeam1;
   private Combatable[] mTeam2;

   private static long seed;
	public static void setSeed(long s) {
		seed = s;
	}

    public static void moveRandomGround() {
        Random var0 = new Random(seed);
      GROUND = var0.nextInt(999) + 1;
    //   System.out.println(" Moving to ground " + GROUND + ".");
   }

   public Battle(Combatable[] var1, Combatable[] var2) {
      this.mTeam1 = var1;
      this.mTeam2 = var2;
   }

   public void combat() {
      double var1 = 0.0;

      for(int var3 = 0; var3 < this.mTeam1.length; ++var3) {
         double var4 = this.duel(this.mTeam1[var3], this.mTeam2[var3]);
         var1 += var4;
         if (var3 == 0 && var4 >= 0.5) {
            moveRandomGround();
         }
      }

      var1 /= (double)this.mTeam1.length;
      System.out.println(" Battle result. pR = " + var1);
   }

   private double duel(Combatable var1, Combatable var2) {
      double var3 = var1.getCombatScore();
      double var5 = var2.getCombatScore();
      double var7 = (var3 - var5 + 999.0) / 2000.0;
      return var7;
   }
}
