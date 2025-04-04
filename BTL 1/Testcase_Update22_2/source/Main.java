/*
 * Dao Phu Thai - thai.daocs2353087@hcmut.edu.vn
 * Computer Science - 2023
 * Faculty of Computer Science and Engineering
 * Ho Chi Minh City University of Technology
 * 
 * Description:
 * I have change Battle.java, TeamMaker.java, Main.java for testing the program.
 * 
 *
 * Instruction to run the program:
 * 0. Paste your work in the source folder. (don't delete Battle.java, Main.java)
 * 1. Open terminal or cmd
 * 2. Compile all the files in the source folder:
 *         javac -d class -cp class source/*.java util/*.java
 * 3. Run the program:
 *        java -cp class Main
 *
 * Hướng dẫn chạy chương trình:
 * 1. Mở terminal hoặc cmd
 * 2. Compile các file trong thư mục source: 
 *          javac -d class -cp class source/*.java util/*.java
 * 3. Chạy chương trình:
 *          java -cp class Main
 */

import java.io.PrintWriter;
import java.util.Arrays;
import java.io.File;
public class Main {
    public Main() {
    }

    public static void main(String[] var0) {
        for(int i = 0; i < 1000; i++){
            Battle.index_ground = i;
            Battle.open_count = 0;
            Battle.moveRandomGround();
            try {
                String hp_wp1 = "input/team1/team1_" + i + ".txt";
                String hp_wp2 = "input/team2/team2_" + i + ".txt";
                File file_team1 = new File(hp_wp1);
                File file_team2 = new File(hp_wp2);
                Combatable[] team1 = TeamMaker.makeTeam1(file_team1);
                Combatable[] team2 = TeamMaker.makeTeam2(file_team2);

                Battle battle = new Battle(team1, team2);

                String output = "output/battle_result" + i + ".txt";
                PrintWriter writer = new PrintWriter( output, "UTF-8");
                writer.println("Battle Begin.");
                writer.println("Team 1 is " + Arrays.toString(team1));
                writer.println("Team 2 is " + Arrays.toString(team2));
                battle.combat(writer);
                writer.println("Battle End.");
                writer.close();
            } catch (Exception e) {
                System.out.println("Error writing to file.");
            }
        }
        //CHECK THE RESULT FROM THE OUTPUT FILE
        System.out.println("=================RESULT=================");
        String fail_testcase = "";
        for(int i = 0; i < 1000; i++){
            try {
                File file = new File("output/battle_result" + i + ".txt");
                File file_sample = new File("output_sample/battle_result" + i + ".txt");
                java.util.Scanner scanner = new java.util.Scanner(file);
                java.util.Scanner scanner_sample = new java.util.Scanner(file_sample);
                boolean error = false;
                String result = "";
                while(scanner.hasNextLine() && scanner_sample.hasNextLine()){
                    String line = scanner.nextLine();
                    String line_sample = scanner_sample.nextLine();
                    if(!line.equals(line_sample)){
                        error = true;
                        fail_testcase += i + "  ";
                        break;
                    }
                }
                scanner_sample.close();
                scanner.close();
            } catch (Exception e) {
                System.out.println("Error reading file.");
            }
        }
        if(fail_testcase.length() > 0){
            System.out.println("\u001B[31m" + "Incorrect Testcase(s):\n" + fail_testcase + "\u001B[0m");
        } else
        System.out.println("\u001B[32m" + "All testcases are correct." + "\u001B[0m");
    }
}