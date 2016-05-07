package johnholiver.game.notation.exception;

public class LexicalException extends Exception {

	public LexicalException(String input, int i, String message) {
		super("Lexical failure: ["+i+"]["+input+"]. "+ message);
	}

	public LexicalException(String input, int i) {
		this(input, i, "");
	}

}
