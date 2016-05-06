package johnholiver.game.piece;

import johnholiver.game.Player;
import johnholiver.game.exceptions.OutOfStoneException;

public class FlatStone extends AbstractStone {

	public FlatStone(Player owner) throws OutOfStoneException {
		super(owner);
		this.name = "f";
	}

	public FlatStone(AbstractPiece piece) {
		super(piece);
		this.name = "f";
	}

	@Override
	public boolean isFlat() {
		return true;
	}

	@Override
	public boolean isStanding() {
		return false;
	}

}
