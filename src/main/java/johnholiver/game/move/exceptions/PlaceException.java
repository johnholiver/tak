package johnholiver.game.move.exceptions;

public class PlaceException extends Exception{
	
	public PlaceException(int x, int y, String message)
	{
		super("Tried to PLACE ["+x+","+y+"]: "+message);
	}

}
