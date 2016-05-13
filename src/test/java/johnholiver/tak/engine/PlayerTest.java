package johnholiver.tak.engine;

import org.junit.Before;
import org.junit.Test;

import johnholiver.tak.engine.piece.PieceType;
import johnholiver.tak.engine.player.Player;
import johnholiver.tak.engine.player.exceptions.OutOfStoneException;

public class PlayerTest {

	private Player p;
	
	@Before
	public void setUp() throws Exception {
		p = new Player(1,"white",1,1);
	}
	
	@Test (expected = OutOfStoneException.class)
	public void stoneTest() throws OutOfStoneException {
		p.popStone(PieceType.FLATSTONE);
		p.popStone(PieceType.STANDINGSTONE);
	}

	@Test (expected = OutOfStoneException.class)
	public void capstoneTest() throws OutOfStoneException {
		p.popStone(PieceType.CAPSTONE);
		p.popStone(PieceType.CAPSTONE);
	}
}
