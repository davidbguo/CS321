import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserTask extends Event {
	/* From Event.java
	 * protected int priority;
	protected LocalDateTime dateTimeCreated;
	protected String name;
	protected ArrayList<LocalDateTime> breakdown;
	protected LocalDateTime endDateTime;
	 */
	protected int userProficiency = 3;
	protected String taskType;
	protected double hoursLeft;
	protected double actualMinTaskBlockHours, actualMaxTaskBlockHours;
	protected double minTaskBlockHours, maxTaskBlockHours, minBreaktimeLengthHours, maxBreaktimeLengthHours;
	protected double hoursTotalEstimate, maxHoursPerDay;
	protected TaskTypeEnum type;
	
	public UserTask(String name, String taskType, LocalDateTime endDateTime, TaskTypeEnum type) {
		this.name = name;
		this.taskType = taskType;
		this.endDateTime = endDateTime;
		//Temp For Testing
		this.hoursTotalEstimate = 5.0;
		this.hoursLeft = hoursTotalEstimate;
		this.minTaskBlockHours = 0.5;
		this.maxTaskBlockHours = 2.0;
		this.minBreaktimeLengthHours = (double)((int)(minTaskBlockHours + 1)) * 5.0/60.0;
		this.actualMinTaskBlockHours = minTaskBlockHours + minBreaktimeLengthHours;
		this.maxBreaktimeLengthHours = (double)((int)(maxTaskBlockHours + 1)) * 5.0/60.0;
		this.maxBreaktimeLengthHours = this.maxBreaktimeLengthHours > 20.0/60.0 ? 20.0/60.0 : this.maxBreaktimeLengthHours;
		this.actualMaxTaskBlockHours = maxTaskBlockHours + maxBreaktimeLengthHours;
		//System.out.println(maxTaskBlockHours + " " + actualMaxTaskBlockHours);
		this.maxHoursPerDay = 8;
		this.type = type;
	}
	//to be implemented
	public boolean addSubTasks(ArrayList<SubTask> subTasks) {
		return true;
	}
}
