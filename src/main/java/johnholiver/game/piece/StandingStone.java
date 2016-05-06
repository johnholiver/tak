package johnholiver.game.piece;

import johnholiver.game.Player;
import johnholiver.game.exceptions.OutOfStoneException;

public class StandingStone extends AbstractStone {

	public StandingStone(Player owner) throws OutOfStoneException {
		super(owner);
		this.name = "s";
	}
	
	@Override
	public boolean isFlat() {
		return false;
	}

	@Override
	public boolean isStanding() {
		return true;
	}

}
