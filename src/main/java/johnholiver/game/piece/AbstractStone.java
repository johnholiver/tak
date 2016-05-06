package johnholiver.game.piece;

import johnholiver.game.Player;
import johnholiver.game.exceptions.OutOfStoneException;

public abstract class AbstractStone extends AbstractPiece{

	public AbstractStone(Player owner) throws OutOfStoneException {
		super(owner);
		owner.decRemainingStone();
	}

	public AbstractStone(AbstractPiece piece) {
		super(piece);
	}

}
