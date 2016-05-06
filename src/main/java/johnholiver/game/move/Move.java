package johnholiver.game.move;

import java.util.ArrayList;
import java.util.List;

import johnholiver.game.Board;
import johnholiver.game.piece.AbstractPiece;
import johnholiver.game.piece.FlatStone;


public class Move extends AbstractMove {

	public enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}
	
	private Direction direction;
	private ArrayList<Integer> drop;

	public Move(Board board, AbstractPiece piece, int x, int y, Direction direction, ArrayList<Integer> drop) {
		super(board, piece, x, y);
		this.direction = direction;
		this.drop = drop;
	}

	@Override
	protected void run() {
		List<AbstractPiece> initialStack = board.getSquare(x, y);
		List<AbstractPiece> dropStack = new ArrayList<AbstractPiece>();
		dropStack.addAll(initialStack);
		initialStack.clear();
		
		for (int dropIndex = 0; dropIndex < drop.size(); dropIndex++)
		{
			List<AbstractPiece> piecesToDrop = new ArrayList<AbstractPiece>();
			for (int i = 0; i < drop.get(dropIndex); i++)
			{
				piecesToDrop.add(dropStack.get(i));
			}
			dropStack.removeAll(piecesToDrop);
			
			int nextX = x;
			int nextY = y;
			switch (direction) {
			case UP:
				nextX+=dropIndex;
				break;
			case DOWN:
				nextX-=dropIndex;
				break;
			case LEFT:
				nextY-=dropIndex;
				break;
			case RIGHT:
				nextY+=dropIndex;
				break;
			}
			
			List<AbstractPiece> nextStack = board.getSquare(nextX, nextY);
			if (!nextStack.isEmpty())
			{
				AbstractPiece topNextStack = nextStack.get(nextStack.size()-1);
				if (topNextStack.isStanding())
				{
					topNextStack = new FlatStone(topNextStack);
				}
			}
			for (AbstractPiece piece : piecesToDrop)
			{
				piece.setLocation(nextX, nextY);
				nextStack.add(piece);
			}
		}
	}

	public void validate() throws Exception {
		List<AbstractPiece> initialStack = board.getSquare(x, y);
		
		//Test for initial exceptions
		if (initialStack.isEmpty())
			throw new Exception("Tried to MOVE: Initial stack is empty");
		if (initialStack.get(initialStack.size()-1) != piece)
			throw new Exception("Tried to MOVE: Top piece is different from what has been expected: "+piece+"!="+initialStack.get(initialStack.size()-1));
		if (drop.isEmpty() || drop.size() < 2)
			throw new Exception("Tried to MOVE: Piece drop order is not valid. Drop order is empty or inferior to inferior to 2 stacks");
		
		//Test dropping exceptions
		int totalPiecesDroped = 0;
		for (int dropIndex = 0; dropIndex < drop.size(); dropIndex++)
		{
			List<AbstractPiece> piecesToDrop = new ArrayList<AbstractPiece>();
			for (int i = totalPiecesDroped; i < totalPiecesDroped+drop.get(dropIndex); i++)
			{
				try {
					piecesToDrop.add(initialStack.get(i));
				} catch (ArrayIndexOutOfBoundsException e) {
					throw new Exception("Tried to MOVE: Piece drop order is not valid. Drop order total quantity is superior to the initial stack");
				}
			}
			totalPiecesDroped+=drop.get(dropIndex);
			
			//Test Carry limit
			if (piecesToDrop.size() > board.getSize())
				throw new Exception("Tried to MOVE: Piece drop order is not valid. Carry limit of "+board.getSize()+" check at drop "+dropIndex);
			int nextX = x;
			int nextY = y;
			switch (direction) {
			case UP:
				nextX+=dropIndex;
				break;
			case DOWN:
				nextX-=dropIndex;
				break;
			case LEFT:
				nextY-=dropIndex;
				break;
			case RIGHT:
				nextY+=dropIndex;
				break;
			}
			if (nextX < 0 
				|| nextX > board.getSize()-1
				|| nextY < 0
				|| nextY > board.getSize()-1)
				throw new Exception("Tried to MOVE: Piece drop order is not valid for given direction. Board out of bounds: ("+nextX+","+nextY+")");
			
			
			if (piecesToDrop.size() == 0)
			{
				if (dropIndex != 0)
					throw new Exception("Tried to MOVE: Piece drop order is not valid. Dropping 0 pieces at drop "+dropIndex);
			} else {
				//Test Insurmountable Pieces
				List<AbstractPiece> nextStack = board.getSquare(nextX, nextY);
				if (!nextStack.isEmpty())
				{
					AbstractPiece topNextStack = nextStack.get(nextStack.size()-1);
					if (topNextStack.isCapstone()
						|| (topNextStack.isStanding() && !piecesToDrop.get(0).isCapstone()))
					{
						throw new Exception("Tried to MOVE: Piece drop order is not valid. Insurmountable Pieces at ("+nextX+","+nextY+")");
					}
				}
			}
		}
	}

}
