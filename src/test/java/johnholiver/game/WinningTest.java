package johnholiver.game;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import johnholiver.game.command.AbstractCommand;
import johnholiver.game.command.PlaceCommand;
import johnholiver.game.exceptions.DrawException;
import johnholiver.game.piece.PieceType;

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

}
