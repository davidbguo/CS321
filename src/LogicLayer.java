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
		System.out.println("inside updateDataCalendar");
		ArrayList<ASMonth> monthList = new ArrayList<ASMonth>();
		ASDay start = data.currentDays.get(0);
		ASDay end = data.currentDays.get(data.currentDays.size()-1);
		ASDay temp = start;
		//System.out.println(start.date.until(end.date, ChronoUnit.MONTHS));
		int counter = 0;
		for (int i = 0; i < start.date.until(end.date, ChronoUnit.MONTHS)+1; i++) {
			//System.out.println(i);
			ASMonth newMonth = new ASMonth(YearMonth.of(start.date.plusMonths(i).getYear(), start.date.plusMonths(i).getMonth()));
			monthList.add(newMonth);
			int monthDayCount = 0;
			temp = data.currentDays.get(counter+monthDayCount);
			while(temp.date.getMonth().equals(newMonth.monthID.getMonth())){
				newMonth.days.add(temp);
				monthDayCount++;
				if (counter + monthDayCount == data.currentDays.size())
					break;
				temp = data.currentDays.get(counter+monthDayCount);
				
			}
			counter += monthDayCount;
		}
		data.calendar.currentView = monthList;
		
		
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
		data.currentDays.clear();
		LocalDate semStart = LocalDate.of(Integer.parseInt(data.userData.get("semStartYear")), Integer.parseInt(data.userData.get("semStartMonth")), Integer.parseInt(data.userData.get("semStartDay")));
		LocalDate semEnd = LocalDate.of(Integer.parseInt(data.userData.get("semEndYear")), Integer.parseInt(data.userData.get("semEndMonth")), Integer.parseInt(data.userData.get("semEndDay")));
		semEnd = semEnd.plusDays(1);
		LocalDate current = semStart;
		while (!current.equals(semEnd)) {
			//System.out.println(current);
			ASDay newTemp = new ASDay(current, data.preExistTaskList);
			data.currentDays.add(newTemp);
			current = current.plusDays(1);
		}
	}
	
	//use comparator for usertask
	private void prioritize(){
		Collections.sort(data.priorityUserTaskList, new SortByPriority());
	}

	public ArrayList<UserTask> listTasksNotDone(){
		ArrayList<UserTask> tasksNotDone = new ArrayList<UserTask>();
		for (int i = 0; i < data.priorityUserTaskList.size(); i++) {
			if (data.priorityUserTaskList.get(i).hoursLeft > 0) {
				tasksNotDone.add(data.priorityUserTaskList.get(i));
			}
		}
		
		return tasksNotDone;
	}

	public void createBreakdown(){
		System.out.println("inside createBreakdown");
		prioritize();
		ArrayList<UserTask> tasksByPriority = data.priorityUserTaskList;
		int daysDiff = (int) data.currentDays.get(0).date.until(LocalDate.now(), ChronoUnit.DAYS);
		int currDayCounter = daysDiff;
		//System.out.println(tasksByPriority.size());
		for (int i = 0 ; i < tasksByPriority.size(); i++) {
			//System.out.println("inside " + data.currentDays.get(currDayCounter).date);
			currDayCounter = daysDiff;
			while(tasksByPriority.get(i).hoursLeft > 0 && !data.currentDays.get(currDayCounter).date.equals(tasksByPriority.get(i).endDateTime.toLocalDate().plusDays(1))) {
				while((data.currentDays.get(currDayCounter).hoursLeft) < 0.01 && (currDayCounter < data.currentDays.size())) {
					//System.out.println(currDayCounter);
					currDayCounter++;
				}
				breakdownForSingleDay(data.currentDays.get(currDayCounter), tasksByPriority.get(i));
				currDayCounter++;
			}
		}
		updateDataCalendar();
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
	protected void breakdownForSingleDay(ASDay day, UserTask task){
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


	public ASCalendar viewBuilder(){
		return data.calendar;
	}


}
