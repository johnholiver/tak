package johnholiver.tak.engine.move;

import java.util.List;

import johnholiver.tak.engine.Board;
import johnholiver.tak.engine.Player;
import johnholiver.tak.engine.exceptions.OutOfStoneException;
import johnholiver.tak.engine.move.exceptions.PlaceException;
import johnholiver.tak.engine.piece.AbstractPiece;
import johnholiver.tak.engine.piece.PieceType;

public class Place extends AbstractMove {

	private AbstractPiece piece;
	
	public Place(Board board, Player player, int x, int y, PieceType newPieceType) {
		super(board, player, x, y);
		try
		{
			piece = player.popStone(newPieceType);
		} catch(OutOfStoneException e) {
			piece = null;
		}
	}

	@Override
	protected void run() 
	{
		piece.setLocation(x, y);
		board.getSquare(x, y).add(piece);
	}
	
	public void validate() throws Exception 
	{
		if(piece==null)
			throw new OutOfStoneException(player);
		try
		{
			List<AbstractPiece> aStack = board.getSquare(x, y);
			if (!aStack.isEmpty())
				throw new PlaceException(x, y, "Square already occupied");
		} catch (IndexOutOfBoundsException e) {
			throw new PlaceException(x, y, "Out of board bounds");
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void atSuccess() {
		//Do nothing
	}

	@Override
	protected void atFailure() {
		if (piece!=null)
		{
			if (piece.isCapstone())
				player.incRemainingCapstone();
			else
				player.incRemainingStone();
		}
	}

}
