package johnholiver.game.notation.ptn;

import java.util.List;
import java.util.StringTokenizer;

import johnholiver.game.Game;
import johnholiver.game.command.AbstractCommand;
import johnholiver.game.notation.ptn.token.AbstractToken;

public class PTNInterface {
	PTNLexicalParser lexical;
	PTNSyntacticAnalyser syntactic;
	
	public PTNInterface()
	{
		lexical = new PTNLexicalParser();
		syntactic = new PTNSyntacticAnalyser();
	}

	public void execute(Game activeGame, String input) throws Exception {
		StringTokenizer tokenizer = new StringTokenizer(input);
		while(tokenizer.hasMoreElements())
		{
			List<AbstractToken> tokenList = lexical.parse(tokenizer.nextToken());
			List<AbstractCommand> commands = syntactic.analyze(tokenList);
			for (AbstractCommand cmd : commands)
			{
				activeGame.doCommand(cmd);
			}
		}
	}

}
