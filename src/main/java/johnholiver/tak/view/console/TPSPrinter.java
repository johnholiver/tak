package johnholiver.tak.view.console;

import johnholiver.tak.engine.Board;
import johnholiver.tak.engine.Game;

public class TPSPrinter {
	
	public void print(Game game)
	{
		Board board = game.getBoard();
		System.out.print(board.toString());
	}
}
