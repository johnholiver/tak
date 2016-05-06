package johnholiver.game;

import johnholiver.game.exceptions.OutOfStoneException;

public class Player {
	
	private int number;
	private String color;
	private int remainingCapstone = 0;
	private int remainingStone = 0;

	public Player(int number, String color, int remainingCapstone, int remainingStone) {
		this.number = number;
		this.color = color;
		this.remainingCapstone = remainingCapstone;
		this.remainingStone = remainingStone;
	}

	public int getNumber() {
		return number;
	}
	
	public String getColor() {
		return color;
	}

	public void decRemainingCapstone() throws OutOfStoneException {
		if (remainingCapstone > 0)
			remainingCapstone--;
		else
			throw new OutOfStoneException(this);
	}
	
	public void decRemainingStone() throws OutOfStoneException {
		if (remainingStone > 0)
			remainingStone--;
		else
			throw new OutOfStoneException(this);
	}
	
	public int getRemainingCapstone() {
		return remainingCapstone;
	}

	public int getRemainingStone() {
		return remainingStone;
	}
	
	public String toString() {
		return "[Player|"+number+","+color+"]";
	}
}
