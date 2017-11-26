import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/*
 * SubTask represents a single slot of time during the day assigned to a specify task
 * Variables:
 * 			LocalTime starTime
 * 			LocalTime endTime, together with day and startTime, uniquely identifies a time slot
 * 			LocalDate day
 * 			DayOfWeek dayOfWeek
 * 			double timeLength, time between startTime and endTime in hours
 * 			String name, to connect it to a specific Event
 * 			LocalDateTime dateCreated, link uniqueID of an Event to newly created SubTask
 * Methods
 * 			Constructor(String, LocalDate, LocalTime, LocalTimee, LocalDateTime)
 * 				for UserTask type Event object
 * 			Constructor(String DayOfWeek, LocalTime, LocalTime, LocalDateTime)
 * 				for PreExistTask type of Event object
 * 			printString(), old method used in testing of LogicLayer
 * 			String toString(), prints name, day/dayOfWeek (depending on UserTask or PET), start, end, and timeLength
 * For FrontEnd:
 * 			ignore printString, you only might use toString. Constructors are for LogicLayer
 */


public class SubTask extends Event{

	protected LocalTime startTime;
	protected LocalTime endTime;
	protected double timeLength;
	protected DayOfWeek dayOfWeek;
	protected LocalDate day;
	protected String name;
	protected LocalDateTime dateCreated;

	//constructor for usertask
	public SubTask(String name, LocalDate day, LocalTime start, LocalTime end, LocalDateTime dateCreated) {
		this.name = name;
		this.day = day;
		this.dayOfWeek = this.day.getDayOfWeek();
		this.endTime = end;
		this.startTime = start;
		this.timeLength = start.until(end, ChronoUnit.MINUTES)/60.0;
		this.dateCreated = dateCreated;
	}
	
	//constructor for reoccuring pet
	public SubTask(String name, DayOfWeek dayOfWeek, LocalTime start, LocalTime end, LocalDateTime dateCreated) {
		this.name = name;
		this.day = null;
		this.dayOfWeek = dayOfWeek;
		this.endTime = end;
		this.startTime = start;
		this.timeLength = start.until(end, ChronoUnit.MINUTES)/60.0;
		this.dateCreated = dateCreated;
	}
	
	public void printString() {
		String format = "%-35s %-35s %-35s \n";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yy");
		String dayVal = "";
		if (this.day == null)
			dayVal = "Recurring PET: " + this.dayOfWeek;
		else
			dayVal = this.day.format(formatter);
		
		System.out.printf(format, "[ " + this.name, "Date: " + dayVal, "Time Slot: " + startTime + " - " + endTime + "]");
		//return "\n[ " + this.name + " \t\t Created on: " + this.dateTimeCreated + "\t End Date: " + this.endDateTime + "\t\t Priority: " + this.priority + "]"; 
	}
	
	@Override
	public String toString() {
		String dayVal = "";
		if (this.day == null)
			dayVal = "Recurring PET: " + this.dayOfWeek;
		else
			dayVal = "Date: " + this.day.toString();
		String format = "%-30s\t\t%-20s\t%-20s\t%-20s";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");		
		return String.format(format, 
				this.name, 
				 
				"Start: " + this.startTime.format(formatter), 
				"End: " + this.endTime.format(formatter),
				"Time Length (Hr): " + String.format("%.2f", this.timeLength));
	}

}
