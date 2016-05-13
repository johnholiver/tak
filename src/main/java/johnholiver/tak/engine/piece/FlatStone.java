package johnholiver.tak.engine.piece;

import johnholiver.tak.engine.Player;

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
