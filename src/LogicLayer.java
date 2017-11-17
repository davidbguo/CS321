import java.time.*;
import java.time.temporal.*;
import java.util.*;

public class LogicLayer {
	protected DataStorage data = new DataStorage();
	protected ArrayList<ASDay> currentDays = new ArrayList<ASDay>();

	//constructor
	public LogicLayer(){
		//temp data
	}
	public int callDeletePreExistTask(PreExistTask task){
		for(int i = 0; i < data.preExistTaskList.size(); i++){
			if(task == data.preExistTaskList.get(i)){
				return data.deletePreExistTask(i);
			}	
		}
		return -1;
	}
	public int callDeleteUserTask(UserTask task){
		for(int i = 0; i < data.priorityUserTaskList.size(); i++){
			if(task == data.priorityUserTaskList.get(i)){
				return data.deleteUserTask(i);
			}	
		}
		return -1;
	}
	public void callEditPreExistTask(PreExistTask task,PreExistTask newTask){
		int i = callDeletePreExistTask(newTask);
		data.editPreExistTask(i, newTask);
	}
	public void callEditUserTask(UserTask task,UserTask newTask){
		int i = callDeleteUserTask(newTask);
		data.editUserTask(i, newTask);
	}
	
	public void setCurrentDays() {
		System.out.println("inside setcurrentdays");
		currentDays.clear();
		LocalDate semStart = LocalDate.of(Integer.parseInt(data.userData.get("semStartYear")), Integer.parseInt(data.userData.get("semStartMonth")), Integer.parseInt(data.userData.get("semStartDay")));
		LocalDate semEnd = LocalDate.of(Integer.parseInt(data.userData.get("semEndYear")), Integer.parseInt(data.userData.get("semEndMonth")), Integer.parseInt(data.userData.get("semEndDay")));
		LocalDate current = semStart;
		while (!current.equals(semEnd)) {
			//System.out.println(current);
			ASDay newTemp = new ASDay(current, data.preExistTaskList);
			currentDays.add(newTemp);
			current = current.plusDays(1);
		}
	}
	
	//use comparator for usertask
	public void prioritization(){
		Collections.sort(data.priorityUserTaskList, new SortByPriority());
	}

	public void conflictDetection(){}

	public void createBreakdown(){
		System.out.println("inside createBreakdown");
		ArrayList<UserTask> tasksByPriority = data.priorityUserTaskList;
		int daysDiff = (int) currentDays.get(0).date.until(LocalDate.now(), ChronoUnit.DAYS);
		int currDayCounter = daysDiff;
		//System.out.println(tasksByPriority.size());
		for (int i = 0 ; i < tasksByPriority.size(); i++) {
			//System.out.println("inside " + currentDays.get(currDayCounter).date);
			currDayCounter = daysDiff;
			while(tasksByPriority.get(i).hoursLeft > 0) {
				while((currentDays.get(currDayCounter).hoursLeft) < 0.01 && (currDayCounter < currentDays.size())) {
					//System.out.println(currDayCounter);
					currDayCounter++;
				}
				breakdownForSingleDay(currentDays.get(currDayCounter), tasksByPriority.get(i));
				currDayCounter++;
			}
		}
	}

	//helper function
	private double findTaskBreakLength(double timeSlotLength) {
		double breakLength = 5.0/60.0;
		if (timeSlotLength <= (1.0+5.0/60.0)) {}
		else if (timeSlotLength <= (2+10.0/60)) {
			breakLength = 10.0/60.0;
		} else if (timeSlotLength <= (3+15.0/60.0)) {
			breakLength = 15.0/60.0;
		} else {
			breakLength = 20.0/60.0;
		}
		return breakLength;
	}


	//so far works, need more restraints
	public void breakdownForSingleDay(ASDay day, UserTask task){
		ArrayList<LocalTime> openSlots = day.getOpenTimeSlot();
		double hoursAssigned = task.hoursLeft > task.maxHoursPerDay ? task.maxHoursPerDay : task.hoursLeft;
		double hoursLeftForDay = hoursAssigned;
		ArrayList<SubTask> subTasksToBeAdded = new ArrayList<SubTask>();
		int counter = 0;
		int taskPartCounter = 1;
		while (counter < openSlots.size() && hoursLeftForDay > 0){
				//check length of next open slot
			double tempTimeLength = (openSlots.get(counter).until(openSlots.get(counter+1), ChronoUnit.MINUTES))/60.0;
			
				//if next open slot is more than actual min task block hours, do stuff
			if (tempTimeLength >= task.actualMinTaskBlockHours){
					//temp new timeslot time length is either actualmaxtaskblock hours or length of open slot
				double tempNewTimeSlotLength = (tempTimeLength >= task.actualMaxTaskBlockHours) ? task.actualMaxTaskBlockHours : tempTimeLength;
					//find new temp tasklength based on timeslot - break
				double tempTaskBreakLength = findTaskBreakLength(tempNewTimeSlotLength);
				double tempNewTaskTimeLength = tempNewTimeSlotLength - tempTaskBreakLength;
					//temptasklength if either hours left or temptasklength
				tempNewTaskTimeLength = tempNewTaskTimeLength > hoursLeftForDay ? hoursLeftForDay : tempNewTaskTimeLength;
					//reduced hours left
				hoursLeftForDay = hoursLeftForDay - tempNewTaskTimeLength;				
					//create LocalTime start end variables to creating new subtasks
				LocalTime tempTaskStart = openSlots.get(counter);
				LocalTime tempTaskEnd = (openSlots.get(counter).plus((long)((tempNewTaskTimeLength*60)), ChronoUnit.MINUTES));
				LocalTime tempTaskBreakEnd = tempTaskEnd.plus((long)(tempTaskBreakLength*60), ChronoUnit.MINUTES);
				String tempTaskName = task.name + " P" + (taskPartCounter++);
				SubTask tempTask = new SubTask(tempTaskName, day.date, tempTaskStart, tempTaskEnd);
				String tempTaskBreakName = tempTaskName + " Break";
				SubTask tempTaskBreak = new SubTask(tempTaskBreakName, day.date, tempTaskEnd, tempTaskBreakEnd);
				//add subtasks to array
				subTasksToBeAdded.add(tempTask);
				subTasksToBeAdded.add(tempTaskBreak);
				//update openSlots with new end time point 
				openSlots.set(counter, openSlots.get(counter).plus((long)((tempNewTaskTimeLength + tempTaskBreakLength)*60), ChronoUnit.MINUTES));
			}
			tempTimeLength = openSlots.get(counter).until(openSlots.get(counter+1), ChronoUnit.HOURS);
			//System.out.println(day.date + " " + openSlots);
			if (tempTimeLength < task.actualMinTaskBlockHours){
				counter+=2;
			}
			
		}
		task.hoursLeft = task.hoursLeft - (hoursAssigned - hoursLeftForDay);
		day.insertSubTasks(subTasksToBeAdded);
		task.addSubTasks(subTasksToBeAdded);

	}



	public void kickoffTask(){}

	public ASCalendar viewBuilder(){
		ASCalendar newCalendar = new ASCalendar();

		return newCalendar;
	}


}
