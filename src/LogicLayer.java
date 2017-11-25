import java.time.*;
import java.time.temporal.*;
import java.util.*;

/*
 * LogicLayer, 2nd tier does all computations, responds to FrontEnd. All you need should be here.
 * Variables
 * 			DataStorage data, instance of DataStorage for data storage
 * Methods:
 * 			ArrayList<UserTask> getUTList, returns list of all UserTasks
 * 			ArrayList<PreExistTask> getPETList, return list of all PreExistTasks
 * 			addPreExistTask(String, DayofWeek, LocalTime start, LocalTime end, LocalDateTime endDateTime)
 * 			addUserTask(String, LocalDateTime start, LocalDateTime end, TaskTypeEnum)
 * 			deletePreExistTask(PreExistTask)
 * 			deleteUserTask(UserTask task)
 * 			editPreExistTask(PreExistTask, String, DayOfWeek, LocalTime start, LocalTime end, LocalDateTime)
 * 			editUserTask(UserTask, String, LocalDateTime start, LocalDateTime end, TaskTypeEnum)
 * 			ArrayList<UserTask> listTasksNotDone, returns list of all tasks with hoursLeft not able to be scheduled
 * 			HashMap<Integer,ASDay> getCalendarData(), returns data of all scheduled subtasks per day from the calendar obj in data
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * 			updateDataCalendar, takes data stored in currentDays and update calendar obj in data
 *  		int findPreExistTask(PreExistTask), private helper method
 * 			int findUserTask(UserTask), private helper method
 * 			setCurrentDays, initializes currentDays[] in data with length based on semster start and end information from HashMap in data
 * 			prioritize, update priorityUserTaskList to be accurately sorted
 * 			createBreakdown, calls prioritize then creates breakdown for each task by order in priorityUserTaskList. calls updateDataCalendar at the end
 * 			double findTaskBreakLength, helper method 
 * 			breakdownForSingleDay(ASDay day, UserTask task), helper method to contains logic to breakdown for single day using openTimeSlots from ASDay and created SubTasks
 * For FrontEnd:
 * 			You will need to use all the methods listed in the first half of the Method Section.
 * 			Most of it is pretty straightforward. Keep note of what inputs the methods needs.
 * 			Special note to editing methods, the first arg is a PreExistTask obj or UserTask obj. Since you guys shouldn't be created such objects directly in the front end
 * 			(look at the add methods that take in input string/localdatetime args for the logiclayer to do the creation)
 * 			you may be wondering how you would pass a PreExistTask obj or UserTask obj from the frontend to the backend.
 *  !!!--->	!!!!! What you need to do is call getUTList and getPETList first. This returns the ArrayList of all PreExistTask/UserTask objects.
 * 			Then you can display them to the user. They will pick one. Then you return that object that has the old data inside, and the new data fills the rest of the edit method args.
 * 			Note if the user didn't change one of the args (for example they didn't change the name), just pass in null. The method must always have 5/4 arg inputs. 
 *  
 * 
 */

public class LogicLayer {
	protected DataStorage data = new DataStorage();
	
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
	
	public ArrayList<PreExistTask> getPETList(){
		return data.preExistTaskList;
	}
	
	public ArrayList<UserTask> getUTList(){
		return data.priorityUserTaskList;
	}

	public void addPreExistTask(String name, DayOfWeek day, LocalTime start, LocalTime end, LocalDateTime endDateTime) {
		PreExistTask newPet = new PreExistTask(name, day, start, end, endDateTime);
		data.preExistTaskList.add(newPet);
		setCurrentDays();
		createBreakdown();
		updateDataCalendar();
	}

	public void addUserTask(String name, LocalDateTime startDateTime, LocalDateTime endDateTime, TaskTypeEnum type) {
		UserTask newTask = new UserTask(name, startDateTime, endDateTime, type);
		data.priorityUserTaskList.add(newTask);
		createBreakdown();
		updateDataCalendar();
	}

	private int findPreExistTask(PreExistTask task) {
		for(int i = 0; i < data.preExistTaskList.size(); i++){
			if(task.equals(data.preExistTaskList.get(i))){
				return i;
			}	
		}
		return -1;
	}

	private int findUserTask(UserTask task) {
		for(int i = 0; i < data.priorityUserTaskList.size(); i++){
			if(task.equals(data.priorityUserTaskList.get(i))){
				return i;
			}	
		}
		return -1;
	}
	
	public void deletePreExistTask(PreExistTask task){
		data.deletePreExistTask(findPreExistTask(task));
		this.setCurrentDays();
		this.createBreakdown();
		updateDataCalendar();
	}
	public void deleteUserTask(UserTask task){
		data.deleteUserTask(findUserTask(task));
		this.prioritize();
		this.createBreakdown();
		updateDataCalendar();
	}

	public void editPreExistTask(PreExistTask task,String name, DayOfWeek day, LocalTime start, LocalTime end
			, LocalDateTime endDateTime){

		if(name == null)
			name = task.name;
		if(day == null)
			day = task.day;
		if(start == null)
			start = task.startTime;
		if(end == null)
			end = task.endTime;
		if(endDateTime == null)
			endDateTime = task.endDateTime;

		PreExistTask newTask = new PreExistTask(name, day, start, end,endDateTime );
		newTask.dateTimeCreated = task.dateTimeCreated;
		data.editPreExistTask(findPreExistTask(task), newTask);
		this.setCurrentDays();
		this.createBreakdown();
		updateDataCalendar();
	}
	public void editUserTask(UserTask task, String name, LocalDateTime startDateTime
			, LocalDateTime endDateTime, TaskTypeEnum type){
		if(name == null)
			name = task.name;
		if(startDateTime == null)
			startDateTime = task.startDateTime;
		if(endDateTime == null)
			endDateTime = task.endDateTime;
		if(type == null)
			type = task.type;

		UserTask newTask = new UserTask(name, startDateTime, endDateTime, type);
		newTask.dateTimeCreated = task.dateTimeCreated;
		data.editUserTask(findUserTask(task), newTask);
		this.prioritize();
		this.createBreakdown();
		updateDataCalendar();

	}

	public void addUserData(String key, String val) {
		data.userData.put(key, val);
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
			data.priorityUserTaskList.get(i).hoursLeft = data.priorityUserTaskList.get(i).hoursTotalEstimate;
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
	public void writeToFile(){
		this.data.saveToFile("cal.ser");
	}
	public void readFromFile(){
		try{
		this.data.readFromFile("cal.ser");
		}catch(Exception FileNotFound){
			System.out.println("cal.ser Not Found");
			}
	}
	
	public HashMap<Integer,ASDay> getCalendarData(){
		HashMap<Integer,ASDay> cal = new HashMap<Integer,ASDay>();
		int key = 0; 
		for(int i = 0; i<data.currentDays.size();i++){
			ASDay curr = data.currentDays.get(i);
			key = curr.getKey();
			cal.put(key, curr);
		}
		return cal;
	}
}
