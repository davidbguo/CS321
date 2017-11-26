import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


/*
 * PreExistTask is recurring activity user already knows he has
 * Variables:
 * 			final long serialVersionUID, probably not used in final product
 * 			DayOfWeek day
 * 			LocalTime startTime
 * 			LocalTime endtime, together with startTime and day they define the time slot this activity takes
 * Methods:
 * 			Constructor(String, DayofWeek, LocalTime, LocalTime, LocalDateTime)
 * 				creates one single subtask to fill the breakdown[]
 * 				endDateTime is null and means never ending
 * 				priority is also 0, so highest order priority
 *  		String toString, prints name, day, startTime, endTime
 *  For FrontEnd:
 *  		You might access toString. For constructing needs, refer to LogicLayer methods instead of creating one directly
 */

public class PreExistTask extends Event implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

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
		SubTask slot = new SubTask(name, day, start, end, dateTimeCreated);
		breakdown.add(slot);
	}
	
	public String toString(){
		String format = "%-20s\t\t %-20s\t\t %-20s\t\t %-20s";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");		
		return String.format(format, 
				this.name, 
				"Recurring: " + this.day, 
				"Start: " + this.startTime.format(formatter), 
				"End: " + this.endTime.format(formatter)); 

	}
	

}
