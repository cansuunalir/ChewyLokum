package Model;

public abstract class BoardObject {
	
	private Integer row;
	private Integer column;
	private Integer type;
	private Integer color;
	
	public BoardObject(int row, int column){
		this.row = row;
		this.column = column;
	}
	
	public Integer getColor() {
		return color;
	}

	public void setColor(Integer color) {
		this.color = color;
	}
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public String toString(){
		return "Row:" + row + " Column:" + column + " Type: " + type + "Color: " + color;
	}
	
	public boolean repOK(){
		if(row == null)
			return false;
		if(column == null)
			return false;
		if(type == null)
			return false;
		return true;
	}

}
