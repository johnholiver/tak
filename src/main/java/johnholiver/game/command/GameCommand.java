package johnholiver.game.command;

import johnholiver.game.command.AbstractCommand.CommandType;

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

}
