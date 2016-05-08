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
}
