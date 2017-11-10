import java.time.*;
import java.time.temporal.*;
import java.util.*;

public class LogicLayer {
	protected DataStorage data = new DataStorage();
	protected ArrayList<ASDay> currentDays;

	//constructor
	public LogicLayer(){
		setCurrentDays();
	}

	private void setCurrentDays() {
		currentDays.clear();
		LocalDate semStart = LocalDate.of(Integer.parseInt(data.userData.get("semStartYear")), Integer.parseInt(data.userData.get("semStartMonth")), Integer.parseInt(data.userData.get("semStartDay")));
		LocalDate semEnd = LocalDate.of(Integer.parseInt(data.userData.get("semEndYear")), Integer.parseInt(data.userData.get("semEndMonth")), Integer.parseInt(data.userData.get("semEndDay")));
		LocalDate current = semStart;
		while (current != semEnd) {
			current = semStart.plusDays(1);
			ASDay newTemp = new ASDay(current, data.preExistTaskList);
			currentDays.add(newTemp);
		}
	}
	
	
	public void prioritization(){}

	public void conflictDetection(){}

	public void createBreakdown(){
		ArrayList<UserTask> tasksByPriority = data.priorityUserTaskList;
		int currDayCounter = currentDays.indexOf(LocalDate.now());
		for (int i = 0 ; i < tasksByPriority.size(); i++) {
			while(tasksByPriority.get(i).hoursLeft > 0) {
				while(currentDays.get(currDayCounter).hoursLeft > 0) {
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
		if (timeSlotLength < (1+5/60)) {}
		else if (timeSlotLength < (2+10.0/60)) {
			breakLength = 10.0/60.0;
		} else if (timeSlotLength < (3+15.0/60.0)) {
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
			double tempTimeLength = openSlots.get(counter).until(openSlots.get(counter+1), ChronoUnit.HOURS);
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
				LocalTime tempTaskEnd = (openSlots.get(counter).plus((long)(tempNewTaskTimeLength*60), ChronoUnit.MINUTES));
				LocalTime tempTaskBreakEnd = tempTaskEnd.plus((long)(tempTaskBreakLength*60), ChronoUnit.MINUTES);
				String tempTaskName = task.name + " part " + (taskPartCounter++);
				SubTask tempTask = new SubTask(tempTaskName, tempTaskStart, tempTaskEnd);
				String tempTaskBreakName = "Break after " + tempTaskName;
				SubTask tempTaskBreak = new SubTask(tempTaskBreakName, tempTaskEnd, tempTaskBreakEnd);
				//add subtasks to array
				subTasksToBeAdded.add(tempTask);
				subTasksToBeAdded.add(tempTaskBreak);
				//update openSlots with new end time point 
				openSlots.set(counter, openSlots.get(counter).plus((long)(tempNewTaskTimeLength + tempTaskBreakLength)*60, ChronoUnit.MINUTES));
			}
			tempTimeLength = openSlots.get(counter).until(openSlots.get(counter+1), ChronoUnit.HOURS);
			if (tempTimeLength < task.actualMinTaskBlockHours){
				counter+=2;
			}
			
		}
		task.hoursLeft = hoursAssigned - hoursLeftForDay;
		day.insertSubTasks(subTasksToBeAdded);
		task.addSubTasks(subTasksToBeAdded);

	}



	public void kickoffTask(){}

	public ASCalendar viewBuilder(){
		ASCalendar newCalendar = new ASCalendar();

		return newCalendar;
	}


}
