import java.time.*;
import java.time.temporal.*;
import java.util.*;

public class LogicLayer {
	protected DataStorage data = new DataStorage();

	//constructor
	public LogicLayer(){
		//temp data

	}
	
	public void updateDataCalendar() {
		ArrayList<ASMonth> monthList = new ArrayList<ASMonth>();
		ASDay start = data.currentDays.get(0);
		ASDay end = data.currentDays.get(data.currentDays.size());
		ASDay temp = start;
		int counter = 0;
		for (int i = 0; i < start.date.until(end.date, ChronoUnit.MONTHS); ) {
			ASMonth newMonth = new ASMonth(YearMonth.of(temp.date.getYear(), temp.date.getMonth()));
			for (int j = 0; j < temp.date.until(end.date, ChronoUnit.DAYS); j++ ) {
				temp = data.currentDays.get(counter+j);
				newMonth.days.add(temp);
				if (!data.currentDays.get(j).date.getMonth().equals(data.currentDays.get(j+1).date.getMonth())){
					i++;
					counter += j+1;
					break;
				}
			}
		}
		
	}

	public void setCurrentDays() {
		System.out.println("inside setcurrentdays");
		data.currentDays.clear();
		LocalDate semStart = LocalDate.of(Integer.parseInt(data.userData.get("semStartYear")), Integer.parseInt(data.userData.get("semStartMonth")), Integer.parseInt(data.userData.get("semStartDay")));
		LocalDate semEnd = LocalDate.of(Integer.parseInt(data.userData.get("semEndYear")), Integer.parseInt(data.userData.get("semEndMonth")), Integer.parseInt(data.userData.get("semEndDay")));
		LocalDate current = semStart;
		while (!current.equals(semEnd)) {
			//System.out.println(current);
			ASDay newTemp = new ASDay(current, data.preExistTaskList);
			data.currentDays.add(newTemp);
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
		int daysDiff = (int) data.currentDays.get(0).date.until(LocalDate.now(), ChronoUnit.DAYS);
		int currDayCounter = daysDiff;
		//System.out.println(tasksByPriority.size());
		for (int i = 0 ; i < tasksByPriority.size(); i++) {
			//System.out.println("inside " + data.currentDays.get(currDayCounter).date);
			currDayCounter = daysDiff;
			while(tasksByPriority.get(i).hoursLeft > 0) {
				while((data.currentDays.get(currDayCounter).hoursLeft) < 0.01 && (currDayCounter < data.currentDays.size())) {
					//System.out.println(currDayCounter);
					currDayCounter++;
				}
				breakdownForSingleDay(data.currentDays.get(currDayCounter), tasksByPriority.get(i));
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
				SubTask tempTask = new SubTask(tempTaskName, day.date, tempTaskStart, tempTaskEnd, task.dateTimeCreated);
				String tempTaskBreakName = tempTaskName + " Break";
				SubTask tempTaskBreak = new SubTask(tempTaskBreakName, day.date, tempTaskEnd, tempTaskBreakEnd, task.dateTimeCreated);
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

	public void deleteEvent() {
		
	}

	public void kickoffTask(){}

	public ASCalendar viewBuilder(){
		ASCalendar newCalendar = new ASCalendar();

		return newCalendar;
	}


}
