package johnholiver.game;

import java.util.ArrayList;
import java.util.List;

import johnholiver.game.move.AbstractMove;
import johnholiver.game.piece.AbstractPiece;;

public class Board {
	//Matrix of stacks (I need the ability to break a vector 
	//taking the first element and passing the stack to other square)
	private List<List<List<AbstractPiece>>> board;

	public Board(int boardSize) {
		
		//Board Initialization
		board = new ArrayList<List<List<AbstractPiece>>>();
		for (int i=0; i<boardSize; i++)
		{
			List<List<AbstractPiece>> aRow = new ArrayList<List<AbstractPiece>>();
			board.add(aRow);
			for (int j=0; j<boardSize; j++)
			{
				List<AbstractPiece> aStack = new ArrayList<AbstractPiece>();
				aRow.add(aStack); 
			}
		}
	}
	
	public List<AbstractPiece> getSquare(int x, int y)
	{
		return board.get(y).get(x);
	}
	
	public void executeMove(AbstractMove move) throws Exception
	{
		move.execute();
	}
}