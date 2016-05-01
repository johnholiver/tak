package johnholiver.game.move;

import java.util.List;

import johnholiver.game.Board;
import johnholiver.game.piece.AbstractPiece;

public class Place extends AbstractMove {

	public Place(Board board, AbstractPiece piece, int x, int y) {
		super(board, piece, x, y);
	}

	@Override
	public void execute() throws Exception {
		List<AbstractPiece> aStack = board.getSquare(x, y);
		if (aStack.isEmpty())
		{
			aStack.add(piece);
		} else {
			throw new Exception("Tried to place a piece on a occupied square");
		}
	}

}
