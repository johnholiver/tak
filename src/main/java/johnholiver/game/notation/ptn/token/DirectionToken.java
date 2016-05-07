package johnholiver.game.notation.ptn.token;

import johnholiver.game.move.Direction;

public class DirectionToken extends Token {

	public DirectionToken(char c) {
		super(Type.DIRECTION, String.valueOf(c));
	}
	
	public Direction getValue()
	{
		switch (this.content) {
		case ">":
			return Direction.RIGHT;
		case "<":
			return Direction.LEFT;
		case "+":
			return Direction.UP;
		case "-":
			return Direction.DOWN;
		}
		return null;
	}

}
