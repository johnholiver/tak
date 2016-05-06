package johnholiver.game.piece;

import johnholiver.game.Player;

public class FlatStone extends AbstractStone {

	public FlatStone(Player owner) {
		super(owner);
		this.name = "f";
	}

	public FlatStone(AbstractPiece piece) {
		super(piece);
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
