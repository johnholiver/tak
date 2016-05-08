package johnholiver.game.notation.ptn;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import johnholiver.game.notation.exception.LexicalException;
import johnholiver.game.notation.ptn.token.AbstractToken;
import johnholiver.game.notation.ptn.token.CommentToken;
import johnholiver.game.notation.ptn.token.CountToken;
import johnholiver.game.notation.ptn.token.DirectionToken;
import johnholiver.game.notation.ptn.token.MarkToken;
import johnholiver.game.notation.ptn.token.SquareToken;
import johnholiver.game.notation.ptn.token.StoneToken;

public class PTNLexicalTest {
	
	private PTNLexicalParser lexical;
	
	@Before
	public void setUp()
	{
		lexical = new PTNLexicalParser();
	}
	
	@Test
	public void incompleteFlatPlace() throws LexicalException {
		List<AbstractToken> tokenList = lexical.parse("a1");
		SquareToken square = new SquareToken("a1");
		assertEquals(square, tokenList.get(0));
	}
	
	@Test
	public void fullFlatPlace() throws LexicalException {
		List<AbstractToken> tokenList = lexical.parse("Fa1");
		StoneToken stone = new StoneToken('F');
		SquareToken square = new SquareToken("a1");
		assertEquals(stone, tokenList.get(0));
		assertEquals(square, tokenList.get(1));
	}

	@Test
	public void fullCapPlace() throws LexicalException {
		List<AbstractToken> tokenList = lexical.parse("Ca1");
		StoneToken stone = new StoneToken('C');
		SquareToken square = new SquareToken("a1");
		assertEquals(stone, tokenList.get(0));
		assertEquals(square, tokenList.get(1));
	}
	
	@Test
	public void incompleteMove() throws LexicalException {
		List<AbstractToken> tokenList = lexical.parse("a1>");
		SquareToken square = new SquareToken("a1");
		DirectionToken direction = new DirectionToken('>');
		assertEquals(square, tokenList.get(0));
		assertEquals(direction, tokenList.get(1));
	}
	
	@Test
	public void fullMove() throws LexicalException {
		List<AbstractToken> tokenList = lexical.parse("4a1>211S");
		CountToken count = new CountToken('4');
		SquareToken square = new SquareToken("a1");
		DirectionToken direction = new DirectionToken('>');
		CountToken drop1 = new CountToken('2');
		CountToken drop2 = new CountToken('1');
		CountToken drop3 = new CountToken('1');
		StoneToken stone = new StoneToken('S');
		assertEquals(count, tokenList.get(0));
		assertEquals(square, tokenList.get(1));
		assertEquals(direction, tokenList.get(2));
		assertEquals(drop1, tokenList.get(3));
		assertEquals(drop2, tokenList.get(4));
		assertEquals(drop3, tokenList.get(5));
		assertEquals(stone, tokenList.get(6));
	}
	
	@Test
	public void tak() throws LexicalException {
		List<AbstractToken> tokenList = lexical.parse("a1'");
		SquareToken square = new SquareToken("a1");
		MarkToken mark = new MarkToken('\'');
		assertEquals(square, tokenList.get(0));
		assertEquals(mark, tokenList.get(1));
	}
	
	@Test
	public void marks() throws LexicalException {
		List<AbstractToken> tokenList = lexical.parse("a1'!?{wow}");
		SquareToken square = new SquareToken("a1");
		MarkToken mark1 = new MarkToken('\'');
		MarkToken mark2 = new MarkToken('!');
		MarkToken mark3 = new MarkToken('?');
		CommentToken comment = new CommentToken("{wow}");
		assertEquals(square, tokenList.get(0));
		assertEquals(mark1, tokenList.get(1));
		assertEquals(mark2, tokenList.get(2));
		assertEquals(mark3, tokenList.get(3));
		assertEquals(comment, tokenList.get(4));
	}
	
	@Test (expected = LexicalException.class)
	public void marksException() throws LexicalException {
		lexical.parse("a1'!?(wow)");
	}
}
