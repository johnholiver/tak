package johnholiver.tak.view.console.ptn;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import johnholiver.tak.engine.command.AbstractCommand;
import johnholiver.tak.engine.command.InfoCommand;
import johnholiver.tak.engine.command.MoveCommand;
import johnholiver.tak.engine.command.PlaceCommand;
import johnholiver.tak.engine.move.Direction;
import johnholiver.tak.engine.piece.PieceType;
import johnholiver.tak.view.console.exception.SyntacticException;
import johnholiver.tak.view.console.ptn.PTNSyntacticAnalyser;
import johnholiver.tak.view.console.ptn.token.AbstractToken;
import johnholiver.tak.view.console.ptn.token.CommentToken;
import johnholiver.tak.view.console.ptn.token.CountToken;
import johnholiver.tak.view.console.ptn.token.DirectionToken;
import johnholiver.tak.view.console.ptn.token.MarkToken;
import johnholiver.tak.view.console.ptn.token.SquareToken;
import johnholiver.tak.view.console.ptn.token.StoneToken;

public class PTNSyntacticalTest {

	List<AbstractToken> tokenList;
	List<AbstractCommand> cmds;
	private PTNSyntacticAnalyser syntactic;
	
	@Before
	public void setUp()
	{
		tokenList = new ArrayList<AbstractToken>();
		syntactic = new PTNSyntacticAnalyser();
	}

	@Test
	public void incompleteFlatPlace() throws SyntacticException {
		tokenList.add(new SquareToken("a1"));
		cmds = syntactic.analyze(tokenList);
		assertEquals(new PlaceCommand(0, 0, PieceType.FLATSTONE), cmds.get(0));
	}
	
	@Test
	public void fullFlatPlace() throws SyntacticException {
		tokenList.add(new StoneToken('F'));
		tokenList.add(new SquareToken("a1"));
		cmds = syntactic.analyze(tokenList);
		assertEquals(new PlaceCommand(0, 0, PieceType.FLATSTONE), cmds.get(0));
	}

	@Test
	public void fullCapPlace() throws SyntacticException {
		tokenList.add(new StoneToken('C'));
		tokenList.add(new SquareToken("a1"));
		cmds = syntactic.analyze(tokenList);
		assertEquals(new PlaceCommand(0, 0, PieceType.CAPSTONE), cmds.get(0));
	}
	
	@Test
	public void incompleteMove() throws SyntacticException {
		tokenList.add(new SquareToken("a1"));
		tokenList.add(new DirectionToken('>'));
		cmds = syntactic.analyze(tokenList);
		List<Integer> drop = new ArrayList<Integer>();
		drop.add(0);
		drop.add(1);
		assertEquals(new MoveCommand(0, 0, Direction.RIGHT, drop), cmds.get(0));
	}
	
	@Test
	public void fullMove() throws SyntacticException {
		tokenList.add(new CountToken('4'));
		tokenList.add(new SquareToken("a1"));
		tokenList.add(new DirectionToken('>'));
		tokenList.add(new CountToken('2'));
		tokenList.add(new CountToken('1'));
		tokenList.add(new CountToken('1'));
		tokenList.add(new StoneToken('S'));
		cmds = syntactic.analyze(tokenList);
		List<Integer> drop = new ArrayList<Integer>();
		drop.add(2);
		drop.add(1);
		drop.add(1);
		assertEquals(new MoveCommand(0, 0, Direction.RIGHT, drop), cmds.get(0));
		
	}
	
	@Test
	public void tak() throws SyntacticException {
		tokenList.add(new SquareToken("a1"));
		tokenList.add(new MarkToken('\''));
		cmds = syntactic.analyze(tokenList);
		assertEquals(new PlaceCommand(0, 0, PieceType.FLATSTONE), cmds.get(0));
		assertEquals(new InfoCommand("'"), cmds.get(1));
	}
	
	@Test
	public void marks() throws SyntacticException {
		tokenList.add(new SquareToken("a1"));
		tokenList.add(new MarkToken('\''));
		tokenList.add(new MarkToken('!'));
		tokenList.add(new MarkToken('?'));
		tokenList.add(new CommentToken("{wow}"));
		cmds = syntactic.analyze(tokenList);
		assertEquals(new PlaceCommand(0, 0, PieceType.FLATSTONE), cmds.get(0));
		assertEquals(new InfoCommand("'"), cmds.get(1));
		assertEquals(new InfoCommand("!"), cmds.get(2));
		assertEquals(new InfoCommand("?"), cmds.get(3));
		assertEquals(new InfoCommand("{wow}"), cmds.get(4));
	}
	
	@Test (expected = SyntacticException.class)
	public void marks1Exception() throws SyntacticException {
		tokenList.add(new SquareToken("a1"));
		tokenList.add(new CommentToken("{wow}"));
		tokenList.add(new MarkToken('\''));
		syntactic.analyze(tokenList);
	}
	
	@Test (expected = SyntacticException.class)
	public void marks2Exception() throws SyntacticException {
		tokenList.add(new SquareToken("a1"));
		tokenList.add(new CommentToken("!"));
		tokenList.add(new MarkToken('\''));
		syntactic.analyze(tokenList);
	}

}
