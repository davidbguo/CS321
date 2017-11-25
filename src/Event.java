import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/*
 * Abstract class template for PreExistTask and UserTask
 * Variables:
 * 			double priority
 * 			LocalDateTime dateTimeCreated, used as an unique ID
 * 			String name
 * 			ArrayList<SubTask> breakdown, list of all subtasks across various ASDays belonging to this Event
 * 			LocalDateTime endDateTime
 * Methods:
 * 			addSubTasks(ArrayList<SubTask>)
 * 			boolean equals(Event), checks name and dateTimeCreated to see if its the same object
 * For FrontEnd:
 * 			You shouldn't do anything with this abstract class for backend
 */

public abstract class Event implements java.io.Serializable {

	protected double priority;
	protected LocalDateTime dateTimeCreated;
	protected String name;
	protected ArrayList<SubTask> breakdown = new ArrayList<SubTask>();
	protected LocalDateTime endDateTime;
	
	//to be implemented
	public void addSubTasks(ArrayList<SubTask> subTasks) {
		breakdown.addAll(subTasks);
		Collections.sort(breakdown, new SortByDay());
	}
	
	
	public boolean equals(Event event) {
		return ((this.name.equals(event.name))&& (this.dateTimeCreated.equals(event.dateTimeCreated)));
	}
	
}
