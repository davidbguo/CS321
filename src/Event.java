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
	public void addSubTasks(ArrayList<SubTask> subTasks) {
		breakdown.addAll(subTasks);
		Collections.sort(breakdown, new SortByDay());
	}
	
	
	public boolean equals(Event event) {
		return ((this.name.equals(event.name))&& (this.dateTimeCreated.equals(event.dateTimeCreated)));
	}
	
}
