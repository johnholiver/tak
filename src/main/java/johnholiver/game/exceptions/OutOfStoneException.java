package johnholiver.game.exceptions;

import johnholiver.game.Player;

public class OutOfStoneException extends Exception {

	public OutOfStoneException(Player p) {
		super(p+" is out of a certain kind of stone ["+p.getRemainingCapstone()+","+p.getRemainingStone()+"]");
	}

}
