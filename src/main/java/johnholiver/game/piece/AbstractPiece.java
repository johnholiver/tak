package johnholiver.game.piece;

import johnholiver.game.Player;

public abstract class AbstractPiece {
	
	protected Player owner;
	protected String name;
	
	public AbstractPiece(Player owner)
	{
		this.owner = owner;
	}
	
	public String toString()
	{
		return name+owner.getNumber();
	}
}
