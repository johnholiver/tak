package johnholiver.game.move.exceptions;

public class MoveException extends Exception {
	
	public MoveException(int x, int y, String message)
	{
		super("Tried to MOVE ["+x+","+y+"]: "+message);
	}
}
