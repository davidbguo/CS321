import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/*
 * UserTask is a new Event use wants us to schedule for him
 * Variables:
 * 			final long serialVersionUID, not used in final product?
 * 			double hoursLeft, hours still not assigned to a time slot before endDateTime
 * 			double actualMinTaskBlockHours, actualMaxTaskBlockHours, minTaskBlockHours, maxTaskBlockHours
 * 			double minBreaktimeLengthHours, maxBreaktimeLengthHours, hoursTotalEstimate, maxHoursPerDay
 * 				all used to created variable length time slots to fit into ASDays
 * 				implements a short break after each "study session". 5 minutes per hours of studying up to 20 minutes
 * 			TaskTypeEnum type, determines hoursTotalEstimate
 * Methods:
 * 			Constructor(String, LocalDateTime, LocalDateTime, TaskTypeEnum), all other vars are set up by computer
 * 				dateTimeCreated is set here for unique ID
 * 			setUpVars(), sets values of various variables based on basic input in constructor
 * 				maxHoursPerDay is default 4 hours but if the task has a lot of work and little time before due date, max hours per day is higher
 * 			String toString(), prints name, type, startDateTime, endDateTime, hours estimated, hoursLeft, priority
 * 			setPriority(), sets priority based on number of days left before dueDate
 * For FrontEnd:
 * 			You might use toString. If you need to created an UserTask, do it through LogicLayer methods. 
 */



public class UserTask extends Event implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	protected LocalDateTime startDateTime;
	protected double hoursLeft;
	protected double actualMinTaskBlockHours, actualMaxTaskBlockHours;
	protected double minTaskBlockHours, maxTaskBlockHours, minBreaktimeLengthHours, maxBreaktimeLengthHours;
	protected double hoursTotalEstimate, maxHoursPerDay;
	protected TaskTypeEnum type;
	
	public UserTask(String name, LocalDateTime startDateTime, LocalDateTime endDateTime, TaskTypeEnum type) {
		this.name = name;
		this.endDateTime = endDateTime;
		this.startDateTime = startDateTime;
		this.priority = 200;
		this.dateTimeCreated = LocalDateTime.now();
		this.type = type;
		setUpVars();
		setPriority();
	}
	
	public void setUpVars() {
		this.hoursTotalEstimate = type.getHourRequired();
		this.hoursLeft = hoursTotalEstimate;
		this.minTaskBlockHours = 0.5;
		this.maxTaskBlockHours = 2.0;
		this.minBreaktimeLengthHours = (double)((int)(minTaskBlockHours + 1)) * 5.0/60.0;
		this.actualMinTaskBlockHours = minTaskBlockHours + minBreaktimeLengthHours;
		this.maxBreaktimeLengthHours = (double)((int)(maxTaskBlockHours + 1)) * 5.0/60.0;
		this.maxBreaktimeLengthHours = this.maxBreaktimeLengthHours > 20.0/60.0 ? 20.0/60.0 : this.maxBreaktimeLengthHours;
		this.actualMaxTaskBlockHours = maxTaskBlockHours + maxBreaktimeLengthHours;
		double avgHoursPerDay = hoursTotalEstimate/this.startDateTime.until(endDateTime, ChronoUnit.DAYS);
		this.maxHoursPerDay = 4 > avgHoursPerDay ? 4 : avgHoursPerDay;
		
	}
	
	@Override
	public String toString() {
		String format = "%-30s\t\t%-30s\t\t%-30s\t\t%-30s\t\t%-30s\t\t%-30s\t\t%-30s";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm MM-dd-yy");		
		return String.format(format, 
				this.name, 
				"Type: " + this.type, 
				"Start: " + this.startDateTime.format(formatter), 
				"End: " + this.endDateTime.format(formatter),
				"Hours Estimated: " + String.format("%.2f", this.hoursTotalEstimate),
				"Hours Unscheduled: " + String.format("%.2f", this.hoursLeft),
				"Priority: " + String.format("%.2f", this.priority)); 
	}

	public void setPriority() {
		double timeToWork = LocalDateTime.now().until(this.endDateTime, ChronoUnit.DAYS);
		this.priority = timeToWork+1;
	}

}
