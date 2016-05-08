package johnholiver.game.move;

import johnholiver.game.Board;
import johnholiver.game.Player;
import johnholiver.game.piece.AbstractPiece;

public abstract class AbstractMove 
{
	protected Board board;
	protected Player player;
	protected int x;
	protected int y;
	
	public AbstractMove(Board board, Player player, int x, int y){
		this.board = board;
		this.player = player;
		this.x = x;
		this.y = y;
	}

	public final void execute() throws Exception
	{
		try
		{
			validate();
			run();
			atSuccess();
		} catch (Exception e) {
			atFailure();
			throw e;
		}
	}
	
	public abstract void validate() throws Exception;
	protected abstract void run();
	protected abstract void atSuccess();
	protected abstract void atFailure();

	public abstract String toString();
}
