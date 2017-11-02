import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ASDay {
	protected SubTask[] allTasks;
	protected LinkedList<SubTask> eventsOfDay = new LinkedList<SubTask>();
	protected String[] details;
	protected LocalDate date;
	protected double hoursLeft;
	protected double hoursWorking;
    
    public ASDay(LocalDate day) {
    	this.date = day;
    	hoursLeft = 24;
    	hoursWorking = 0;
    	eventsOfDay.addFirst(new SubTask("startOfDay", LocalTime.of(0, 0),LocalTime.of(0, 0)));
    	eventsOfDay.addLast(new SubTask("endofDay", LocalTime.of(23, 59),LocalTime.of(23, 59)));
    	updateHoursLeft();
    	
    }
    
    public void updateHoursLeft() {
    	double tempHoursLeft = 0;
    	double tempHoursWorking = 0;
    	
    	LocalTime tempEnd = eventsOfDay.getFirst().endTime;
    	LocalTime tempStart = eventsOfDay.getFirst().endTime;
    	for(int i = 1; i< eventsOfDay.size(); i++) {
    		tempStart = eventsOfDay.get(i).startTime;
    		tempHoursLeft += tempEnd.until(tempStart, ChronoUnit.HOURS);
    		tempHoursWorking += eventsOfDay.get(i).timeLength;
    		tempEnd = eventsOfDay.get(i).endTime;
    	}
    	hoursLeft = tempHoursLeft;
    	hoursWorking = tempHoursWorking;
    }
    
    public String[] getDailyEventsDetails(){
    	return details;
    }
    
    public String[] getDailyEventsSummary(){
    	String[] detailSummary = new String[10]; //placeholder code
    	
    	return detailSummary;
    } 
    
}
