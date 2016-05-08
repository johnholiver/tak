package johnholiver.game.move;

import java.util.ArrayList;
import java.util.List;

import johnholiver.game.Board;
import johnholiver.game.Player;
import johnholiver.game.move.exceptions.MoveException;
import johnholiver.game.piece.AbstractPiece;
import johnholiver.game.piece.FlatStone;


public class Move extends AbstractMove {
	
	private Direction direction;
	private List<Integer> drop;

	public Move(Board board, Player player, int x, int y, Direction direction, List<Integer> drop) {
		super(board, player, x, y);
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
				piecesToDrop.add(dropStack.remove(0));
			}
			
			int[] nextLocation = getNextStackLocation(x, y, dropIndex);
			int nextX = nextLocation[0];
			int nextY = nextLocation[1];
			
			List<AbstractPiece> nextStack = board.getSquare(nextX, nextY);
			if (!nextStack.isEmpty())
			{
				AbstractPiece topNextStack = nextStack.get(nextStack.size()-1);
				if (topNextStack.isStanding())
				{
					nextStack.add(new FlatStone(topNextStack));
					nextStack.remove(topNextStack);
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
			throw new MoveException(x, y, "Initial stack is empty");
		if (initialStack.get(initialStack.size()-1).getOwner() != player)
			throw new MoveException(x, y, "Top piece does not belong to player: "+initialStack.get(initialStack.size()-1).getOwner()+"!="+player);
		if (drop.isEmpty() || drop.size() < 2)
			throw new MoveException(x, y, "Piece drop order is not valid. Drop order is empty or inferior to inferior to 2 stacks");
		
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
					throw new MoveException(x, y, "Piece drop order is not valid. Drop order total quantity is superior to the initial stack");
				}
			}
			totalPiecesDroped+=drop.get(dropIndex);
			
			//Test Carry limit
			if (piecesToDrop.size() > board.getSize())
				throw new MoveException(x, y, "Piece drop order is not valid. Carry limit of "+board.getSize()+" check at drop "+dropIndex);
			int[] nextLocation = getNextStackLocation(x, y, dropIndex);
			int nextX = nextLocation[0];
			int nextY = nextLocation[1];
			if (nextX < 0 
				|| nextX > board.getSize()-1
				|| nextY < 0
				|| nextY > board.getSize()-1)
				throw new MoveException(x, y, "Piece drop order is not valid for given direction. Board out of bounds: ("+nextX+","+nextY+")");
			
			
			if (piecesToDrop.size() == 0)
			{
				if (dropIndex != 0)
					throw new MoveException(x, y, "Piece drop order is not valid. Dropping 0 pieces at drop "+dropIndex);
			} else {
				//Test Insurmountable Pieces
				List<AbstractPiece> nextStack = board.getSquare(nextX, nextY);
				if (!nextStack.isEmpty())
				{
					AbstractPiece topNextStack = nextStack.get(nextStack.size()-1);
					if (topNextStack.isCapstone()
						|| (topNextStack.isStanding() && !piecesToDrop.get(0).isCapstone()))
					{
						throw new MoveException(x, y, "Piece drop order is not valid. Insurmountable Pieces at ("+nextX+","+nextY+")");
					}
				}
			}
		}
		if (totalPiecesDroped != initialStack.size())
		{
			throw new MoveException(x, y, "Piece drop order is not valid. Drop order total quantity is inferior to the initial stack");
		}
	}

	private int[] getNextStackLocation(int x, int y, int modifier) {
		int[] nextLocation = {x,y};
		switch (direction) {
		case UP:
			nextLocation[1]+=modifier;
			break;
		case DOWN:
			nextLocation[1]-=modifier;
			break;
		case LEFT:
			nextLocation[0]-=modifier;
			break;
		case RIGHT:
			nextLocation[0]+=modifier;
			break;
		}
		return nextLocation;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void atSuccess() {
		//Do nothing
	}

	@Override
	protected void atFailure() {
		//Do nothing	
	}

}
