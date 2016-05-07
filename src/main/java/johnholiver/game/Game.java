package johnholiver.game;

import java.util.ArrayList;
import java.util.List;

import johnholiver.game.exceptions.DrawException;
import johnholiver.game.exceptions.OutOfStoneException;
import johnholiver.game.move.AbstractMove;
import johnholiver.game.move.Move;
import johnholiver.game.move.Direction;
import johnholiver.game.move.Place;
import johnholiver.game.piece.AbstractPiece;
import johnholiver.game.piece.Capstone;
import johnholiver.game.piece.FlatStone;
import johnholiver.game.piece.StandingStone;

public class Game {

	private Board board;
	
	private Player player1;
	private Player player2;
	
	private Player activePlayer;
	private Player inactivePlayer;
	
	private ArrayList<AbstractMove> turns;

	public Game(int boardSize) throws Exception
	{
		player1 = new Player(1, "white", getCapstoneSet(boardSize), getStoneSet(boardSize));
		player2 = new Player(2, "black", getCapstoneSet(boardSize), getStoneSet(boardSize));
		activePlayer = player1;
		inactivePlayer = player2;
		
		board = new Board(boardSize);
	}
	
	public Player getActivePlayer()
	{
		return activePlayer;
	}
	
	private Player getActivePlayerInternal()
	{
		if (getTurn()<3)
			return activePlayer;
		else
			return inactivePlayer;
	}
	
	public String getActiveColor()
	{
		return getActivePlayerInternal().getColor();
	}
	
	public int getTurn()
	{
		return turns.size()+1;
	}
	
	public Board getBoard()
	{
		return board;
	}
	
	public boolean doPlace(int x, int y, String newPieceType)
	{
		AbstractPiece piece = null;
		try
		{
			switch (newPieceType) {
			case "f":
				piece = new FlatStone(getActivePlayerInternal());
				break;
			case "s":
				piece = new StandingStone(getActivePlayerInternal());
				break;
			case "c":
				piece = new Capstone(getActivePlayerInternal());
				break;
			}
		} catch (OutOfStoneException e) {
			this.atFailure(null, e);
		}
		if (piece!=null)
		{
			AbstractMove move = new Place(board, piece, x, y);
			try {
				board.executeMove(move);
				this.atSuccess(move);
				return true;
			} catch (Exception e) {
				if (newPieceType.equals("c"))
					activePlayer.incRemainingCapstone();
				else
					activePlayer.incRemainingStone();
				this.atFailure(move, e);
			}
		}
		return false;
	}
	
	public boolean doMove(int x, int y, Direction direction, List<Integer> drop)
	{
		List<AbstractPiece> aStack = board.getSquare(x, y);
		AbstractPiece aStackTop = aStack.get(aStack.size()-1);
		if (aStackTop.getOwner() == activePlayer)
		{
			AbstractMove move = new Move(board, aStackTop, x, y, direction, drop);
			try {
				board.executeMove(move);
				this.atSuccess(move);
				return true;
			} catch (Exception e) {
				this.atFailure(move, e);
			}
		}
		return false;
	}
	
	private void atSuccess(AbstractMove move) {
		//Add to successful turns list
		turns.add(move);
		//Change player
		Player aux = activePlayer;
		activePlayer=inactivePlayer;
		inactivePlayer=aux;
	}

	private void atFailure(AbstractMove move, Exception e) {
		// Do nothing, maybe log
	}
	
	public Player checkWinningConditions() throws DrawException
	{
		Player winner = null;
		winner = checkRoadWinCondition();
		if (winner==null)
			winner = checkFlatWinCondition();
		return winner;
	}
	
	private Player checkRoadWinCondition() {
		Player winner = null;
		List<List<AbstractPiece>> roads = board.getRoads();
		for (List<AbstractPiece> road : roads)
		{
			winner = road.get(0).getOwner();
			//DoubleWin: If the movement of the active player generate roads for both players
			//the active player will be the winner
			if (winner == activePlayer)
				return winner;
		}
		return winner;
	}

	private Player checkFlatWinCondition() throws DrawException {
		boolean fullBoard = board.isFull();
		boolean outOfPieces = (player1.getRemainingCapstone()+player1.getRemainingStone()==0) && 
							  (player2.getRemainingCapstone()+player2.getRemainingStone()==0);
		if (fullBoard || outOfPieces)
		{
			int[] flatCounters = board.countFlats();
			if (flatCounters[0]>flatCounters[1])
				return player1;
			else if (flatCounters[1]>flatCounters[2])
				return player2;
			else throw new DrawException("Flat counting resulted in a Draw");
		} else
			return null;
	}

	private int getStoneSet(int boardSize) throws Exception {
		switch (boardSize) {
		case 3:
			return 10;
		case 4:
			return 15;
		case 5:
			return 21;
		case 6:
			return 30;
		case 7:
			return 40;
		case 8:
			return 50;
		default:
			throw new Exception("Invalid Board Size");
		}
	}

	private int getCapstoneSet(int boardSize) throws Exception {
		switch (boardSize) {
		case 3:
		case 4:
			return 0;
		case 5:
		case 6:
			return 1;
		case 7:
		case 8:
			return 2;
		default:
			throw new Exception("Invalid Board Size");
		}
	}
}
