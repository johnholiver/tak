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
	
	protected CommandType type;

	protected AbstractCommand() { }
	
	protected AbstractCommand(CommandType type)
	{
		this.type = type;
	}

	public CommandType getType() 
	{
		return type;
	}
	
	protected void setType(CommandType type) 
	{
		this.type = type;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (obj == null) {
	        return false;
	    }
	    if (!AbstractCommand.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    final AbstractCommand other = (AbstractCommand) obj;
	    if ((this.type == null) ? (other.type != null) : !this.type.equals(other.type)) {
	        return false;
	    }
	    return true;
	}
}
