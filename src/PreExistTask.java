import java.time.*;
import java.util.ArrayList;

public class PreExistTask extends Event {
	/* From Event.java
	 * protected int priority;
	protected LocalDateTime dateTimeCreated;
	protected String name;
	protected ArrayList<LocalDateTime> breakdown;
	protected LocalDateTime endDateTime;
	 */
	protected DayOfWeek day;
	protected LocalTime startTime;
	protected LocalTime endTime;
	
	public PreExistTask(String name, DayOfWeek day, LocalTime start, LocalTime end) {
		this.name = name;	
		this.priority = 1;
		this.dateTimeCreated = LocalDateTime.now();
		this.day = day;
		this.startTime = start;
		this.endTime = end;
	}
}
