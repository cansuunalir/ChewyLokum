package Model;

public class EmptyObject extends BoardObject {

	public EmptyObject(int row, int column) {
		super(row, column);
		super.setType(4);
		super.setColor(null);
	}

}
