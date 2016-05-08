package johnholiver.game;

import java.util.Scanner;

import johnholiver.game.notation.TPSPrinter;
import johnholiver.game.notation.ptn.PTNInterface;

/**
 * Hello world!
 *
 */
public class TakMain {
	private static Scanner scan;
	private static PTNInterface view; 
	private static TPSPrinter printer;
	
	public static void main(String[] args)
	{
		scan = new Scanner(System.in);
		view = new PTNInterface();
		
		Game activeGame = null;
		
		System.out.println( "Welcome to Tak!" );
		System.out.println("=========================");
		help();
		System.out.println("=========================");
		String input = scan.nextLine().trim();
		while (!input.equals("END"))
		{
			switch (input) {
			case "NEW":
				System.out.println("Select a board size [3-8]:");
				String boardSizeInput = scan.nextLine().trim();
				try {
					activeGame = new Game(Integer.parseInt(boardSizeInput));
					printer = new TPSPrinter(activeGame);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("======== GAME ON ========");
				break;
			case "HELP":
				help();
				break;
			case "PRINT":
				if (printer!=null)
					printer.print();
				break;
			default:
				if (activeGame!=null)
					try {
						view.execute(activeGame, input);
					} catch (Exception e) {
						e.getMessage();
					}
				break;
			}
			System.out.println("=========================");
			input = scan.nextLine().trim();
		}
    }

	private static void help() {
		System.out.println( "This implementation of Tak follows the PTN for movement announce." );
		System.out.println( "List of commands:" );
		System.out.println( "\"NEW\"	: Starts a new game" );
		System.out.println( "\"PRINT\"	: Print last valid board" );
		System.out.println( "\"HELP\"	: Shows the list of commands" );
		System.out.println( "\"END\"	: Closes the app" );
	}
}
