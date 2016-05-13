package johnholiver.tak.view.console.ptn;

import java.util.List;
import java.util.StringTokenizer;

import johnholiver.tak.engine.Game;
import johnholiver.tak.engine.command.AbstractCommand;
import johnholiver.tak.view.console.ptn.token.AbstractToken;

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
