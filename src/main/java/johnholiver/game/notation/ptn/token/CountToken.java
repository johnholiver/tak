package johnholiver.game.notation.ptn.token;

public class CountToken extends Token {

	public CountToken(char c) {
		super(Type.COUNT, String.valueOf(c));
	}
	
	public int getValue()
	{
		return Integer.parseInt(this.content);
	}
}
