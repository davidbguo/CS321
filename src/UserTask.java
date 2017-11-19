import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class UserTask extends Event implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* From Event.java
	 * protected double priority;
	protected LocalDateTime dateTimeCreated;
	protected String name;
	protected ArrayList<LocalDateTime> breakdown;
	protected LocalDateTime endDateTime;
	 */
	protected LocalDateTime startDateTime;
	protected int userProficiency = 3;
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
		//Temp For Testings
		this.hoursTotalEstimate = 5.0;
		this.hoursLeft = hoursTotalEstimate;
		this.minTaskBlockHours = 0.5;
		this.maxTaskBlockHours = 2.0;
		this.minBreaktimeLengthHours = (double)((int)(minTaskBlockHours + 1)) * 5.0/60.0;
		this.actualMinTaskBlockHours = minTaskBlockHours + minBreaktimeLengthHours;
		this.maxBreaktimeLengthHours = (double)((int)(maxTaskBlockHours + 1)) * 5.0/60.0;
		this.maxBreaktimeLengthHours = this.maxBreaktimeLengthHours > 20.0/60.0 ? 20.0/60.0 : this.maxBreaktimeLengthHours;
		this.actualMaxTaskBlockHours = maxTaskBlockHours + maxBreaktimeLengthHours;
		this.maxHoursPerDay = 8;
		this.type = type;
		//
		setPriority();
	}
	//to be implemented
	public void addSubTasks(ArrayList<SubTask> subTasks) {
		//return true;
	}

	public String toString() {
		String format = "%-35s %-15s %-35s %-35s %-15s %-15s %-15s";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm MM-dd-yy");		
		return String.format(format, 
				this.name, 
				"Type: " + this.type, 
				"Start: " + this.startDateTime.format(formatter), 
				"End: " + this.endDateTime.format(formatter),
				"Hours Estimated: " + this.hoursTotalEstimate,
				"Hours Unscheduled: " + this.hoursLeft,
				"Priority: " + this.priority); 
	}

	public void setPriority() {
		double timeToWork = LocalDateTime.now().until(this.endDateTime, ChronoUnit.DAYS);
		//double avgHoursPerDay= this.hoursLeft/timeToWork;
		this.priority = timeToWork;


		
	}

}
