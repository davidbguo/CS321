import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

/*
 * Our own version of a Day object.
 * Variables: 
 * 			LinkedList of Subtasks for the day.
 * 			LocalDate date
 * 			double hoursLeft open in the day
 * 			double hoursWorking, total length of all subtasks assigned for the day
 * 			final double Mininum-task-block-length, minimum manageable time slot is set to 15 minutes
 * Method:
 * 			Constructor(LocalDate, ArrayList<PreExistTask>), for LogicLayer use
 * 			updateHoursLeft(), for LogicLayer use
 * 			ArrayList<LocalTime> getOpenTimeSlots(), for LogicLayer use
 * 			boolean insertSubTasks(ArrayList<SubTask>), for LogicLayer use
 * 			boolean insertSubTask(SubTask), singuler for logiclayer use
 * 			addPreeExistTasks(ArrayList<PreExistTask>), for logiclayer use
 * 			int getKey() returns key value for View.java JTable as [YEAR][MONTH][DAY]
 * 			String toString(), Prints the date and then loops through toString of all subTasks in eventsOfDay
 * For FrontEnd:
 * 			You can access all variable directly because nothing in private.
 * 			You might want to use eventsOfDay, date, hoursLeft, hoursWorking, getKey(), and toString()
 * 
 */

public class ASDay implements java.io.Serializable{

	protected LinkedList<SubTask> eventsOfDay = new LinkedList<SubTask>(); //ASDay is comprised of SubTask
	protected LocalDate date;		//The date of the Day
	protected double hoursLeft;		//Hours available in the Day
	protected double hoursWorking;	//Hours working for the Day
    protected static final double MINIMUM_TASK_BLOCK_LENGTH = 0.25; //in hours

	private static final long serialVersionUID = 1L;
	
    public ASDay(LocalDate day, ArrayList<PreExistTask> preExistTasks) {
    	this.date = day;
    	hoursLeft = 24;
    	hoursWorking = 0;

    	eventsOfDay.addFirst(new SubTask("startOfDay", day, LocalTime.of(0, 0),LocalTime.of(0, 0), LocalDateTime.of(0, 1, 1, 0, 0)));
    	eventsOfDay.addLast(new SubTask("endOfDay", day, LocalTime.of(23, 59),LocalTime.of(23, 59), LocalDateTime.of(0, 1, 1, 0, 0)));

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
    
    //also use this to re add all PETs after user deletes one from the day
    public void addPreExistTasks(ArrayList<PreExistTask> preExistTasks) {
    	
    	for (int i = 0; i < preExistTasks.size(); i++) {
    		if (preExistTasks.get(i).day.equals(this.date.getDayOfWeek())) {
    			SubTask temp = new SubTask(preExistTasks.get(i).name, preExistTasks.get(i).day, preExistTasks.get(i).startTime, preExistTasks.get(i).endTime, preExistTasks.get(i).dateTimeCreated);
    			insertSubTask(temp);
    		}
    	}
    	
    }
     
    public int getKey(){	//Returns a Key value as [YEAR][MONTH][DAY]
    	int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        int key = (year * 10000) + (month * 100) + day;
    		
    	return key;
    }
    
    public String toString() {
    	String retVal = date + "\n";
    	for (int i = 1; i < eventsOfDay.size()-1; i ++) {
    		retVal += eventsOfDay.get(i).toString() + "\n";
    	}
    	return retVal;
    }
}