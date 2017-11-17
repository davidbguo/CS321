import java.time.*;
import java.util.ArrayList;



public class TestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LogicLayer logicEngine = new LogicLayer();
		setupLogicLayer(logicEngine);
		logicEngine.setCurrentDays();
		//testASDay(logicEngine);	
		//testBreakdownSingleDay(logicEngine);
		//testTaskType();
		//testBreakdown(logicEngine);
		//testDataStorage(logicEngine);
		testEventDeletion(logicEngine);
	}

	//continue testing to see is pre existing tasks are being taken into consideration/represented properly
	public static void setupLogicLayer(LogicLayer llayer) {
			System.out.println("inside setup");
			ArrayList<PreExistTask> petList = new ArrayList<PreExistTask>();
			petList.add(new PreExistTask("MONDAY SLEEP", DayOfWeek.MONDAY, LocalTime.of(0, 0), LocalTime.of(9, 0), null));
			petList.add(new PreExistTask("MONDAY PET1", DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(2, 0), null));
			petList.add(new PreExistTask("MONDAY PET2", DayOfWeek.MONDAY, LocalTime.of(17, 0), LocalTime.of(22, 0), null));
			petList.add(new PreExistTask("TUESDAY SLEEP", DayOfWeek.TUESDAY, LocalTime.of(0, 0), LocalTime.of(9, 0), null));
			petList.add(new PreExistTask("TUESDAY PET1", DayOfWeek.TUESDAY, LocalTime.of(12, 0), LocalTime.of(16, 0), null));
			petList.add(new PreExistTask("TUESDAY PET2", DayOfWeek.TUESDAY, LocalTime.of(18, 0), LocalTime.of(21, 0), null));
			petList.add(new PreExistTask("WEDNESDAY SLEEP", DayOfWeek.WEDNESDAY, LocalTime.of(0, 0), LocalTime.of(9, 0), null));
			petList.add(new PreExistTask("WEDNESDAY PET1", DayOfWeek.WEDNESDAY, LocalTime.of(9, 0), LocalTime.of(11, 0), null));
			petList.add(new PreExistTask("WEDNESDAY PET2", DayOfWeek.WEDNESDAY, LocalTime.of(13, 0), LocalTime.of(17, 0), null));
			petList.add(new PreExistTask("THURSDAY SLEEP", DayOfWeek.THURSDAY, LocalTime.of(0, 0), LocalTime.of(9, 0), null));
			petList.add(new PreExistTask("THURSDAY PET1", DayOfWeek.THURSDAY, LocalTime.of(17, 0), LocalTime.of(8, 0), null));
			petList.add(new PreExistTask("THURSDAY PET2", DayOfWeek.THURSDAY, LocalTime.of(22, 0), LocalTime.of(23, 0), null));
			petList.add(new PreExistTask("FRIDAY SLEEP", DayOfWeek.FRIDAY, LocalTime.of(0, 0), LocalTime.of(9, 0), null));
			petList.add(new PreExistTask("FRIDAY PET1", DayOfWeek.FRIDAY, LocalTime.of(11, 30), LocalTime.of(16, 45), null));
			petList.add(new PreExistTask("FRIDAY PET2", DayOfWeek.FRIDAY, LocalTime.of(21, 0), LocalTime.of(23, 59), null));
			petList.add(new PreExistTask("SATURDAY SLEEP", DayOfWeek.SATURDAY, LocalTime.of(0, 0), LocalTime.of(9, 0), null));
			petList.add(new PreExistTask("SATURDAY PET1", DayOfWeek.SATURDAY, LocalTime.of(18, 0), LocalTime.of(18, 13), null));
			petList.add(new PreExistTask("SATURDAY PET2", DayOfWeek.SATURDAY, LocalTime.of(21, 0), LocalTime.of(22, 45), null));
			petList.add(new PreExistTask("SUNDAY SLEEP", DayOfWeek.SUNDAY, LocalTime.of(0, 0), LocalTime.of(9, 0), null));
			petList.add(new PreExistTask("SUNDAY PET1", DayOfWeek.SUNDAY, LocalTime.of(14, 0), LocalTime.of(16,29), null));
			petList.add(new PreExistTask("SUNDAY PET2", DayOfWeek.SUNDAY, LocalTime.of(22, 0), LocalTime.of(23, 30), null));
			
			
			llayer.data.preExistTaskList = petList;
			UserTask ut1 = new UserTask("ut1", "testCat", LocalDateTime.now(), LocalDateTime.now().plusDays(3), TaskTypeEnum.QUEST, 2);
			UserTask ut2 = new UserTask("ut2", "testCat", LocalDateTime.now(), LocalDateTime.of(2017, 11, 25, 23, 59),TaskTypeEnum.QUIZ, 3);
			UserTask ut3 = new UserTask("ut3", "testCat", LocalDateTime.now().plusDays(7), LocalDateTime.of(2017, 12, 10, 23, 59),TaskTypeEnum.TEST, 4);
			ut1.hoursLeft = 20;
			ut2.hoursLeft = 20;
			ut3.hoursLeft = 20;
			ArrayList<UserTask> utList = new ArrayList<UserTask>();
			utList.add(ut1);
			utList.add(ut2);
			utList.add(ut3);
			llayer.data.priorityUserTaskList = utList;
			llayer.data.userData.put("semStartYear", "2017");
			llayer.data.userData.put("semStartMonth", "11");
			llayer.data.userData.put("semStartDay", "9");
			llayer.data.userData.put("semEndYear", "2017");
			llayer.data.userData.put("semEndMonth", "12");
			llayer.data.userData.put("semEndDay", "20");
			
		}
	

	public static void testDataStorage(LogicLayer ll){
		DataStorage data = new DataStorage();
		data.preExistTaskList = ll.data.preExistTaskList;
		data.saveToFile("Trial1.ser");
		DataStorage dataOld = DataStorage.readFromFile("Trial1.ser");
		System.out.println("Done Reading! ");
		
		System.out.println(dataOld.preExistTaskList);
		System.out.println(dataOld.priorityUserTaskList);
	}
	
	public static void testEventDeletion(LogicLayer ll){
		System.out.println("Before---------------------");
		System.out.println(ll.data.preExistTaskList);
		System.out.println(ll.data.priorityUserTaskList);
		PreExistTask preTask = ll.data.preExistTaskList.get(1);
		UserTask userTask = ll.data.priorityUserTaskList.get(2);
		System.out.println("Going to Delete------------");
		System.out.println("PreTask Delete:"+ preTask);
		System.out.println("UserTask Delete:"+ userTask);
		ll.callDeletePreExistTask(preTask);
		ll.callDeleteUserTask(userTask);

		System.out.println("After---------------------");
		System.out.println(ll.data.preExistTaskList);
		System.out.println(ll.data.priorityUserTaskList);
	}
	public static void testEventEditing(LogicLayer ll){
		
		//ll.callEditPreExistTask(preTask, preNewTask);
		//ll.callEditUserTask(userTask, userNewTask);
	}


	public static void testTaskType(){
		UserTask task = new UserTask("task", "testCat", LocalDateTime.now(), LocalDateTime.of(2017, 11, 25, 23, 59),TaskTypeEnum.QUIZ, 3);
		task.type.setPageNumer(30);
		System.out.println(task.type.getHourRequired());
		UserTask task1 = new UserTask("task1", "testCat", LocalDateTime.now().plusDays(7), LocalDateTime.of(2017, 12, 10, 23, 59),TaskTypeEnum.TEST, 4);
		task1.type.setWordCount(500);
		System.out.println(task1.type.getHourRequired());
	}
	
	public static void testASDay(LogicLayer logic) {
		LocalDate day = LocalDate.of(2015, 12, 31);
		ASDay test = new ASDay(day, logic.data.preExistTaskList);
		SubTask	s1 = new SubTask("test",LocalTime.of(3, 0),LocalTime.of(8,00));
		SubTask	s2 = new SubTask("test",LocalTime.of(14, 15),LocalTime.of(15,00));
			/*
				SubTask	s3 = new SubTask("test",LocalTime.of(13, 0),LocalTime.of(14,00));
				SubTask	s4 = new SubTask("test",LocalTime.of(13, 0),LocalTime.of(14,00));
				SubTask	s5 = new SubTask("test",LocalTime.of(13, 0),LocalTime.of(14,00));
			*/
		test.insertSubTask(s1);
		test.insertSubTask(s2);
		System.out.print(test.getOpenTimeSlot());
	}

	public static void testBreakdownSingleDay(LogicLayer llayer) {
		LocalDate day = LocalDate.of(2015, 12, 31);
		ASDay test = new ASDay(day, llayer.data.preExistTaskList);
		SubTask	s1 = new SubTask("BLOCKED",LocalTime.of(3, 0),LocalTime.of(8,00));
		SubTask	s2 = new SubTask("BLOCKED",LocalTime.of(14, 15),LocalTime.of(15,00));
		test.insertSubTask(s1);
		test.insertSubTask(s2);
		
		UserTask task = new UserTask("task", "testCat", LocalDateTime.now(), LocalDateTime.of(2017, 11, 25, 23, 59),TaskTypeEnum.QUIZ, 3);
		task.hoursLeft = 20;
		llayer.breakdownForSingleDay(test, task);
		System.out.println(test.eventsOfDay);
		System.out.println(task.breakdown);
		
	}
	
	public static void testBreakdown(LogicLayer llayer) {
		System.out.println("inside test");
		llayer.createBreakdown();
		for (int i = 0 ; i < llayer.currentDays.size(); i++) {
			System.out.println(llayer.currentDays.get(i).date.toString() + " " + llayer.currentDays.get(i).eventsOfDay.toString());
		}
	}
}
