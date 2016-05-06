package johnholiver.game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import johnholiver.game.exceptions.OutOfStoneException;
import johnholiver.game.piece.Capstone;
import johnholiver.game.piece.FlatStone;

public class PlayerTest {

	private Player p;
	
	@Before
	public void setUp() throws Exception {
		p = new Player(1,"white",1,1);
	}
	
	@Test (expected = OutOfStoneException.class)
	public void stoneTest() throws OutOfStoneException {
		FlatStone piece1 = new FlatStone(p);
		FlatStone piece2 = new FlatStone(p);
	}

	@Test (expected = OutOfStoneException.class)
	public void capstoneTest() throws OutOfStoneException {
		Capstone piece1 = new Capstone(p);
		Capstone piece2 = new Capstone(p);
	}
}
