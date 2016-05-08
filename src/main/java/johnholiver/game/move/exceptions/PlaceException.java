package johnholiver.game.move.exceptions;

@SuppressWarnings("serial")
public class PlaceException extends Exception{
	
	public PlaceException(int x, int y, String message)
	{
		super("Tried to PLACE ["+x+","+y+"]: "+message);
	}

}
