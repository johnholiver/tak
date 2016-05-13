package johnholiver.tak.engine.command;

public abstract class GameCommand extends AbstractCommand {

	private int x;
	private int y;

	protected GameCommand(CommandType type, int x, int y)
	{
		super(type);
		this.x = x;
		this.y = y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (!GameCommand.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    final GameCommand other = (GameCommand) obj;
	    if (this.x != other.x) {
	        return false;
	    }
	    if (this.y != other.y) {
	        return false;
	    }
	    return true && super.equals(obj);
	}

}
