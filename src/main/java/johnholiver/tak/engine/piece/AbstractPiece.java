package johnholiver.tak.engine.piece;

import johnholiver.tak.engine.player.Player;

public abstract class AbstractPiece {
	
	protected Player owner;
	protected PieceType type;
	protected int locationX;
	protected int locationY;
	
	public AbstractPiece(Player owner)
	{
		this.owner = owner;
		locationX = -1;
		locationY = -1;
	}
	
	//Copy constructor
	public AbstractPiece(AbstractPiece piece)
	{
		this.owner = piece.getOwner();
		this.type = piece.type;
		this.locationX = piece.getLocation()[0];
		this.locationY = piece.getLocation()[1];
	}
	
	public Player getOwner() {
		return owner;
	}
	
	public int[] getLocation()
	{
		int[] location = {locationX, locationY};
		return location;
	}
	
	public void setLocation(int x, int y)
	{
		this.locationX = x;
		this.locationY = y;
	}

	@Override
	public String toString()
	{
		return "[Piece|"+type.name()+","+owner+"]";
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (!AbstractPiece.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    final AbstractPiece other = (AbstractPiece) obj;
	    if ((this.owner == null) ? (other.owner != null) : !this.owner.equals(other.owner)) {
	        return false;
	    }
	    if ((this.type == null) ? (other.type != null) : !this.type.equals(other.type)) {
	        return false;
	    }
	    if (this.locationX != other.locationX) {
	        return false;
	    }
	    if (this.locationY != other.locationY) {
	        return false;
	    }
	    return true;
	}


	public abstract boolean isFlat();
	public abstract boolean isStanding();

	public boolean isCapstone() {
		return (isFlat() && isStanding());
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
