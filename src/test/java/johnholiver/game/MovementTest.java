package johnholiver.game;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import johnholiver.game.exceptions.OutOfStoneException;
import johnholiver.game.move.Move;
import johnholiver.game.move.Move.Direction;
import johnholiver.game.move.Place;
import johnholiver.game.move.exceptions.MoveException;
import johnholiver.game.move.exceptions.PlaceException;
import johnholiver.game.piece.AbstractPiece;
import johnholiver.game.piece.Capstone;
import johnholiver.game.piece.FlatStone;
import johnholiver.game.piece.StandingStone;

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
	public void simplePlaceTest() throws Exception {
		AbstractPiece piece = new Capstone(p1);
		Place move = new Place(board, piece, 0, 0);
		board.executeMove(move);
		assertSame(piece, board.getSquare(0, 0).get(0));
	}

	@Test 
	public void stackTest() throws Exception {
		AbstractPiece piece1 = new FlatStone(p1);
		AbstractPiece piece2 = new FlatStone(p2);
		Place move1 = new Place(board, piece1, 0, 0);
		Place move2 = new Place(board, piece2, 0, 1);
		List<Integer> drop = new ArrayList<Integer>();
		drop.add(0);
		drop.add(1);
		Move move3 = new Move(board, piece2, 0, 1, Direction.DOWN, drop);
		board.executeMove(move1);
		board.executeMove(move2);
		board.executeMove(move3);
		List<AbstractPiece> stack = new ArrayList<AbstractPiece>();
		stack.add(piece1);
		stack.add(piece2);
		for (int i = 0; i < stack.size(); i++)
			assertSame(stack.get(i), board.getSquare(0, 0).get(i));
	}

	@Test (expected = PlaceException.class)
	public void placeOccupiedTest() throws Exception {
		Place move1 = new Place(board, new Capstone(p1), 0, 0);
		Place move2 = new Place(board, new Capstone(p2), 0, 0);
		board.executeMove(move1);
		board.executeMove(move2);
	}
	
	@Test (expected = PlaceException.class)
	public void placeOutOfBoundsTest() throws Exception {
		Place move = new Place(board, new Capstone(p1), board.getSize(), board.getSize());
		board.executeMove(move);
	}

	@Test 
	public void moveSimpleTest() throws Exception {
		AbstractPiece piece1 = new FlatStone(p1);
		Place move1 = new Place(board, piece1, 0, 0);
		List<Integer> drop = new ArrayList<Integer>();
		drop.add(0);
		drop.add(1);
		Move move2 = new Move(board, piece1, 0, 0, Direction.UP, drop);
		board.executeMove(move1);
		board.executeMove(move2);
		List<AbstractPiece> stack = new ArrayList<AbstractPiece>();
		stack.add(piece1);
		assertTrue(board.getSquare(0, 0).isEmpty());
		for (int i = 0; i < stack.size(); i++)
			assertSame(stack.get(i), board.getSquare(0, 1).get(i));
	}
	
	@Test 
	public void moveStackDropOneTest() throws Exception {
		AbstractPiece piece1 = new FlatStone(p1);
		AbstractPiece piece2 = new FlatStone(p1);
		AbstractPiece piece3 = new FlatStone(p1);
		Place move1 = new Place(board, piece1, 0, 0);
		Place move2 = new Place(board, piece2, 1, 0);
		Place move3 = new Place(board, piece3, 2, 0);
		board.executeMove(move1);
		board.executeMove(move2);
		board.executeMove(move3);

		List<Integer> drop = new ArrayList<Integer>();
		drop.add(0);
		drop.add(1);
		Move move4 = new Move(board, piece1, 0, 0, Direction.RIGHT, drop);
		board.executeMove(move4);
		drop.clear();
		drop.add(1);
		drop.add(1);
		Move move5 = new Move(board, piece1, 1, 0, Direction.RIGHT, drop);
		board.executeMove(move5);
		drop.clear();
		drop.add(0);
		drop.add(1);
		Move move6 = new Move(board, piece2, 1, 0, Direction.RIGHT, drop);
		board.executeMove(move6);
		drop.clear();
		drop.add(1);
		drop.add(1);
		drop.add(1);
		Move move7 = new Move(board, piece2, 2, 0, Direction.UP, drop);
		board.executeMove(move7);
		assertSame(piece2, board.getSquare(2, 2).get(0));
		assertSame(piece1, board.getSquare(2, 1).get(0));
		assertSame(piece3, board.getSquare(2, 0).get(0));
	}
	
	@Test 
	public void moveStackDropRandomTest() throws Exception {
		AbstractPiece piece1 = new FlatStone(p1);
		AbstractPiece piece2 = new FlatStone(p1);
		AbstractPiece piece3 = new FlatStone(p1);
		Place move1 = new Place(board, piece1, 0, 0);
		Place move2 = new Place(board, piece2, 1, 0);
		Place move3 = new Place(board, piece3, 2, 0);
		board.executeMove(move1);
		board.executeMove(move2);
		board.executeMove(move3);

		List<Integer> drop = new ArrayList<Integer>();
		drop.add(0);
		drop.add(1);
		Move move4 = new Move(board, piece1, 0, 0, Direction.RIGHT, drop);
		board.executeMove(move4);
		drop.clear();
		drop.add(1);
		drop.add(1);
		Move move5 = new Move(board, piece1, 1, 0, Direction.RIGHT, drop);
		board.executeMove(move5);
		drop.clear();
		drop.add(0);
		drop.add(1);
		Move move6 = new Move(board, piece2, 1, 0, Direction.RIGHT, drop);
		board.executeMove(move6);
		drop.clear();
		drop.add(0);
		drop.add(2);
		drop.add(1);
		Move move7 = new Move(board, piece2, 2, 0, Direction.UP, drop);
		board.executeMove(move7);
		assertSame(piece3, board.getSquare(2, 1).get(0));
		assertSame(piece1, board.getSquare(2, 1).get(1));
		assertSame(piece2, board.getSquare(2, 2).get(0));
	}
	
	@Test (expected = MoveException.class)
	public void moveStackNonCaptstoneOverStandingTest() throws Exception {
		AbstractPiece piece1 = new Capstone(p1);
		AbstractPiece piece2 = new FlatStone(p1);
		AbstractPiece piece3 = new StandingStone(p1);
		Place move1 = new Place(board, piece1, 0, 0);
		Place move2 = new Place(board, piece2, 1, 0);
		Place move3 = new Place(board, piece3, 2, 0);
		board.executeMove(move1);
		board.executeMove(move2);
		board.executeMove(move3);

		List<Integer> drop = new ArrayList<Integer>();
		drop.add(0);
		drop.add(1);
		Move move4 = new Move(board, piece1, 0, 0, Direction.RIGHT, drop);
		board.executeMove(move4);
		drop.clear();
		drop.add(0);
		drop.add(2);
		Move move5 = new Move(board, piece1, 1, 0, Direction.RIGHT, drop);
		board.executeMove(move5);
	}
	
	@Test (expected = MoveException.class)
	public void moveOutOfBoundsTest() throws Exception {
		AbstractPiece piece1 = new StandingStone(p1);
		Place move1 = new Place(board, piece1, 0, 0);
		List<Integer> drop = new ArrayList<Integer>();
		drop.add(0);
		drop.add(1);
		Move move2 = new Move(board, piece1, 0, 0, Direction.DOWN, drop);
		board.executeMove(move1);
		board.executeMove(move2);
	}
	
	@Test (expected = MoveException.class)
	public void moveSkipDropTest() throws Exception {
		AbstractPiece piece1 = new StandingStone(p1);
		AbstractPiece piece2 = new FlatStone(p2);
		Place move1 = new Place(board, piece1, 0, 0);
		Place move2 = new Place(board, piece2, 0, 2);
		List<Integer> drop = new ArrayList<Integer>();
		drop.add(0);
		drop.add(0);
		drop.add(1);
		Move move3 = new Move(board, piece2, 0, 2, Direction.DOWN, drop);
		board.executeMove(move1);
		board.executeMove(move2);
		board.executeMove(move3);
	}
	
	@Test (expected = MoveException.class)
	public void moveCarryLimitTest() throws Exception {
		AbstractPiece piece1 = new FlatStone(p1);
		Place move1 = new Place(board, piece1, 0, 0);
		board.executeMove(move1);

		AbstractPiece piece2 = null;
		for (int i = 0; i < board.getSize(); i++)
		{
			//Add
			piece2 = new FlatStone(p1);
			Place move2 = new Place(board, piece2, 1, 0);
			board.executeMove(move2);
			//Move
			List<Integer> drop = new ArrayList<Integer>();
			drop.add(0);
			drop.add(1);
			Move move3 = new Move(board, piece2, 1, 0, Direction.LEFT, drop);
			board.executeMove(move3);
		}

		//Carry Limit
		List<Integer> drop = new ArrayList<Integer>();
		drop.add(0);
		drop.add(4);
		if (piece2!=null)
		{
			Move move4 = new Move(board, piece2, 0, 0, Direction.RIGHT, drop);
			board.executeMove(move4);
		}
	}
	
	@Test (expected = MoveException.class)
	public void moveInsurmountablePieceFlatToStandingTest() throws Exception {
		AbstractPiece piece1 = new StandingStone(p1);
		AbstractPiece piece2 = new FlatStone(p2);
		Place move1 = new Place(board, piece1, 0, 0);
		Place move2 = new Place(board, piece2, 0, 1);
		List<Integer> drop = new ArrayList<Integer>();
		drop.add(0);
		drop.add(1);
		Move move3 = new Move(board, piece2, 0, 1, Direction.DOWN, drop);
		board.executeMove(move1);
		board.executeMove(move2);
		board.executeMove(move3);
	}
	
	@Test (expected = MoveException.class)
	public void moveInsurmountablePieceFlatToCapTest() throws Exception {
		AbstractPiece piece1 = new Capstone(p1);
		AbstractPiece piece2 = new FlatStone(p2);
		Place move1 = new Place(board, piece1, 0, 0);
		Place move2 = new Place(board, piece2, 0, 1);
		List<Integer> drop = new ArrayList<Integer>();
		drop.add(0);
		drop.add(1);
		Move move3 = new Move(board, piece2, 0, 1, Direction.DOWN, drop);
		board.executeMove(move1);
		board.executeMove(move2);
		board.executeMove(move3);
	}
	
	@Test
	public void moveInsurmountablePieceCapToStandingTest() throws Exception {
		AbstractPiece piece1 = new StandingStone(p1);
		AbstractPiece piece2 = new Capstone(p2);
		Place move1 = new Place(board, piece1, 0, 0);
		Place move2 = new Place(board, piece2, 0, 1);
		List<Integer> drop = new ArrayList<Integer>();
		drop.add(0);
		drop.add(1);
		Move move3 = new Move(board, piece2, 0, 1, Direction.DOWN, drop);
		board.executeMove(move1);
		board.executeMove(move2);
		board.executeMove(move3);
		List<AbstractPiece> stack = new ArrayList<AbstractPiece>();
		assertEquals((new FlatStone(p1)).toString(), board.getSquare(0, 0).get(0).toString());
		assertSame(piece2, board.getSquare(0, 0).get(1));
	}
	
	@Test (expected = MoveException.class)
	public void moveInsurmountablePieceCapToCapTest() throws Exception {
		AbstractPiece piece1 = new Capstone(p1);
		AbstractPiece piece2 = new Capstone(p2);
		Place move1 = new Place(board, piece1, 0, 0);
		Place move2 = new Place(board, piece2, 0, 1);
		List<Integer> drop = new ArrayList<Integer>();
		drop.add(0);
		drop.add(1);
		Move move3 = new Move(board, piece2, 0, 1, Direction.DOWN, drop);
		board.executeMove(move1);
		board.executeMove(move2);
		board.executeMove(move3);
	}

}
