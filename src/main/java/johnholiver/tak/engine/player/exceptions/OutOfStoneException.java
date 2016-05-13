package johnholiver.tak.engine.player.exceptions;

import johnholiver.tak.engine.player.Player;

@SuppressWarnings("serial")
public class OutOfStoneException extends Exception {

	public OutOfStoneException(Player p) {
		super(p+" is out of a certain kind of stone ["+p.getRemainingCapstone()+","+p.getRemainingStone()+"]");
	}

}
