package johnholiver.tak.engine;

import johnholiver.tak.engine.exceptions.OutOfStoneException;
import johnholiver.tak.engine.piece.AbstractPiece;
import johnholiver.tak.engine.piece.Capstone;
import johnholiver.tak.engine.piece.FlatStone;
import johnholiver.tak.engine.piece.PieceType;
import johnholiver.tak.engine.piece.StandingStone;

public class Player {
	
	private int number;
	private String color;
	private int remainingCapstone = 0;
	private int remainingStone = 0;

	public Player(int number, String color, int remainingCapstone, int remainingStone) {
		this.number = number;
		this.color = color;
		this.remainingCapstone = remainingCapstone;
		this.remainingStone = remainingStone;
	}

	public int getNumber() {
		return number;
	}
	
	public String getColor() {
		return color;
	}

	public void decRemainingCapstone() throws OutOfStoneException {
		if (remainingCapstone > 0)
			remainingCapstone--;
		else
			throw new OutOfStoneException(this);
	}
	
	public void decRemainingStone() throws OutOfStoneException {
		if (remainingStone > 0)
			remainingStone--;
		else
			throw new OutOfStoneException(this);
	}
	
	public void incRemainingCapstone() {
		remainingCapstone++;
	}
	
	public void incRemainingStone() {
		remainingStone++;
	}
	
	public int getRemainingCapstone() {
		return remainingCapstone;
	}

	public int getRemainingStone() {
		return remainingStone;
	}
	
	public AbstractPiece popStone(PieceType newPieceType) throws OutOfStoneException {
		switch (newPieceType) {
		case FLATSTONE:
			decRemainingStone();
			return new FlatStone(this);
		case STANDINGSTONE:
			decRemainingStone();
			return new StandingStone(this);
		case CAPSTONE:
			decRemainingCapstone();
			return new Capstone(this);
		}
		return null;
	}

	@Override
	public String toString() {
		return "[Player|"+number+","+color+"]";
	}

	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (!Player.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    final Player other = (Player) obj;
	    if ((this.color == null) ? (other.color != null) : !this.color.equals(other.color)) {
	        return false;
	    }
	    if (this.number != other.number) {
	        return false;
	    }
	    return true;
	}

}
