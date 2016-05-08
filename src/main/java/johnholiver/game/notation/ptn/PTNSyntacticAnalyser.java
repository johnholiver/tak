package johnholiver.game.notation.ptn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import johnholiver.game.command.AbstractCommand;
import johnholiver.game.command.InfoCommand;
import johnholiver.game.command.MoveCommand;
import johnholiver.game.command.PlaceCommand;
import johnholiver.game.notation.exception.SyntacticException;
import johnholiver.game.notation.ptn.token.CountToken;
import johnholiver.game.notation.ptn.token.DirectionToken;
import johnholiver.game.notation.ptn.token.SquareToken;
import johnholiver.game.notation.ptn.token.StoneToken;
import johnholiver.game.notation.ptn.token.AbstractToken;
import johnholiver.game.notation.ptn.token.AbstractToken.TokenType;

public class PTNSyntacticAnalyser {

	private List<AbstractToken> analizedTokenList;
	private int i;
	private AbstractToken lookAhead;
	private List<AbstractCommand> commands;
	
	public List<AbstractCommand> analyze(List<AbstractToken> tokenList) throws SyntacticException {
		commands = new ArrayList<AbstractCommand>();
		this.analizedTokenList = tokenList;
		i = 0;
		lookAhead = analizedTokenList.get(i);
		initialState();
		return commands;
	}
	
	private void initialState() throws SyntacticException {
		if (lookAhead == null)
			throw new SyntacticException(analizedTokenList, i);
		switch (lookAhead.getType()) {
		case STONE:
			stone();
			break;
		case SQUARE:
			square();
			break;
		case COUNT:
			count();
			break;
		default:
			throw new SyntacticException(analizedTokenList, i);
		}
	}

	private void updateLookAhead() {
		i++;
		try {
			lookAhead = analizedTokenList.get(i);
		} catch (Exception e) {
			lookAhead = null;
		}
	}
	
	private void stone() throws SyntacticException {
		updateLookAhead();
		if (lookAhead == null)
			throw new SyntacticException(analizedTokenList, i);
		switch (lookAhead.getType()) {
		case SQUARE:
			stone_square();
			break;
		default:
			throw new SyntacticException(analizedTokenList, i);
		}
	}


	private void stone_square() throws SyntacticException {
		updateLookAhead();
		buildPlaceCommand();
		markInitialState();
	}
	
	private void square() throws SyntacticException {
		updateLookAhead();
		if (lookAhead != null && lookAhead.getType()==TokenType.DIRECTION)
			count_square_direction();
		else {
			analizedTokenList = fixPlace(analizedTokenList);
			buildPlaceCommand();
			markInitialState();
		} 
	}
	
	private void count() throws SyntacticException {
		updateLookAhead();
		if (lookAhead == null)
			throw new SyntacticException(analizedTokenList, i);
		switch (lookAhead.getType()) {
		case SQUARE:
			count_square();
			break;
		default:
			throw new SyntacticException(analizedTokenList, i);
		}
	}
	
	private void count_square() throws SyntacticException {
		updateLookAhead();
		if (lookAhead == null)
			throw new SyntacticException(analizedTokenList, i);
		switch (lookAhead.getType()) {
		case DIRECTION:
			count_square_direction();
			break;
		default:
			throw new SyntacticException(analizedTokenList, i);
		}
	}
	
	private void count_square_direction() throws SyntacticException {
		updateLookAhead();
		if (lookAhead != null && lookAhead.getType()==TokenType.COUNT)
			count_square_direction_count();
		else {
			analizedTokenList = fixMove(analizedTokenList);
			buildMoveCommand();
			markInitialState();
		}
	}
	
	private void count_square_direction_count() throws SyntacticException {
		updateLookAhead();
		if (lookAhead != null && lookAhead.getType()==TokenType.STONE)
			count_square_direction_count_stone();
		else {
			analizedTokenList = fixMove(analizedTokenList);
			buildMoveCommand();
			markInitialState();
		}
	}
	
	private void count_square_direction_count_stone() throws SyntacticException {
		updateLookAhead();
		buildMoveCommand();
		markInitialState();
	}

	private List<AbstractToken> fixPlace(List<AbstractToken> tokenList)
	{
		List<AbstractToken> fixedTokenList = new ArrayList<AbstractToken>();
		fixedTokenList.add(new StoneToken('F'));
		fixedTokenList.addAll(tokenList);
		return fixedTokenList;
	}

