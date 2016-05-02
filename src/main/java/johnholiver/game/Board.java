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
		for (int y=0; y<boardSize; y++)
		{
			List<List<AbstractPiece>> aRow = new ArrayList<List<AbstractPiece>>();
			board.add(aRow);
			for (int x=0; x<boardSize; x++)
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
	
	@Override
	public String toString()
	{
		String boardString = "";
		int boardSize = board.size();
		for (int y=0; y<boardSize; y++)
		{
			List<List<AbstractPiece>> aRow = board.get(y);
			String rowString = "";
			for (int x=0; x<boardSize; x++)
			{
				List<AbstractPiece> aStack = aRow.get(x);
				String stackString = "";
				for (AbstractPiece piece : aStack)
				{
					stackString+=piece.toString();
					stackString+=",";
				}
				if (!stackString.isEmpty())
					rowString+=stackString.substring(0, stackString.length()-1);
				rowString+="|";
			}
			if (!rowString.isEmpty())
				boardString+=rowString.substring(0, rowString.length()-1);
			boardString+="\n";
		}
		return boardString;
	}
}