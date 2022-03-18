public class Hangman extends Game{
	private String wrd;
	private int guessesLeft;
	private String hiddenWord = "";
	private final int maxGuesses;
	private final int minWordLen;
	private final int maxWordLen;
	private final WordsList wl;

	/**
	 *
	 * @param words list of 600 most common English words
	 * @param minWordLen minimum word length
	 * @param maxWordLen maximum word length
	 * @param maxGuesses starting number of guesses
	 */
	public Hangman(WordsList words, int minWordLen, int maxWordLen, int maxGuesses) {
		this.guessesLeft = maxGuesses;
		this.maxGuesses= maxGuesses;
		this.minWordLen = minWordLen;
		this.maxWordLen = maxWordLen;
		this.wl = words;
	}

	/**
	 *
	 * @return String saying how long wrd is and number of guesses given
	 */
	@Override
	protected String prepToPlay() {
		this.guessesLeft = this.maxGuesses;
		this.wrd = wl.getWord(minWordLen, maxWordLen);
		this.hiddenWord = "";
		for (int i = 0; i < wrd.length(); i++) {
			this.hiddenWord += "_";
		}
		return "I've picked a " + wrd.length() +
				" letter word. Guess letters you think are in the word. You get "
				+ this.guessesLeft + " guesses.";
	}

	/**
	 *
	 * @return true when hiddenWord equals the word or when the user runs out of guesses
	 */
	@Override
	protected boolean isOver() {
		return hiddenWord.equals(wrd) || this.guessesLeft == 0;
	}

	/**
	 *
	 * @param move user input
	 * @return true if the string is one char long as a String
	 */
	@Override
	protected boolean isValid(String move) {
		if (move.length() < 2) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 *
	 * @param move user input
	 * @return updated version of hidden word showing the letters that user guessed if in wrd
	 */
	@Override
	protected String processMove(String move) {
		char[] hidden = hiddenWord.toCharArray();
		this.hiddenWord = "";
		for (int i = 0; i < wrd.length(); i++){
			if(move.equals((String.valueOf(wrd.charAt(i))))) {
				hidden[i] = wrd.charAt(i);
			}
		}
		for (int i = 0; i < wrd.length(); i++){
			this.hiddenWord += hidden[i];
		}
		this.guessesLeft--;
		return this.hiddenWord;
	}

	/**
	 *
	 * @return String telling user what wrd was
	 */
	@Override
	protected String finalMessage() {
		return "The word was: " + this.wrd;
	}

	/**
	 *
	 * @return name of game
	 */
	@Override
	public String getName() {
		return "Hangman";
	}
}
