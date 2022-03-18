
import java.util.*;
public class GameGrabber {
	private final Game[] games;
	private final Scanner inp;

	/**
	 *
	 * @param game Array of games!!
	 * @param user User input
	 */
	public GameGrabber(Game[] game, Scanner user){
		this.games = game;
		this.inp = user;
	}

	/**
	 * Prints the UI to the user and allows them to choose whichever games are presented or quit at the end of the list of games
	 */
	public void doMenu() {
		String menu;
		String pick;
		int num = 0;
		while(num < games.length) {
		menu = "";
			for (int i = 0; i < games.length; i++) {
			menu += i + ") " + games[i].getName() + "\n";
			}
		menu += games.length + ") " + "Quit\n";
		pick = "Pick a game (0-" + games.length + ") ";
		System.out.print(menu + pick);
		num = inp.nextInt();
			if (num >= 0 && num < games.length){
			games[num].play(inp);
			}else if (num == games.length){
				System.out.println("goodbye");
				break;
			}else{
				while(num < 0 || num > games.length) {
					System.out.print(pick);
					num = inp.nextInt();
				}
				if (num < games.length){
					games[num].play(inp);
				}else {
					System.out.println("goodbye");
					break;
				}

			}
		}
	}

	public static void main(String[] args){
		Game[] g = {
				new Hangman(new WordsList(new Random()), 2, 9, 15),
				new NumberGuesser(new Random(), 1000,  10),
				new RPS(new Random(), 5, 5),
				new WordJumble(new WordsList(new Random()), new Random(), 5, 100, 6)

		};
		Scanner scan = new Scanner(System.in);
		GameGrabber gg = new GameGrabber(g, scan);
		gg.doMenu();
	}
}
