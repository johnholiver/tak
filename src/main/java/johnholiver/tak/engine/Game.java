package johnholiver.tak.engine;

import java.util.ArrayList;
import java.util.List;

import johnholiver.tak.engine.command.AbstractCommand;
import johnholiver.tak.engine.command.MoveCommand;
import johnholiver.tak.engine.command.PlaceCommand;
import johnholiver.tak.engine.exceptions.DrawException;
import johnholiver.tak.engine.move.AbstractMove;
import johnholiver.tak.engine.move.Move;
import johnholiver.tak.engine.move.Place;
import johnholiver.tak.engine.piece.AbstractPiece;
import johnholiver.tak.engine.player.Player;

public class Game {

	private Board board;
	
	private Player player1;
	private Player player2;
	
	private Player activePlayer;
	private Player inactivePlayer;
	
	private List<AbstractMove> turns;

	protected Game()
	{
		turns = new ArrayList<AbstractMove>();
	}
	
	public Game(int boardSize) throws Exception
	{
		this();
		this.player1 = new Player(1, "white", getCapstoneSet(boardSize), getStoneSet(boardSize));
		this.player2 = new Player(2, "black", getCapstoneSet(boardSize), getStoneSet(boardSize));
		activePlayer = this.player1;
		inactivePlayer = this.player2;
		
		board = new Board(boardSize);
	}
	
	public Game(int boardSize, Player player1, Player player2) throws Exception
	{
		this();
		this.player1 = player1;
		this.player2 = player2;
		activePlayer = this.player1;
		inactivePlayer = this.player2;
		
		board = new Board(boardSize);
	}

	public Player getPlayer1() {
		return player1;
	}
	
	public Player getPlayer2() {
		return player2;
	}

	public Player getActivePlayer()
	{
		return activePlayer;
	}
	
	private Player getActivePlayerInternal()
	{
		if (getTurn()>=3)
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
	
	public boolean doCommand(AbstractCommand cmd) throws Exception
	{
		AbstractMove move = null;
		switch (cmd.getType()) {
		case GAME_PLACE:
			PlaceCommand placeCmd = (PlaceCommand)cmd; 
			move = new Place(board, getActivePlayerInternal(),
					placeCmd.getX(), placeCmd.getY(), placeCmd.getPieceType());
			break;
		case GAME_MOVE:
			MoveCommand moveCmd = (MoveCommand)cmd;
			move = new Move(board, getActivePlayerInternal(), 
					moveCmd.getX(), moveCmd.getY(), moveCmd.getDirection(), moveCmd.getDrop());
			break;
		default:
			throw new Exception("Command invalid");
		}
		
		if (move!=null)
		{
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
			else if (flatCounters[1]>flatCounters[0])
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
