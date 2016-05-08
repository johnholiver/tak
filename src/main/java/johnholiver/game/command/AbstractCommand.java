package johnholiver.game.command;

public abstract class AbstractCommand {
	public static enum CommandType {
        GAME_PLACE, 
        GAME_MOVE, 
        INFO_TAK, 
        INFO_EXCLAMATION, 
        INFO_INTERROGATION, 
        INFO_COMMENT;
    }
	
	private CommandType type;

	protected AbstractCommand() { }
	
	protected AbstractCommand(CommandType type)
	{
		this.type = type;
	}

	public CommandType getType() {
		return type;
	}
	
	protected void setType(CommandType type) {
		this.type = type;
	}

}
