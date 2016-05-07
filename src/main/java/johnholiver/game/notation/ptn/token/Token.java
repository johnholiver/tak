package johnholiver.game.notation.ptn.token;

public abstract class Token {
	public static enum TokenType {
        STONE, SQUARE, DIRECTION, COUNT, MARK, COMMENT;
    }
	
	private TokenType type;
	protected String content;
	
	protected Token(TokenType type, String content)
	{
		this.setType(type);
		this.setContent(content);
	}

	public String toString()
	{
		return content;
	}

	public TokenType getType() {
		return type;
	}

	public void setType(TokenType type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
