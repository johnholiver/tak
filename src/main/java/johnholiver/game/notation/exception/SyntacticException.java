package johnholiver.game.notation.exception;

import java.util.List;

import johnholiver.game.notation.ptn.token.AbstractToken;

public class SyntacticException extends Exception {
	public SyntacticException(List<AbstractToken> tokenList, int i, String message) {
		super("Syntactic failure: ["+i+"]["+tokenList+"]. "+ message);
	}

	public SyntacticException(List<AbstractToken> tokenList, int i) {
		this(tokenList, i, "");
	}
}
