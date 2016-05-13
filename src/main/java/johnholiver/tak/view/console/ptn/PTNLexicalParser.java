package johnholiver.tak.view.console.ptn;

import java.util.ArrayList;
import java.util.List;

import johnholiver.tak.view.console.exception.LexicalException;
import johnholiver.tak.view.console.ptn.token.AbstractToken;
import johnholiver.tak.view.console.ptn.token.CommentToken;
import johnholiver.tak.view.console.ptn.token.CountToken;
import johnholiver.tak.view.console.ptn.token.DirectionToken;
import johnholiver.tak.view.console.ptn.token.MarkToken;
import johnholiver.tak.view.console.ptn.token.SquareToken;
import johnholiver.tak.view.console.ptn.token.StoneToken;

public class PTNLexicalParser {
	private String input;
	private int i;

	public List<AbstractToken> parse(String input) throws LexicalException {
		this.input = input;
		i = 0;
		return initialState();
	}

	private char consumeChar() {
		char c = input.charAt(i);
		i++;
		return c;
	}

	private List<AbstractToken> initialState() throws LexicalException {
		List<AbstractToken> tokenList = new ArrayList<AbstractToken>();
		for (;i < this.input.length();)
		{
			char c = consumeChar();
			switch (c) {
			case 'F':
			case 'C':
			case 'S':
				tokenList.add(new StoneToken(c));
				break;
			case 'a':
			case 'b':
			case 'c':
			case 'd':
			case 'e':
			case 'f':
			case 'g':
			case 'h':
				tokenList.add(getSquareToken(c));
				break;
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
				tokenList.add(new CountToken(c));
				break;
			case '>':
			case '<':
			case '+':
			case '-':
				tokenList.add(new DirectionToken(c));
				break;
			case '\'':
			case '!':
			case '?':
				tokenList.add(new MarkToken(c));
				break;
			case '{':
				tokenList.add(getCommentToken());
				break;
			default:
				throw new LexicalException(this.input, this.i);
			}
		}
		return tokenList;
	}

	private AbstractToken getSquareToken(char x) throws LexicalException {
		char y = consumeChar();
		switch (y) {
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
			return new SquareToken(String.valueOf(x)+String.valueOf(y));
		default:
			throw new LexicalException(this.input, this.i);
		}
	}

	private AbstractToken getCommentToken() throws LexicalException {
		String comment="{";
		for( ; i < input.length(); ) 
		{
			char c = consumeChar();
			comment+=c;
			switch(c) {
			case '}':
				return new CommentToken(comment);
			}
        }
		throw new LexicalException(this.input, this.i, "Couldn't find '}' to close the comment.");
	}	
}
