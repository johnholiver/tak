package johnholiver.game.command;

import johnholiver.game.piece.PieceType;

public class PlaceCommand extends GameCommand {

	private PieceType pieceType;

	public PlaceCommand(int x, int y, PieceType pieceType) {
		super(CommandType.GAME_PLACE, x, y);
		this.pieceType = pieceType;
	}

	public PieceType getPieceType() {
		return pieceType;
	}

}
