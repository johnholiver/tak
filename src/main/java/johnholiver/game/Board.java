package johnholiver.game;

import java.util.ArrayList;
import java.util.List;

import johnholiver.game.move.AbstractMove;
import johnholiver.game.piece.AbstractPiece;

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

	public int getSize() {
		return board.size();
	}

	public boolean isFull() {
		int boardSize = getSize();
		for (int y=0; y<boardSize; y++)
			for (int x=0; x<boardSize; x++)
				if (getSquare(x, y).isEmpty())
					return false;
		return true;
	}
	
	@Override
	public String toString()
	{
		String boardString = "";
		int boardSize = getSize();
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

	public int[] countFlats() {
		int[] flatCounter = {0,0};
		int boardSize = getSize();
		for (int y=0; y<boardSize; y++)
		{
			for (int x=0; x<boardSize; x++)
			{
				List<AbstractPiece> aStack = getSquare(x, y);
				AbstractPiece piece = aStack.get(aStack.size()-1);
				if (piece.isFlat())
				{
					if (piece.getOwner().getNumber()==1)
						flatCounter[0]++;
					else if (piece.getOwner().getNumber()==2)
						flatCounter[1]++;
				}
			}
		}
		return flatCounter;
	}

	public List<List<AbstractPiece>> getRoads() {
		//TODO: Road isn't a List<AbstractPiece>, it needs the coordinates of each piece
		List<List<AbstractPiece>> roads = new ArrayList<List<AbstractPiece>>();
		int boardSize = getSize();
		for (int y=0; y<boardSize; y++)
		{
			List<AbstractPiece> road = getRoad(0,y);
			if (road!=null)
				roads.add(road);
		}
		for (int x=0; x<boardSize; x++)
		{
			List<AbstractPiece> road = getRoad(x,0);
			if (road!=null)
				roads.add(road);
		}
		return roads;
	}

	private List<AbstractPiece> getRoad(int initialX, int initialY) {
		List<AbstractPiece> initialStack = getSquare(initialX, initialY);
		AbstractPiece initialPiece;
		Player roadOwner;
		if (!initialStack.isEmpty())
		{
			initialPiece = initialStack.get(initialStack.size()-1);
			if (initialPiece.isFlat())
				roadOwner = initialPiece.getOwner();
		}
		return null;
	}
}