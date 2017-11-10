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
	TaskTypeEnum type;
	
	public UserTask(String name, String taskType, LocalDateTime endDateTime) {
		this.name = name;
		this.taskType = taskType;
		this.endDateTime = endDateTime;
		//Temp For Testing
		this.hoursTotalEstimate = 5;
		this.hoursLeft = hoursTotalEstimate;
		this.minTaskBlockHours = 0.5;
		this.maxTaskBlockHours = 2;
		this.minBreaktimeLengthHours = (int)(minTaskBlockHours + 1) * 5/60;
		this.actualMinTaskBlockHours = minTaskBlockHours + minBreaktimeLengthHours;
		this.maxBreaktimeLengthHours = (int)(maxTaskBlockHours + 1) * 5/60;
		this.maxBreaktimeLengthHours = this.maxBreaktimeLengthHours > 20/60 ? 20/60 : this.maxBreaktimeLengthHours;
		this.actualMaxTaskBlockHours = maxTaskBlockHours + maxBreaktimeLengthHours;
		type = TaskTypeEnum.HOMEWORK;
	}
	//to be implemented
	public boolean addSubTasks(ArrayList<SubTask> subTasks) {
		return true;
	}
}
