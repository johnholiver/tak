package johnholiver.game;

import java.util.ArrayList;
import java.util.List;

import johnholiver.game.move.AbstractMove;
import johnholiver.game.piece.AbstractPiece;

public class Game {

	private Board board;
	
	private Player player1;
	private Player player2;
	
	private Player activePlayer;
	
	private ArrayList<AbstractMove> turns;

	public Game(int boardSize) throws Exception
	{
		player1 = new Player(1, "white", getCapstoneSet(boardSize), getStoneSet(boardSize));
		player2 = new Player(2, "black", getCapstoneSet(boardSize), getStoneSet(boardSize));
		activePlayer = player1;
		
		board = new Board(boardSize);
		System.out.print(board.toString());
	}
	
	private Player checkWinningConditions() throws Exception
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

	private Player checkFlatWinCondition() throws Exception {
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
			else throw new Exception("Flat counting resulted in a Draw");
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
