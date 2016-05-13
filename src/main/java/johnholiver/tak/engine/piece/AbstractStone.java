package johnholiver.tak.engine.piece;

import johnholiver.tak.engine.player.Player;

public abstract class AbstractStone extends AbstractPiece{

	public AbstractStone(Player owner) {
		super(owner);
	}

	public AbstractStone(AbstractPiece piece) {
		super(piece);
	}

}
