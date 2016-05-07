package johnholiver.game.notation.ptn;

import java.util.List;

import johnholiver.game.Game;
import johnholiver.game.notation.ptn.token.Token;

public class PTNInterface {
	PTNLexicalParser lexical;
	PTNSyntacticAnalyser syntactic;
	
	public PTNInterface()
	{
		lexical = new PTNLexicalParser();
	}

	public void execute(Game activeGame, String input) throws Exception {
		List<Token> tokenList = lexical.parse(input);
		syntactic.analyze(tokenList);
	}

}
