package johnholiver.game.notation;

import johnholiver.game.Board;
import johnholiver.game.Game;

public class TPSPrinter {

	private Board board;
	
	public TPSPrinter(Game game)
	{
		this.board = game.getBoard();
	}
	
	public void print()
	{
		System.out.print(board.toString());
	}
}
