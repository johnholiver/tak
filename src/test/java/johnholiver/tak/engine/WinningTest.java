package johnholiver.tak.engine;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.junit.Before;
import org.junit.Test;

import johnholiver.tak.engine.Game;
import johnholiver.tak.engine.command.AbstractCommand;
import johnholiver.tak.engine.command.PlaceCommand;
import johnholiver.tak.engine.exceptions.DrawException;
import johnholiver.tak.engine.piece.PieceType;
import johnholiver.tak.engine.player.Player;
import johnholiver.tak.view.console.ptn.PTNInterface;

public class WinningTest {

	private Game game;
	
	@Before
	public void setUp() throws Exception {
		game = new Game(3);
	}
	
	@Test
	public void playerDoingWrongMoveTest() throws Exception {
		Player p3 = new Player(1,"white", 0, 0);
		Player p4 = new Player(2,"black", 0, 0);
		Game playerDoingWrongMoveGame = new Game(3, p3, p4);
		
		assertEquals(p3, game.getActivePlayer());
		AbstractCommand cmd1 = new PlaceCommand(0, 0, PieceType.FLATSTONE);
		playerDoingWrongMoveGame.doCommand(cmd1);
		assertEquals(p3, game.getActivePlayer());
	}

	@Test
	public void flatOutOfStoneTest() throws Exception {
		Player p3 = new Player(1,"white", 0, 0);
		Player p4 = new Player(2,"black", 0, 1);
		Game drawGame = new Game(3, p3, p4);
		
		AbstractCommand cmd1 = new PlaceCommand(0, 0, PieceType.FLATSTONE);
		drawGame.doCommand(cmd1);
		Player winner = drawGame.checkWinningConditions();
		assertEquals(p4, winner);
	}
	
	@Test
	public void flatOutOfBoardTest() throws Exception {
		Player p3 = new Player(1,"white", 0, 5);
		Player p4 = new Player(2,"black", 0, 5);
		Game outOfBoardGame = new Game(3, p3, p4);
		
		for (int i=0; i<game.getBoard().getSize(); i++)
		{
			for (int j=0; j<game.getBoard().getSize(); j++)
			{
				AbstractCommand cmd = new PlaceCommand(i, j, PieceType.FLATSTONE);
				outOfBoardGame.doCommand(cmd);
			}
		}
		Player winner = outOfBoardGame.checkWinningConditions();
		assertEquals(p3, winner);
	}
	
	@Test (expected = DrawException.class)
	public void flatDrawTest() throws Exception {
		Player p3 = new Player(1,"white", 0, 1);
		Player p4 = new Player(2,"black", 0, 1);
		Game drawGame = new Game(3, p3, p4);
		
		AbstractCommand cmd1 = new PlaceCommand(0, 0, PieceType.FLATSTONE);
		AbstractCommand cmd2 = new PlaceCommand(0, 1, PieceType.FLATSTONE);
		drawGame.doCommand(cmd1);
		drawGame.doCommand(cmd2);
		drawGame.checkWinningConditions();
	}
	
	@Test (expected = DrawException.class)
	public void flatDraw2Test() throws Exception {
		Player p3 = new Player(1,"white", 0, 0);
		Player p4 = new Player(2,"black", 0, 0);
		Game drawGame = new Game(3, p3, p4);
		
		drawGame.checkWinningConditions();
	}
	
	@Test
	public void roadStraightTest() throws Exception {
		AbstractCommand cmd1 = new PlaceCommand(2, 2, PieceType.FLATSTONE);
		AbstractCommand cmd2 = new PlaceCommand(2, 1, PieceType.FLATSTONE);
		game.doCommand(cmd1);
		game.doCommand(cmd2);
		
		List<AbstractCommand> commands = new ArrayList<AbstractCommand>(); 
		commands.add(new PlaceCommand(0, 0, PieceType.FLATSTONE));
		commands.add(new PlaceCommand(2, 0, PieceType.FLATSTONE));
		commands.add(new PlaceCommand(0, 1, PieceType.FLATSTONE));
		commands.add(new PlaceCommand(1, 2, PieceType.FLATSTONE));
		commands.add(new PlaceCommand(0, 2, PieceType.FLATSTONE));
		
		Player winner = null;
		for (AbstractCommand cmd : commands)
		{
			game.doCommand(cmd);		
			winner = game.checkWinningConditions();
			if (winner!=null)
				break;
		}
		assertEquals(7, game.getTurn()-1);
		assertEquals(game.getPlayer1(), winner);
	}
	
