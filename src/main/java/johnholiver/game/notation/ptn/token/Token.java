package johnholiver.game.notation.ptn.token;

public abstract class Token {
	public static enum Type {
        STONE, SQUARE, DIRECTION, COUNT, TAK, MARK, COMMENT;
    }
	
	private Type type;
	protected String content;
	
	protected Token(Type type, String content)
	{
		this.setType(type);
		this.setContent(content);
	}

	public String toString()
	{
		return content;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
