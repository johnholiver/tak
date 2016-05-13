package johnholiver.tak.view.console.exception;

import java.util.List;

import johnholiver.tak.view.console.ptn.token.AbstractToken;

@SuppressWarnings("serial")
public class SyntacticException extends Exception {
	public SyntacticException(List<AbstractToken> tokenList, int i, String message) {
		super("Syntactic failure: ["+i+"]["+tokenList+"]. "+ message);
	}

	public SyntacticException(List<AbstractToken> tokenList, int i) {
		this(tokenList, i, "");
	}
}
