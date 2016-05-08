package johnholiver.game.notation.ptn.token;

import johnholiver.game.piece.PieceType;

public class StoneToken extends AbstractToken {

	public StoneToken(char c) {
		super(TokenType.STONE, String.valueOf(c));
	}

	public PieceType getValue() {
		switch (this.content) {
		case "F":
			return PieceType.FLATSTONE;
		case "S":
			return PieceType.STANDINGSTONE;
		case "C":
			return PieceType.CAPSTONE;
		}
		return null;
	}

}
