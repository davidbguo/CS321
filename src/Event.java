import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public abstract class Event implements java.io.Serializable {

	protected double priority;
	protected LocalDateTime dateTimeCreated;
	protected String name;
	protected ArrayList<SubTask> breakdown = new ArrayList<SubTask>();
	protected LocalDateTime endDateTime;
	
	//to be implemented
	public boolean addSubTasks(ArrayList<SubTask> subTasks) {
		breakdown.addAll(subTasks);
		Collections.sort(breakdown, new SortByDay());
	}
	
	public void printString() {
		String format = "%-35s %-35s %-35s %-35s \n";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm MM-dd-yy");
		String endDateVal = "";
		if (this.endDateTime == null)
			endDateVal = "Never";
		else
			endDateVal = this.endDateTime.format(formatter);
		
		System.out.printf(format, "[ " + this.name, "Created on: " + this.dateTimeCreated.format(formatter), "End Date: " + endDateVal, "Priority: " + this.priority + "]");
		//return "\n[ " + this.name + " \t\t Created on: " + this.dateTimeCreated + "\t End Date: " + this.endDateTime + "\t\t Priority: " + this.priority + "]"; 
	}
	
}
