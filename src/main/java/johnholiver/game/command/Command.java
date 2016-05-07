package johnholiver.game.command;

public abstract class Command {
	public static enum CommandType {
        GAME_PLACE, 
        GAME_MOVE, 
        INFO_TAK, 
        INFO_EXCLAMATION, 
        INFO_INTERROGATION, 
        INFO_COMMENT;
    }
	
	private CommandType type;

	protected Command() { }
	
	protected Command(CommandType type)
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
