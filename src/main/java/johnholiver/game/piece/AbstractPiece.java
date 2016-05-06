package johnholiver.game.piece;

import johnholiver.game.Player;

public abstract class AbstractPiece {
	
	protected Player owner;
	protected String name;
	protected int locationX;
	protected int locationY;
	
	public AbstractPiece(Player owner)
	{
		this.owner = owner;
		locationX = -1;
		locationY = -1;
	}
	
	public AbstractPiece(AbstractPiece piece)
	{
		this.owner = piece.getOwner();
		this.locationX = piece.getLocation()[0];
		this.locationY = piece.getLocation()[1];
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
	
	public void setLocation(int x, int y)
	{
		this.locationX = x;
		this.locationY = y;
	}
	
	public int[] getLocation()
	{
		int[] location = {locationX, locationY};
		return location;
	}

	public boolean isNeighbor(AbstractPiece piece) {
		int[] pieceLocation = piece.getLocation();
		if (pieceLocation[0]==this.locationX)
			if (pieceLocation[1]==this.locationY-1 || pieceLocation[1]==this.locationY+1)
				return true;
		if (pieceLocation[1]==this.locationY)
			if (pieceLocation[0]==this.locationX-1 || pieceLocation[0]==this.locationX+1)
				return true;		
		return false;
	}
}
