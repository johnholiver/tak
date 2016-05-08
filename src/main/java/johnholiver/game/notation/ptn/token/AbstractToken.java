package johnholiver.game.notation.ptn.token;

public abstract class AbstractToken {
	public static enum TokenType {
        STONE, SQUARE, DIRECTION, COUNT, MARK, COMMENT;
    }
	
	private TokenType type;
	protected String content;
	
	protected AbstractToken(TokenType type, String content)
	{
		this.setType(type);
		this.setContent(content);
	}
	
	@Override
	public String toString()
	{
		return content;
	}
	

	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (!AbstractToken.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    final AbstractToken other = (AbstractToken) obj;
	    if ((this.type == null) ? (other.type != null) : !this.type.equals(other.type)) {
	        return false;
	    }
	    if (!this.content.equals(other.content)) {
	        return false;
	    }
	    return true;
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
