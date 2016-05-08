package johnholiver.game.piece;

import johnholiver.game.Player;

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
