package Model;

public class TimerLokum extends RegularLokum {

	public TimerLokum(int row, int column, int color) {
		super(row, column);
		super.setColor(color);
		super.setTimerLokum(true);
	}
	
}