	private void buildPlaceCommand() {
		SquareToken square = (SquareToken)analizedTokenList.get(i-1);
		StoneToken stone = (StoneToken)analizedTokenList.get(i-2);
		commands.add(new PlaceCommand(square.getX(), square.getY(), stone.getValue()));
	}
	
	private List<AbstractToken> fixMove(List<AbstractToken> tokenList)
	{
		List<AbstractToken> fixedTokenList = new ArrayList<AbstractToken>();
		if (tokenList.get(0).getType()==TokenType.SQUARE)
			fixedTokenList.add(new CountToken('1'));
		fixedTokenList.addAll(tokenList);
		if (fixedTokenList.get(fixedTokenList.size()-1).getType()==TokenType.DIRECTION)
		{
			fixedTokenList.add(new CountToken('0'));
			CountToken first = (CountToken)fixedTokenList.get(0);
			fixedTokenList.add(new CountToken((char)first.getValue()));
		}
		if (fixedTokenList.get(fixedTokenList.size()-1).getType()==TokenType.COUNT)
		{
			fixedTokenList.add(new StoneToken('F'));
		}
		return fixedTokenList;
	}

	@SuppressWarnings("unused")
	private void buildMoveCommand() {
		StoneToken stone = (StoneToken)analizedTokenList.get(i-1);
		List<Integer> dropOrdered = new ArrayList<Integer>();
		CountToken drop;
		int dropSize = 0;
		while (analizedTokenList.get(i-2-dropSize).getType()==TokenType.COUNT)
		{
			drop = (CountToken)analizedTokenList.get(i-2-dropSize);
			dropOrdered.add(0,drop.getValue());
			dropSize++;
		}
		DirectionToken direction = (DirectionToken)analizedTokenList.get(i-2-dropSize);
		SquareToken square = (SquareToken)analizedTokenList.get(i-3-dropSize);
		CountToken count = (CountToken)analizedTokenList.get(i-4-dropSize);
		
		commands.add(new MoveCommand(square.getX(), square.getY(), direction.getValue(), dropOrdered));
	}
	
	private void markInitialState() throws SyntacticException {
		if (lookAhead != null)
		{
			switch (lookAhead.getType()) {
			case MARK:
				switch (lookAhead.getContent()) {
				case "'":
					tak();
					break;
				case "!":
					exclamationMark();
					break;
				case "?":
					interrogationMark();
					break;
				default:
					throw new SyntacticException(analizedTokenList, i);
				}
				break;
			case COMMENT:
				comment();
				break;
			default:
				throw new SyntacticException(analizedTokenList, i);
			}
		}
	}

	private void tak() throws SyntacticException {
		commands.add(new InfoCommand(lookAhead.getContent()));
		updateLookAhead();
		if (lookAhead != null)
		{
			switch (lookAhead.getType()) {
			case MARK:
				switch (lookAhead.getContent()) {
				case "!":
					exclamationMark();
					break;
				case "?":
					interrogationMark();
					break;
				default:
					throw new SyntacticException(analizedTokenList, i);
				}
				break;
			case COMMENT:
				comment();
				break;
			default:
				throw new SyntacticException(analizedTokenList, i);
			}
		}
	}

	private void exclamationMark() throws SyntacticException {
		commands.add(new InfoCommand(lookAhead.getContent()));
		updateLookAhead();
		if (lookAhead != null)
		{
			switch (lookAhead.getType()) {
			case MARK:
				switch (lookAhead.getContent()) {
				case "?":
					interrogationMark();
					break;
				default:
					throw new SyntacticException(analizedTokenList, i);
				}
				break;
			case COMMENT:
				comment();
				break;
			default:
				throw new SyntacticException(analizedTokenList, i);
			}
		}
	}

	private void interrogationMark() throws SyntacticException {
		commands.add(new InfoCommand(lookAhead.getContent()));
		updateLookAhead();
		if (lookAhead != null)
		{
			switch (lookAhead.getType()) {
			case COMMENT:
				comment();
				break;
			default:
				throw new SyntacticException(analizedTokenList, i);
			}
		}
	}

	private void comment() throws SyntacticException {
		commands.add(new InfoCommand(lookAhead.getContent()));
		updateLookAhead();
		if (lookAhead != null)
			throw new SyntacticException(analizedTokenList, i);
	}

}
