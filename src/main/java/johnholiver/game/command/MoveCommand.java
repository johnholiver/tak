package johnholiver.game.command;

import java.util.List;

import johnholiver.game.move.Direction;

public class MoveCommand extends GameCommand {
	
	private Direction direction;
	private List<Integer> drop;

	public MoveCommand(int x, int y, Direction direction, List<Integer> drop) {
		super(CommandType.GAME_MOVE, x, y);
		this.direction = direction;
		this.drop = drop;
	}

	public Direction getDirection() {
		return direction;
	}

	public List<Integer> getDrop() {
		return drop;
	}

}
