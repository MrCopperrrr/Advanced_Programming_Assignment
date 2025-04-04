
import java.io.File;
import java.io.PrintWriter;

public class Battle {
    private static final double RATE_WIN = 0.5;
    private static final int GROUND_BOUND = 999;
    public static int GROUND = 1;
    private Combatable[] mTeam1;
    private Combatable[] mTeam2;

    public static int index_ground;
    public static int open_count = 0;

    public static void moveRandomGround() {
        try {
            File file = new File("input/ground/ground" + index_ground + ".txt");
            java.util.Scanner scanner = new java.util.Scanner(file);
            if(open_count == 0){
                //READ INT IN THE FIRST LINE
                GROUND = scanner.nextInt();
                open_count++;
            }else{
                if(open_count == 1){
                    scanner.nextLine();
                    open_count++;
                    GROUND = scanner.nextInt();
                }else{
                    scanner.nextLine();
                    scanner.nextLine();
                    GROUND = scanner.nextInt();
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error reading file.");
        }
    }

    public Battle(Combatable[] team1, Combatable[] team2) {
        this.mTeam1 = team1;
        this.mTeam2 = team2;
    }

    public void combat(PrintWriter writer) {
        double totalScore = 0.0;

        for (int i = 0; i < this.mTeam1.length; i++) {
            writer.println("================== Battle " + i + " ==================");
            writer.println("Fighter 1: " + this.mTeam1[i]);
            writer.println("Fighter 2: " + this.mTeam2[i]);
            writer.println("Ground: " + GROUND);
            double duelResult = this.duel(this.mTeam1[i], this.mTeam2[i], writer);
            writer.println("Duel Result: " + duelResult);
            totalScore += duelResult;

            if (i == 0 && duelResult >= RATE_WIN) {
                moveRandomGround();
            }

            writer.println("==============================================");
        }

        totalScore /= this.mTeam1.length;
        writer.println("Battle result. pR = " + totalScore); //pR is the probability of team1 winning
    }

    private double duel(Combatable fighter1, Combatable fighter2, PrintWriter writer) {
        double score1 = fighter1.getCombatScore();
        writer.println("Fighter 1 score: " + score1);
        double score2 = fighter2.getCombatScore();
        writer.println("Fighter 2 score: " + score2);
        return (score1 - score2 + GROUND_BOUND) / (2.0 * (GROUND_BOUND + 1));
    }
}