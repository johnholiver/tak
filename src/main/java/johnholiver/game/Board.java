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
		for (int y=boardSize-1; y>=0; y--)
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
				if (!aStack.isEmpty())
				{
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
		}
		return flatCounter;
	}

	public List<List<AbstractPiece>> getRoads() {
		List<List<AbstractPiece>> roads = new ArrayList<List<AbstractPiece>>();
		int boardSize = getSize();
		
		//Clusterize groups of pieces
		for (int y=0; y<boardSize; y++)
		{
			for (int x=0; x<boardSize; x++)
			{
				List<AbstractPiece> aStack = this.getSquare(x,y);
				if (!aStack.isEmpty())
				{
					AbstractPiece piece = aStack.get(aStack.size()-1);
				
					List<List<AbstractPiece>> roadsToJoin = getAdjacentRoad(roads, piece);
					List<AbstractPiece> newRoad = new ArrayList<AbstractPiece>();
					for (List<AbstractPiece> roadToJoin : roadsToJoin)
					{
						newRoad.addAll(roadToJoin);
						roads.remove(roadToJoin);
					}

					newRoad.add(piece);
					roads.add(newRoad);	
				}
			}
		}
		
		//Purge clusters which aren't roads
		List<List<AbstractPiece>> roadsToPurge = new ArrayList<List<AbstractPiece>>();
		for (List<AbstractPiece> road : roads)
		{
			boolean firstRow = false;
			boolean lastRow = false;
			boolean firstColumn = false;
			boolean lastColumn = false;

			for (AbstractPiece roadPiece : road)
			{
				int[] pieceLocation = roadPiece.getLocation();
				if (pieceLocation[0] == 0)
					firstColumn = true;
				if (pieceLocation[0] == this.getSize()-1)
					lastColumn = true;
				if (pieceLocation[1] == 0)
					firstRow = true;
				if (pieceLocation[1] == this.getSize()-1)
					lastRow = true;
			}
			
			if (!((firstRow && lastRow) || (firstColumn && lastColumn)))
				roadsToPurge.add(road);
		}
		roads.removeAll(roadsToPurge);
		return roads;
	}
	
	private List<List<AbstractPiece>> getAdjacentRoad(List<List<AbstractPiece>> roads, AbstractPiece piece) {
		List<List<AbstractPiece>> roadsToJoin = new ArrayList<List<AbstractPiece>>();
		for (List<AbstractPiece> road : roads)
		{
			for (AbstractPiece roadPiece : road)
			{
				if (piece.getOwner()==roadPiece.getOwner()
					&& piece.isNeighbor(roadPiece))
				{
					if (!roadsToJoin.contains(road))
						roadsToJoin.add(road);
					break;
				}
			}
		}
		return roadsToJoin;
	}
}