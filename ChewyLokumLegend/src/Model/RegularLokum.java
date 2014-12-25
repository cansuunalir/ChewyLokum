package Model;

public abstract class RegularLokum extends BoardObject {
	
	private boolean timerLokum;

	public RegularLokum(int row, int column) {
		super(row, column);
		super.setType(1);
		setTimerLokum(false);
	}
	
	public boolean isTimerLokum() {
		return timerLokum;
	}


	public void setTimerLokum(boolean timerLokum) {
		this.timerLokum = timerLokum;
	}


	public String toString(){
		return super.toString();
	}

}
