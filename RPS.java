import java.util.*;

/**
 *  The classic game of rock, paper, scissors!
 */
public class RPS extends Game{
	private final int requiredWins, maxLosses;
	private int wins, losses;
	private final Random rng;

	/**
	 *
	 * @param rng random number generator for the ai to choose rock, paper, or scissors
	 * @param requiredWins the amount of wins needed to end the series
	 * @param maxLosses the amount of losses needed to end the series
	 */
	public RPS (Random rng, int requiredWins, int maxLosses) {
		this.maxLosses = maxLosses;
		this.requiredWins = requiredWins;
		this.rng = rng;
		this.wins = 0;
		this.losses = 0;
	}

	/**
	 * Resets wins and losses
	 * @return string stating the amount of wins and losses needed to end the series
	 */
	@Override
	protected String prepToPlay() {
		this.wins = 0;
		this.losses = 0;
		return "Enter rock, paper, or scissors. Beat me " + this.requiredWins +" times before I win " + this.maxLosses + " times!";
	}

	/**
	 *
	 * @return over if the amount of wins matches the required wins
	 * or if the amount of losses matches the max amount of losses
	 */
	@Override
	protected boolean isOver() {
		return this.wins == this.requiredWins || this.losses == this.maxLosses;
	}

	/**
	 *
	 * @param move User's move
	 * @return true if move is "rock", "paper", or "scissors"
	 */
	@Override
	protected boolean isValid(String move) {
		if (move.equals("rock") || move.equals("paper") || move.equals("scissors")){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * converts the move to a numerical value to easier code comparisons
	 * @param move User's move
	 * @return the matchup of users move vs. ai move and whether you won, lost, or tied the matchup
	 */
	@Override
	protected String processMove(String move) {
		// 0 = "rock", 1 = "paper", 2 = "scissors"
		int ai = this.rng.nextInt(3);
		int m = -1;
		if (move.equals("rock")){
			m = 0;
		}else if (move.equals("paper")){
			m = 1;
		}else if(move.equals("scissors")){
			m = 2;
		}else{
			m = -1;
		}

		if (ai == m){
			return move + " vs. " + move + " We tie";
		}else if((m == 0 && ai == 2)||(m == 1 && ai == 0)||(m == 2 && ai == 1)){
			this.wins++;
			if (move.equals("rock")){
				return move + " vs. scissors you Win";
			}else if(move.equals("paper")){
				return move + " vs. rock you Win";
			}else{
				return move + " vs. paper you Win";
			}
		}else{
			this.losses++;
			if (move.equals("rock")){
				return move + " vs. paper you lose";
			}else if(move.equals("paper")){
				return move + " vs. scissors you lose";
			}else{
				return move + " vs. rock you lose";
			}

		}
	}

	/**
	 *
	 * @return whether you lost the set or won the set
	 */
	@Override
	protected String finalMessage() {
		if (this.wins == this.requiredWins){
			return "You win the set";
		}else{
			return "You lose the set";
		}
	}

	/**
	 *
	 * @return name of rock paper scissors
	 */
	@Override
	public String getName() {
		return "Rock Paper Scissors";
	}
}
