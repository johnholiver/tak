package johnholiver.game;

public class Player {
	
	private int number;
	
	private int remainingCapstone = 0;
	private int remainingStone = 0;

	public Player(int number, int remainingCapstone, int remainingStone) {
		this.number = number;
		this.remainingCapstone = remainingCapstone;
		this.remainingStone = remainingStone;
	}

	public int getNumber() {
		return number;
	}

	public int getRemainingCapstone() {
		return remainingCapstone;
	}

	public int getRemainingStone() {
		return remainingStone;
	}
}
