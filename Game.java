import java.util.*;
import java.lang.*;

public abstract class Game {

	/**
	 *
	 * @return String of the starting sentence for whichever game is chosen
	 */
	protected abstract String prepToPlay();


	/**
	 *
	 * @return checks if the game is over
	 */
	protected abstract boolean isOver();


	/**
	 *
	 * @param move User's move
	 * @return checks if the user's move is a valid move
	 */
	protected abstract boolean isValid(String move);


	/**
	 *
	 * @param move User's move
	 * @return updated state of the game based on the user's input
	 */
	protected abstract String processMove(String move);


	/**
	 *
	 * @return String of last sentence of the game chosen
	 */
	protected abstract String finalMessage();


	/**
	 *
	 * @return name of game
	 */
	public abstract String getName();


	/**
	 * allow the user to input a move for the game checks if its valid and/or if the game is over
	 * processes the move if its valid and the game is not over and repeats until one of those states
	 * change then prints the final message of the game
	 * @param user user input
	 */
	public void play(Scanner user){
		System.out.println(this.prepToPlay());
		String inp = "";
		while(!this.isOver()) {
			System.out.print("Enter Your Move or 'quit' to quit> ");
			inp = user.next();
			if (inp.equals("quit")) {
				break;
			}
			while (!this.isValid(inp)) {
				System.out.print("Invalid Move! try again> ");
				inp = user.next();
			}
			String g = this.processMove(inp);
				if (g == null) {

				} else {
					System.out.println(g);
				}

		}
		System.out.println(this.finalMessage());
	}
}
