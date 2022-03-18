import java.util.*;

/**
 *  A Game where the user tries to guess a number from a specified range and has a certain amount of guesses to guess the number
 */
public class NumberGuesser extends Game{
	private int number;
	private int guessesLeft;
	private final int maxNumber;
	private final Random rng;
	private final int maxGuesses;

	/**
	 *
	 * @param rng random number generator to generate the number
	 * @param maxNumber max limit on the range (1 to this number)
	 * @param maxGuesses max guesses allowed for the user to guess the number
	 */
	public NumberGuesser(Random rng, int maxNumber, int maxGuesses){
		this.guessesLeft = maxGuesses;
		this.maxNumber = maxNumber;
		this.rng = rng;
		this.maxGuesses = maxGuesses;
	}

	/**
	 *  resets the number to guess and the guesses
	 * @return returns a string stating the range of numbers and the amount of guesses given
	 */
	@Override
	protected String prepToPlay() {
		this.number = rng.nextInt(maxNumber)+1;
		this.guessesLeft = this.maxGuesses;
		return "I've picked a number 1 to "+ this.maxNumber+ ". You get " + this.guessesLeft+ " guesses to guess it";

	}


	/**
	 *
	 * @return true if guesses hits 0 (guessing the correct number, sets guesses to zero to end the game)
	 */
	@Override
	protected boolean isOver() {
		return this.guessesLeft == 0;
	}

	/**
	 *
	 * @param move User's move
	 * @return true if the move is a digit value (number) otherwise false
	 */
	@Override
	protected boolean isValid(String move) {
		char[] m = move.toCharArray();
		boolean flag = false;
		for (char c : m) {
			if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}

		return flag;
	}


	/**
	 *
	 * @param move User's move
	 * @return string saying if the user was too high or too low or correct
	 */
	@Override
	protected String processMove(String move) {
		this.guessesLeft--;
		if((Integer.parseInt(move) > this.number)){
			return "Too High";
		}else if((Integer.parseInt(move) < this.number)){
			return "Too Low";
		}else{
			this.guessesLeft = 0;
			return "That's it!";

		}

	}

	/**
	 *
	 * @return a string showing what the number was
	 */
	@Override
	protected String finalMessage() {
		return "The number was: " + this.number;
	}

	/**
	 *
	 * @return name of game
	 */
	@Override
	public String getName() {
		return "NumberGuesser";
	}
}
