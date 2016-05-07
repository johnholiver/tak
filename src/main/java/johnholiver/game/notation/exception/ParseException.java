package johnholiver.game.notation.exception;

public class ParseException extends Exception {

	public ParseException(String input, int i, String message) {
		super("Failed to parse: ["+i+"]["+input+"]. "+ message);
	}

	public ParseException(String input, int i) {
		this(input, i, "");
	}

}
