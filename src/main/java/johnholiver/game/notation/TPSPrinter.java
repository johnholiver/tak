package johnholiver.game.notation;

import johnholiver.game.Board;

public class TPSPrinter {

	private Board board;
	
	public TPSPrinter(Board board)
	{
		this.board = board;
	}
	
	public void print()
	{
		System.out.print(board.toString());
	}
}
