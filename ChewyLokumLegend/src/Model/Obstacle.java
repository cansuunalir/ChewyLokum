package Model;

public class Obstacle extends BoardObject {

	public Obstacle(int row, int column) {
		super(row, column);
		super.setType(3);
		super.setColor(7);
	}

}
