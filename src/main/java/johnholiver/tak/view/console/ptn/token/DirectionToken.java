package johnholiver.tak.view.console.ptn.token;

import johnholiver.tak.engine.move.Direction;

public class DirectionToken extends AbstractToken {

	public DirectionToken(char c) {
		super(TokenType.DIRECTION, String.valueOf(c));
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
