package johnholiver.game.command;

public class InfoCommand extends AbstractCommand {

	private String content;
	
	public InfoCommand(String message) {
		switch (message) {
		case "\'":
			setType(CommandType.INFO_TAK);
			break;
		case "!":
			setType(CommandType.INFO_EXCLAMATION);
			break;
		case "?":
			setType(CommandType.INFO_INTERROGATION);
			break;
		default:
			setType(CommandType.INFO_COMMENT);
			break;
		}
		this.content=message;
	}

	public String getContent() {
		return content;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (!InfoCommand.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    final InfoCommand other = (InfoCommand) obj;
	    if ((this.content == null) ? (other.content != null) : !this.content.equals(other.content)) {
	        return false;
	    }
	    return true && super.equals(obj);
	}
}
