package johnholiver.game.piece;

import johnholiver.game.Player;
import johnholiver.game.exceptions.OutOfStoneException;

public class Capstone extends AbstractPiece {

	public Capstone(Player owner) throws OutOfStoneException {
		super(owner);
		this.name = "c";
		owner.decRemainingCapstone();
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
