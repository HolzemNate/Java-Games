import java.util.*;

/**
 * This game jumbles up a word and the user must guess what the word is
 */
public class WordJumble extends Game{
	private String wrd;
	private final int minWordLen;
	private final int maxWordLen;
	private final int maxGuesses;
	private int guessesLeft;
	private final WordsList wl;
	private Random rng;

	/**
	 *
	 * @param wl list of words
	 * @param rng random number generator for which word is jumbled
	 * @param minWordLen minimum word length for word to be jumbled
	 * @param maxWordLen max word length for word to be jumbled
	 * @param maxGuesses max amount of guesses to guess the word
	 */
	public WordJumble(WordsList wl, Random rng, int minWordLen, int maxWordLen, int maxGuesses){
		this.guessesLeft = maxGuesses;
		this.wl = wl;
		this.maxWordLen = maxWordLen;
		this.minWordLen = minWordLen;
		this.rng = rng;
		this.maxGuesses = maxGuesses;
	}

	/**
	 * changes the jumbled word and resets the guesses
	 * @return string stating the jumbled word and how many guesses given
	 */
	@Override
	protected String prepToPlay() {
		this.guessesLeft = this.maxGuesses;
		this.wrd = wl.getWord(minWordLen, maxWordLen);
		char[] c = wrd.toCharArray();
		ArrayList<String> al = new ArrayList<String>();
		for (int i = 0; i < c.length; i++){
			al.add(Character.toString(c[i]));
		}
		String jumbled = "";
		//loop to jumble the letters
		while (al.size() > 0){
			int ind = new Random().nextInt(al.size());
			String let = al.get(ind);
			jumbled += let;
			al.remove(ind);
		}
		return "The following is a jumbled up word: " + jumbled + " You get " + this.guessesLeft + " guesses to guess it";
	}

	/**
	 *
	 * @return true if guessesleft hits 0 (guessing the word correctly sets it to 0)
	 */
	@Override
	protected boolean isOver() {
		return this.guessesLeft == 0;
	}

	/**
	 *
	 * @param move User's move
	 * @return always return true as any input is valid
	 */
	@Override
	protected boolean isValid(String move) {
		return true;
	}

	/**
	 *
	 * @param move User's move
	 * @return checks to see if the move equals the word if so
	 * returns nothing and ends the game otherwise says that's not it
	 */
	@Override
	protected String processMove(String move) {
		if(move.equals(this.wrd)){
			this.guessesLeft = 0;
			return null;
		}
		this.guessesLeft--;
		return "That's not it";
	}

	/**
	 *
	 * @return returns what word the jumbled word is
	 */
	@Override
	protected String finalMessage() {
		return "The word was " + wrd;
	}

	/**
	 *
	 * @return "Word jumble"
	 */
	@Override
	public String getName() {
		return "Word jumble";
	}
}
