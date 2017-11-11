	import java.time.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class ASDay {

	protected LinkedList<SubTask> eventsOfDay = new LinkedList<SubTask>();
	protected String[] details;
	protected LocalDate date;
	protected double hoursLeft;
	protected double hoursWorking;
    protected static final double MINIMUM_TASK_BLOCK_LENGTH = 0.25; //in hours
    
    public ASDay(LocalDate day, ArrayList<PreExistTask> preExistTasks) {
    	//System.out.println("inside asday constructor");
    	this.date = day;
    	hoursLeft = 24;
    	hoursWorking = 0;
    	eventsOfDay.addFirst(new SubTask("startOfDay", LocalTime.of(0, 0),LocalTime.of(0, 0)));
    	eventsOfDay.addLast(new SubTask("endOfDay", LocalTime.of(23, 59),LocalTime.of(23, 59)));
    	addPreExistTasks(preExistTasks);
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
    	//System.out.println(hoursLeft + "" + hoursWorking);
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

    //to be implemented
    //return a list of SubTask that weren't inserted
	public boolean insertSubTasks(ArrayList<SubTask> toBeAdded){
        boolean success = true;
        for (int i = 0; i < toBeAdded.size(); i++){
            success = success & insertSubTask(toBeAdded.get(i));        }
		return success;
	}
    
    public boolean insertSubTask(SubTask newTask){
        boolean success = false;
        for (int i = 0; i < (eventsOfDay.size()-1); i++){
            if ((eventsOfDay.get(i).endTime.isBefore(newTask.startTime) || eventsOfDay.get(i).endTime.equals(newTask.startTime) )
            		&& (newTask.endTime.isBefore(eventsOfDay.get(i+1).startTime)) || newTask.endTime.equals(eventsOfDay.get(i+1).startTime)){
                eventsOfDay.add(i + 1, newTask);
                success = true;
                break;
            }
        }
        return success;
        
    }
    
    private void addPreExistTasks(ArrayList<PreExistTask> preExistTasks) {
    	
    	for (int i = 0; i < preExistTasks.size(); i++) {
    		//System.out.println(preExistTasks.get(i).day);
    		if (preExistTasks.get(i).day.equals(this.date.getDayOfWeek())) {
    			SubTask temp = new SubTask(preExistTasks.get(i).name, preExistTasks.get(i).startTime, preExistTasks.get(i).endTime);
    			insertSubTask(temp);
    		}
    	}
    	
    }
    
    public String[] getDailyEventsDetails(){
    	return details;
    }
    
    public String[] getDailyEventsSummary(){
    	String[] detailSummary = new String[10]; //placeholder code
    	
    	return detailSummary;
    } 
    
    public String toString() {
    	return date.toString() + " " + date.getDayOfWeek().toString() + eventsOfDay.toString();
    }
}