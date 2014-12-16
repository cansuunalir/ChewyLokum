package Model;

public abstract class SpecialLokum extends BoardObject{
	
	private int specialType;

	public SpecialLokum(int row, int column) {
		super(row, column);
		super.setType(2);
	}

	public int getSpecialType() {
		return specialType;
	}

	public void setSpecialType(int specialType) {
		this.specialType = specialType;
	}

}
