import java.time.*;
import java.util.ArrayList;

/*
 * Class with a main just for running tests of LogicLayer methods
 * For FrontEnd:
 * 			Ignore this completely. 
 */

public class TestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LogicLayer logicEngine = new LogicLayer();
		setupLogicLayer(logicEngine);
		logicEngine.setCurrentDays();
		
		//testDataStorage(logicEngine);
		//testEventDeletion(logicEngine);

		//testEventEditing(logicEngine);
		//testBreakdown(logicEngine);

		//testUpdateDataCalendar(logicEngine);
		//setupUICal(logicEngine);
		testUpdateDataCalendar(logicEngine);

	}
	//testing HashMap for UI layer
	public static void setupUICal(LogicLayer llayer){
		llayer.getCalendarData();
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
			UserTask ut1 = new UserTask("ut1", LocalDateTime.now(), LocalDateTime.now().plusDays(3), TaskTypeEnum.QUEST);
			UserTask ut2 = new UserTask("ut2", LocalDateTime.now(), LocalDateTime.of(2017, 11, 25, 23, 59),TaskTypeEnum.QUIZ);
			UserTask ut3 = new UserTask("ut3", LocalDateTime.now().plusDays(7), LocalDateTime.of(2017, 12, 10, 23, 59),TaskTypeEnum.TEST);
			ut1.hoursLeft = 20;
			ut2.hoursLeft = 20;
			ut3.hoursLeft = 20;
			ArrayList<UserTask> utList = new ArrayList<UserTask>();
			utList.add(ut1);
			utList.add(ut2);
			utList.add(ut3);
			llayer.data.priorityUserTaskList = utList;
			llayer.data.userData.put("semStartYear", "2017");
			llayer.data.userData.put("semStartMonth", "9");
			llayer.data.userData.put("semStartDay", "1");
			llayer.data.userData.put("semEndYear", "2017");
			llayer.data.userData.put("semEndMonth", "12");
			llayer.data.userData.put("semEndDay", "15");
			
		}
	

	public static void testDataStorage(LogicLayer ll){
		DataStorage data = new DataStorage();
		data.preExistTaskList = ll.data.preExistTaskList;
		data.saveToFile("Trial1.ser");
		DataStorage dataOld = data.readFromFile("Trial1.ser");
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
		ll.deletePreExistTask(preTask);
		ll.deleteUserTask(userTask);

		System.out.println("After---------------------");
		System.out.println(ll.data.preExistTaskList);
		System.out.println(ll.data.priorityUserTaskList);
	}
	public static void testEventEditing(LogicLayer ll){
		System.out.println("Before---------------------");
		System.out.println(ll.data.preExistTaskList);
		System.out.println(ll.data.priorityUserTaskList);
		PreExistTask preTask = ll.data.preExistTaskList.get(1);
		UserTask userTask = ll.data.priorityUserTaskList.get(2);
		
		System.out.println("Going to Edit------------");
		System.out.println("PreTask Edit:"+ preTask);
		System.out.println("UserTask Edit:"+ userTask);
		
		ll.editPreExistTask(preTask,"Test",null,null,null,null );
		ll.editUserTask(userTask,"HELP",null,null,null );
		System.out.println("After---------------------");
		System.out.println(ll.data.preExistTaskList);
		System.out.println(ll.data.priorityUserTaskList);
	}


	public static void testTaskType(){
		UserTask task = new UserTask("task", LocalDateTime.now(), LocalDateTime.of(2017, 11, 25, 23, 59),TaskTypeEnum.QUIZ);
		task.type.setPageNumer(30);
		System.out.println(task.type.getHourRequired());
		UserTask task1 = new UserTask("task1", LocalDateTime.now().plusDays(7), LocalDateTime.of(2017, 12, 10, 23, 59),TaskTypeEnum.TEST);
		task1.type.setWordCount(500);
		System.out.println(task1.type.getHourRequired());
	}
	
	public static void testASDay(LogicLayer logic) {
		LocalDate day = LocalDate.of(2015, 12, 31);
		ASDay test = new ASDay(day, logic.data.preExistTaskList);
		SubTask	s1 = new SubTask("test", LocalDate.now(), LocalTime.of(3, 0),LocalTime.of(8,00), null);
		SubTask	s2 = new SubTask("test", LocalDate.now(), LocalTime.of(14, 15),LocalTime.of(15,00), null);
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
		SubTask	s1 = new SubTask("BLOCKED", LocalDate.now(), LocalTime.of(3, 0),LocalTime.of(8,00), null);
		SubTask	s2 = new SubTask("BLOCKED", LocalDate.now(), LocalTime.of(14, 15),LocalTime.of(15,00), null);
		test.insertSubTask(s1);
		test.insertSubTask(s2);
		
		UserTask task = new UserTask("task", LocalDateTime.now(), LocalDateTime.of(2017, 11, 25, 23, 59),TaskTypeEnum.QUIZ);
		task.hoursLeft = 20;
		llayer.breakdownForSingleDay(test, task);
		System.out.println(test.eventsOfDay);
		System.out.println(task.breakdown);
		
	}
	
	//also prints out data stored in PriorityUserTaskList, LLayer.currentDays and PreExistTaskList
	public static void testBreakdown(LogicLayer llayer) {
		System.out.println("inside test");
		llayer.createBreakdown();
		/*for (int i = 0 ; i < llayer.currentDays.size(); i++) {
			System.out.println(llayer.currentDays.get(i).date.toString() + " " + llayer.currentDays.get(i).eventsOfDay.toString());
		}
		
		for (int i = 0 ; i < llayer.data.priorityUserTaskList.size(); i++) {
			llayer.data.priorityUserTaskList.get(i).printString();
			for (int j = 0 ; j < llayer.data.priorityUserTaskList.get(i).breakdown.size(); j++) {
				llayer.data.priorityUserTaskList.get(i).breakdown.get(j).printString();
			}
		}
		
		for (int i = 0 ; i < llayer.data.preExistTaskList.size(); i++) {
			llayer.data.preExistTaskList.get(i).printString();
		}*/
	}
	
	//need testing on updateDataCalendar
	public static void testUpdateDataCalendar(LogicLayer llayer) {
		llayer.createBreakdown();
		System.out.println(llayer.data.calendar.toString());

	}
}
