package Model;

public class StripedLokum extends SpecialLokum {
	
	private boolean orientation;

	public StripedLokum(int row, int column, int color, boolean orientation) {
		super(row, column);
		this.orientation = orientation;
		super.setSpecialType(0);
		super.setColor(color);
	}

	public boolean isOrientation() {
		return orientation;
	}

	public void setOrientation(boolean orientation) {
		this.orientation = orientation;
	}
	
	public String toString(){
		return super.toString() + " Orientation:" + orientation;
	}
	

}
