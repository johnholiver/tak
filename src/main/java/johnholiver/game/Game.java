package johnholiver.game;

import java.util.ArrayList;
import johnholiver.game.move.AbstractMove;

public class Game {

	private Board board;
	
	private Player player1;
	private Player player2;
	
	private Player activePlayer;
	
	private ArrayList<AbstractMove> turns;

	public Game(int boardSize)
	{
		player1 = new Player(1);
		player2 = new Player(2);
		activePlayer = player1;
		
		board = new Board(boardSize);
		System.out.print(board.toString());
	}
}
