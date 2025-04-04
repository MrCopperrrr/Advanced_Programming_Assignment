//javac -cp class -d class source/*.java util/*.java Main.java
//java -cp class Main

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Battle Begin.");

        
        Battle.GROUND = 506;
        System.out.println("Moving to ground " + Battle.GROUND + ".");

        
        Combatable[] team1 = TeamMaker.makeTeam1();
        Combatable[] team2 = TeamMaker.makeTeam2();

        System.out.println("\nTeam 1 is " + Arrays.toString(team1));
        for (Combatable c : team1) {
            System.out.println("   " + c + " Combat Score: " + c.getCombatScore());
        }

        System.out.println("\nTeam 2 is " + Arrays.toString(team2));
        for (Combatable c : team2) {
            System.out.println("   " + c + " Combat Score: " + c.getCombatScore());
        }

        
        Battle.moveRandomGround();
        System.out.println("\nAfter move: Battle.GROUND = " + Battle.GROUND);

        Battle battle = new Battle(team1, team2);
        battle.combat();

        System.out.println("Battle End.");
    }
}