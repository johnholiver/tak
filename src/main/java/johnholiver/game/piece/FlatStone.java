package johnholiver.game.piece;

import johnholiver.game.Player;
import johnholiver.game.exceptions.OutOfStoneException;

public class FlatStone extends AbstractStone {

	public FlatStone(Player owner) {
		super(owner);
		this.type = PieceType.FLATSTONE;
	}

	public FlatStone(AbstractPiece piece) {
		super(piece);
		this.type = PieceType.FLATSTONE;
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
