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
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (!MoveCommand.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    final MoveCommand other = (MoveCommand) obj;
	    if ((this.direction == null) ? (other.direction != null) : !this.direction.equals(other.direction)) {
	        return false;
	    }
	    //TODO: drop
	    return true && super.equals(obj);
	}

}
