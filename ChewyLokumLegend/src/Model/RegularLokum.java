package Model;

public abstract class RegularLokum extends BoardObject {

	public RegularLokum(int row, int column) {
		super(row, column);
		super.setType(1);
	}
	
	public String toString(){
		return super.toString();
	}

}
