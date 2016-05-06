package johnholiver.game.move;

import java.util.List;

import johnholiver.game.Board;
import johnholiver.game.piece.AbstractPiece;

public class Place extends AbstractMove {

	public Place(Board board, AbstractPiece piece, int x, int y) {
		super(board, piece, x, y);
	}

	@Override
	protected void run() 
	{
		board.getSquare(x, y).add(piece);
	}
	
	protected void validate() throws Exception 
	{
		List<AbstractPiece> aStack = board.getSquare(x, y);
		if (!aStack.isEmpty())
			throw new Exception("Tried to PLACE a piece on a occupied square");
	}

}
