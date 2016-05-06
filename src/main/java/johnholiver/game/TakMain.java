package johnholiver.game;

/**
 * Hello world!
 *
 */
public class TakMain {

	public static void main(String[] args)
	{
        System.out.println( "Hello World!" );
        try {
			Game game = new Game(3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
