import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PreExistTask extends Event implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* From Event.javas
	 * protected double priority;
	protected LocalDateTime dateTimeCreated;
	protected String name;
	protected ArrayList<LocalDateTime> breakdown;
	protected LocalDateTime endDateTime;
	 */
	protected DayOfWeek day;
	protected LocalTime startTime;
	protected LocalTime endTime;
	
	public PreExistTask(String name, DayOfWeek day, LocalTime start, LocalTime end, LocalDateTime endDateTime) {
		this.name = name;	
		this.priority = 0;
		this.dateTimeCreated = LocalDateTime.now();
		this.day = day;
		this.startTime = start;
		this.endTime = end;
		this.endDateTime = endDateTime;
	}
	
	public String toString(){
		String format = "%-35s %-25s %-35s %-35s";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");		
		return String.format(format, 
				this.name, 
				"Recurring: " + this.day, 
				"Start: " + this.startTime.format(formatter), 
				"End: " + this.endTime.format(formatter)); 

	}
	

}
