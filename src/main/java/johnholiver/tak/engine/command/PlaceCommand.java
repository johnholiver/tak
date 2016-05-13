package johnholiver.tak.engine.command;

import johnholiver.tak.engine.piece.PieceType;

public class PlaceCommand extends GameCommand {

	private PieceType pieceType;

	public PlaceCommand(int x, int y, PieceType pieceType) {
		super(CommandType.GAME_PLACE, x, y);
		this.pieceType = pieceType;
	}

	public PieceType getPieceType() {
		return pieceType;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (!PlaceCommand.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    final PlaceCommand other = (PlaceCommand) obj;
	    if ((this.pieceType == null) ? (other.pieceType != null) : !this.pieceType.equals(other.pieceType)) {
	        return false;
	    }
	    return true && super.equals(obj);
	}

}
