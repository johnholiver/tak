package johnholiver.tak.engine.piece;

import johnholiver.tak.engine.Player;

public class Capstone extends AbstractPiece {

	public Capstone(Player owner) {
		super(owner);
		this.type = PieceType.CAPSTONE;
	}

	@Override
	public boolean isFlat() {
		return true;
	}

	@Override
	public boolean isStanding() {
		return true;
	}

}
