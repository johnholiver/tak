package johnholiver.game.move;

import johnholiver.game.Board;
import johnholiver.game.piece.AbstractPiece;

public abstract class AbstractMove 
{
	protected Board board;
	protected int x;
	protected int y;
	protected AbstractPiece piece;
	
	public AbstractMove(Board board, AbstractPiece piece, int x, int y){
		this.board = board;
		this.piece = piece;
		this.x = x;
		this.y = y;
	}

	public final void execute() throws Exception
	{
		validate();
		run();
	}
	
	protected abstract void validate() throws Exception;
	protected abstract void run();

}
