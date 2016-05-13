package johnholiver.tak.view.console.ptn.token;

public class SquareToken extends AbstractToken {

	public SquareToken(String c) {
		super(TokenType.SQUARE, String.valueOf(c));
	}
	
	public int getX()
	{
		switch (this.content.charAt(0)) {
		case 'a':
			return 0;
		case 'b':
			return 1;
		case 'c':
			return 2;
		case 'd':
			return 3;
		case 'e':
			return 4;
		case 'f':
			return 5;
		case 'g':
			return 6;
		case 'h':
			return 7;
		}
		return -1;
	}
	
	public int getY()
	{
		return Integer.parseInt(this.content.substring(1))-1;
	}

}
