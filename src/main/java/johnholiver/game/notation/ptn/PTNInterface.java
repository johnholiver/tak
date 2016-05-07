package johnholiver.game.notation.ptn;

import java.util.List;
import java.util.StringTokenizer;

import johnholiver.game.Game;
import johnholiver.game.command.Command;
import johnholiver.game.notation.ptn.token.Token;

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
			List<Token> tokenList = lexical.parse(tokenizer.nextToken());
			List<Command> commands = syntactic.analyze(tokenList);
			for (Command cmd : commands)
			{
				activeGame.doCommand(cmd);
			}
		}
	}

}