	@Test
	public void roadCurve1Test() throws Exception {
		AbstractCommand cmd1 = new PlaceCommand(0, 2, PieceType.FLATSTONE);
		AbstractCommand cmd2 = new PlaceCommand(2, 2, PieceType.FLATSTONE);
		game.doCommand(cmd1);
		game.doCommand(cmd2);
		
		List<AbstractCommand> commands = new ArrayList<AbstractCommand>(); 
		commands.add(new PlaceCommand(0, 0, PieceType.FLATSTONE));
		commands.add(new PlaceCommand(2, 0, PieceType.FLATSTONE));
		commands.add(new PlaceCommand(0, 1, PieceType.FLATSTONE));
		commands.add(new PlaceCommand(1, 0, PieceType.FLATSTONE));
		commands.add(new PlaceCommand(1, 1, PieceType.FLATSTONE));
		commands.add(new PlaceCommand(1, 2, PieceType.FLATSTONE));
		commands.add(new PlaceCommand(2, 1, PieceType.FLATSTONE));
		Player winner = null;
		for (AbstractCommand cmd : commands)
		{
			game.doCommand(cmd);		
			winner = game.checkWinningConditions();
			if (winner!=null)
				break;
		}
		assertEquals(9, game.getTurn()-1);
		assertEquals(game.getPlayer1(), winner);
	}
	
	@Test
	public void roadCurve2Test() throws Exception {
		AbstractCommand cmd1 = new PlaceCommand(0, 2, PieceType.FLATSTONE);
		AbstractCommand cmd2 = new PlaceCommand(2, 2, PieceType.FLATSTONE);
		game.doCommand(cmd1);
		game.doCommand(cmd2);
		
		List<AbstractCommand> commands = new ArrayList<AbstractCommand>(); 
		commands.add(new PlaceCommand(0, 0, PieceType.FLATSTONE));
		commands.add(new PlaceCommand(2, 0, PieceType.FLATSTONE));
		commands.add(new PlaceCommand(0, 1, PieceType.FLATSTONE));
		commands.add(new PlaceCommand(1, 0, PieceType.FLATSTONE));
		commands.add(new PlaceCommand(1, 1, PieceType.FLATSTONE));
		commands.add(new PlaceCommand(2, 1, PieceType.FLATSTONE));
		commands.add(new PlaceCommand(1, 2, PieceType.FLATSTONE));
		Player winner = null;
		for (AbstractCommand cmd : commands)
		{
			game.doCommand(cmd);		
			winner = game.checkWinningConditions();
			if (winner!=null)
				break;
		}
		assertEquals(9, game.getTurn()-1);
		assertEquals(game.getPlayer1(), winner);
	}
	
	@Test
	public void fullGame1Test() throws Exception
	{
		String ptn 	= "1. e5 e1\n"
				   	+ "2. c2 c3\n"
					+ "3. c4 d3\n"
					+ "4. a4 d4\n"
					+ "5. b4 b3\n"
					+ "6. b4- c3<\n"
					+ "7. e4 c3\n"
					+ "8. e4< d3+\n"
					+ "9. c4> c4\n"
					+ "10. 2d4< c3+\n"
					+ "11. 2d4< Cc3\n"
					+ "12. 5c4>23 e3\n"
					+ "13. c5 e3+\n"
					+ "14. 2d4> e5-\n"
					+ "15. Ce3 5e4<212\n"
					+ "16. e3+ c3+\n"
					+ "17. Sc3 a3\n"
					+ "18. 3e4< e4\n"
					+ "19. 4d4> d3\n"
					+ "20. c1 d3+\n"
					+ "21. 5e4< e4\n"
					+ "22. d1 a1\n"
					+ "23. a2 b2\n"
					+ "24. a2- b1\n"
					+ "25. 2a1> Sa1\n"
					+ "26. 3b1+ a1+\n"
					+ "27. 4b2+ a3>\n"
					+ "28. b5 a5\n"
					+ "29. d5 e5\n"
					+ "30. d3 e5<\n"
					+ "31. c5> Sd2\n"
					+ "32. e3 5b3-14\n"
					+ "33. e2\n";
		PTNInterface gameInterface = new PTNInterface();
		Game fullGame1 = new Game(5);
		
		StringTokenizer tokenizer = new StringTokenizer(ptn);
		Player winner = null;
		int turn = 0;
		while (tokenizer.hasMoreTokens())
		{
			String token = tokenizer.nextToken();
			if (!token.endsWith("."))
			{
				turn = fullGame1.getTurn();
				gameInterface.execute(fullGame1, token);
				winner = fullGame1.checkWinningConditions();
			}
		}
		assertEquals(65, turn);
		assertEquals(fullGame1.getPlayer1(), winner);
	}

}
