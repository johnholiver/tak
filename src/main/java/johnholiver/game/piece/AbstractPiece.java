package johnholiver.game.piece;

import johnholiver.game.Player;

public abstract class AbstractPiece {
	
	protected Player owner;
	protected String name;
	
	public AbstractPiece(Player owner)
	{
		this.owner = owner;
	}
	
	public Player getOwner() {
		return owner;
	}

	public String toString()
	{
		return name+owner.getNumber();
	}

	public abstract boolean isFlat();
	public abstract boolean isStanding();

	public boolean isCapstone() {
		return (isFlat() && isStanding());
	}
}
