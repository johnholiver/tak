package johnholiver.game.move;

import java.util.List;

import johnholiver.game.Board;
import johnholiver.game.move.exceptions.PlaceException;
import johnholiver.game.piece.AbstractPiece;

public class Place extends AbstractMove {

	public Place(Board board, AbstractPiece piece, int x, int y) {
		super(board, piece, x, y);
	}

	@Override
	protected void run() 
	{
		piece.setLocation(x, y);
		board.getSquare(x, y).add(piece);
	}
	
	public void validate() throws Exception 
	{
		try {
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

}
