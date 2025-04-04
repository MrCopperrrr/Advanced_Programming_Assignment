//import java.util.Random;
import java.io.File;
// import java.io.PrintWriter;

/**
 * <p>This class is used for creating the team in a combat.</p>
 * 
 * <p> <b>Note: This class will be replaced by other version when being judged.</b></p>
 */
public class TeamMaker {
	private static Combatable makeRandomTeam1Member(File file_team1, int i) {
		int baseHP = 0;
		int wp = 0;
		try{
			java.util.Scanner scanner = new java.util.Scanner(file_team1);
			if(i == 0){
				baseHP = scanner.nextInt();
				wp = scanner.nextInt();
			}
			if(i == 1){
				scanner.nextLine();
				scanner.nextLine();
				scanner.nextLine();
				baseHP = scanner.nextInt();
				wp = scanner.nextInt();
			}
			if(i == 2){
				scanner.nextLine();
				scanner.nextLine();
				scanner.nextLine();
				scanner.nextLine();
				scanner.nextLine();
				scanner.nextLine();
				baseHP = scanner.nextInt();
				wp = scanner.nextInt();
			}
			scanner.close();
		}
		catch(Exception e){
			System.out.println("Error reading file1.");
		}
		return (wp % 2 == 0) ? new Knight(baseHP, wp) : new Paladin(500, 1);
	}

	private static Combatable makeRandomTeam2Member(File file_team2, int i) {
		int baseHP = 0;
		int wp = 0;
		try{
			java.util.Scanner scanner = new java.util.Scanner(file_team2);
			if(i == 0){
				baseHP = scanner.nextInt();
				wp = scanner.nextInt();
			}
			if(i == 1){
				scanner.nextLine();
				scanner.nextLine();
				scanner.nextLine();
				baseHP = scanner.nextInt();
				wp = scanner.nextInt();
			}
			if(i == 2){
				scanner.nextLine();
				scanner.nextLine();
				scanner.nextLine();
				scanner.nextLine();
				scanner.nextLine();
				scanner.nextLine();
				baseHP = scanner.nextInt();
				wp = scanner.nextInt();
			}
			scanner.close();
		}
		catch(Exception e){
			System.out.println("Error reading file2.");
		}
		return (Battle.GROUND % 2 == 0) ? new Warrior(baseHP, wp) : new DeathEater(new Complex(300, 200));
	}

	public static Combatable[] makeTeam1(File file_team1) {
		Combatable[] knights = new Combatable[3];
		for (int i = 0; i < knights.length; i++) {
			knights[i] = makeRandomTeam1Member(file_team1, i);
		}
		return knights;
	}

	public static Combatable[] makeTeam2(File file_team2) {
		Combatable[] warriors = new Combatable[3];
		for (int i = 0; i < warriors.length; i++) {
			warriors[i] = makeRandomTeam2Member(file_team2, i);
		}
		return warriors;
	}
}
