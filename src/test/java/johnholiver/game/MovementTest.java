package johnholiver.game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import johnholiver.game.move.exceptions.MoveException;
import johnholiver.game.move.exceptions.PlaceException;

public class MovementTest {

	private Player p1;
	private Player p2;
	private Board board;
	
	@Before
	public void setUp() throws Exception {
		board = new Board(3);
		p1 = new Player(1,"white", 1, 10);
		p2 = new Player(2,"black", 1, 10);
	}

	@Test 
	public void simplePlaceTest() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test (expected = PlaceException.class)
	public void placeOccupiedTest() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test (expected = PlaceException.class)
	public void placeOutOfBoundsTest() {
		fail("Not yet implemented"); // TODO
	}

	@Test 
	public void moveSimpleTest() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test 
	public void moveCapstoneOverStandingTest() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test 
	public void moveStackDropOneTest() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test 
	public void moveStackDropRandomTest() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test (expected = MoveException.class)
	public void moveStackNonCaptstoneOverStandingTest() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test (expected = MoveException.class)
	public void moveOutOfBoundsTest() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test (expected = MoveException.class)
	public void moveSkipDropTest() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test (expected = MoveException.class)
	public void moveCarryLimitTest() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test (expected = MoveException.class)
	public void moveInsurmountablePieceFlatToStandingTest() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test (expected = MoveException.class)
	public void moveInsurmountablePieceFlatToCapTest() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test (expected = MoveException.class)
	public void moveInsurmountablePieceCapToStandingTest() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test (expected = MoveException.class)
	public void moveInsurmountablePieceCapToCapTest() {
		fail("Not yet implemented"); // TODO
	}

}
