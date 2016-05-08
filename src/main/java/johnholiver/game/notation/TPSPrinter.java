package johnholiver.game.notation;

import johnholiver.game.Board;
import johnholiver.game.Game;

public class TPSPrinter {
	
	public void print(Game game)
	{
		Board board = game.getBoard();
		System.out.print(board.toString());
	}
}
