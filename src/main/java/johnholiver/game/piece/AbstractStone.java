package johnholiver.game.piece;

import johnholiver.game.Player;

public abstract class AbstractStone extends AbstractPiece{

	public AbstractStone(Player owner) {
		super(owner);
	}

	public AbstractStone(AbstractPiece piece) {
		super(piece);
	}

}
