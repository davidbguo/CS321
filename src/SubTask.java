import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class SubTask extends Event{

	protected LocalTime startTime;
	protected LocalTime endTime;
	protected double timeLength;
	protected DayOfWeek dayOfWeek;
	protected LocalDate day;
	protected String name;
	
	//constructor for usertask
	public SubTask(String name, LocalDate day, LocalTime start, LocalTime end) {
		this.name = name;
		this.day = day;
		this.dayOfWeek = this.day.getDayOfWeek();
		this.endTime = end;
		this.startTime = start;
		this.timeLength = start.until(end, ChronoUnit.HOURS);
	}
	
	//constructor for reoccuring pet
	public SubTask(String name, DayOfWeek dayOfWeek, LocalTime start, LocalTime end) {
		this.name = name;
		this.day = null;
		this.dayOfWeek = dayOfWeek;
		this.endTime = end;
		this.startTime = start;
		this.timeLength = start.until(end, ChronoUnit.HOURS);
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
			dayVal = "Recurring PET " + this.dayOfWeek;
		else
			dayVal = this.day.toString();
		
		return "\n[ " + name + "\t\t " + dayVal + ":\t " + startTime.toString() + " - " + endTime.toString() + " ]";
	}

}
