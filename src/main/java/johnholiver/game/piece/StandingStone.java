package johnholiver.game.piece;

import johnholiver.game.Player;

public class StandingStone extends AbstractStone {

	public StandingStone(Player owner) {
		super(owner);
		this.type = PieceType.STANDINGSTONE;
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
