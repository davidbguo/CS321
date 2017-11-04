import java.time.*;
import java.time.LocalDate; 
import java.time.temporal.ChronoUnit;
import java.util.*;


public class ASDay {
	//Testing of ASDay
	public static void main(String args[]){
		
		LocalDate day = LocalDate.of(2015, 12, 31);
		ASDay test = new ASDay(day);
		SubTask	s1 = new SubTask("test",LocalTime.of(13, 0),LocalTime.of(14,00));
		SubTask	s2 = new SubTask("test",LocalTime.of(14, 15),LocalTime.of(15,00));
			/*
			SubTask	s3 = new SubTask("test",LocalTime.of(13, 0),LocalTime.of(14,00));
			SubTask	s4 = new SubTask("test",LocalTime.of(13, 0),LocalTime.of(14,00));
			SubTask	s5 = new SubTask("test",LocalTime.of(13, 0),LocalTime.of(14,00));
		*/
		test.eventsOfDay.add(s1);
		test.eventsOfDay.add(s2);
		System.out.print(test.getOpenTimeSlot());
	}

	protected SubTask[] allTasks;
	protected LinkedList<SubTask> eventsOfDay = new LinkedList<SubTask>();
	protected String[] details;
	protected LocalDate date;
	protected double hoursLeft;
	protected double hoursWorking;
    protected static final double MINIMUM_TASK_BLOCK_LENGTH = 0.25; //in hours
    
    public ASDay(LocalDate day) {
    	this.date = day;
    	hoursLeft = 24;
    	hoursWorking = 0;
    	eventsOfDay.addFirst(new SubTask("startOfDay", LocalTime.of(0, 0),LocalTime.of(0, 0)));
    	eventsOfDay.addLast(new SubTask("endOfDay", LocalTime.of(23, 59),LocalTime.of(23, 59)));
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

    public ArrayList<LocalTime> getOpenTimeSlot(){
        ArrayList<LocalTime> openTimeSlot= new ArrayList<LocalTime>();
        LocalTime tempEnd = eventsOfDay.getFirst().endTime;
    	LocalTime tempStart = eventsOfDay.get(1).startTime;
    	for(int i = 1; i< eventsOfDay.size(); i++){ 
            if(tempEnd.until(tempStart,ChronoUnit.HOURS)>MINIMUM_TASK_BLOCK_LENGTH) {
                //if there is more than 15 mins left 
                openTimeSlot.add(tempEnd);
                openTimeSlot.add(tempStart);
            }
            if(eventsOfDay.get(i).name.equals("endOfDay"))  
                break;
            tempEnd = eventsOfDay.get(i).endTime;
            tempStart = eventsOfDay.get(i+1).startTime;
    	}      
        return openTimeSlot;        
    }

	public boolean insertSubTasks(ArrayList<SubTask> toBeAdded){
		return true;
	}
    public String[] getDailyEventsDetails(){
    	return details;
    }
    
    public String[] getDailyEventsSummary(){
    	String[] detailSummary = new String[10]; //placeholder code
    	
    	return detailSummary;
    } 
    
}
