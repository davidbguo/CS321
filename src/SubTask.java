import java.time.*;
import java.time.temporal.ChronoUnit;

public class SubTask extends Event{

	protected LocalTime startTime;
	protected LocalTime endTime;
	protected double timeLength;
	
	//constructor
	public SubTask(String name, LocalTime start, LocalTime end) {
		this.name = name;
		this.endTime = end;
		this.startTime = start;
		this.timeLength = start.until(end, ChronoUnit.HOURS);
	}

}
