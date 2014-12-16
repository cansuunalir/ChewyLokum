package Model;

public class WrappedLokum extends SpecialLokum {

	public WrappedLokum(int row, int column, int color) {
		super(row, column);
		super.setSpecialType(1);
		super.setColor(color);
	}

}
