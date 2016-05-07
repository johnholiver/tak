package johnholiver.game.command;

public class PlaceCommand extends GameCommand {

	private String pieceType;

	public PlaceCommand(int x, int y, String pieceType) {
		super(CommandType.GAME_PLACE, x, y);
		this.pieceType = pieceType;
	}

	public String getPieceType() {
		return pieceType;
	}

}
