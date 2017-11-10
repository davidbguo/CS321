import java.time.*;
import java.util.ArrayList;



public class TestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LogicLayer logicEngine = new LogicLayer();
		setupLogicLayer(logicEngine);
		//testASDay(logicEngine);	
		//testBreakdownSingleDay(logicEngine);
		//testTaskType();
		testBreakdown(logicEngine);
		testDataStorage(logicEngine);
	}
	public static void testDataStorage(LogicLayer ll){
		DataStorage data = new DataStorage(ll.data.preExistTaskList);
		data.saveToFile("Trial1.txt");
		//data = (DataStorage)DataStorage.readFromFile("Trial1.txt");
		System.out.println("Done");
		System.out.println(data.preExistTaskList);
	}
	public static void setupLogicLayer(LogicLayer llayer) {
		System.out.println("inside setup");
		PreExistTask pet1 = new PreExistTask("pet1", DayOfWeek.MONDAY, LocalTime.of(11, 0), LocalTime.of(23, 0));
		PreExistTask pet2 = new PreExistTask("pet2", DayOfWeek.TUESDAY, LocalTime.of(6, 0), LocalTime.of(11, 0));
		PreExistTask pet3 = new PreExistTask("pet3", DayOfWeek.WEDNESDAY, LocalTime.of(3, 0), LocalTime.of(6, 0));
		PreExistTask pet4 = new PreExistTask("pet4", DayOfWeek.THURSDAY, LocalTime.of(1, 0), LocalTime.of(3, 0));
		PreExistTask pet5 = new PreExistTask("pet5", DayOfWeek.FRIDAY, LocalTime.of(0, 0), LocalTime.of(1, 0));

		ArrayList<PreExistTask> petList = new ArrayList<PreExistTask>();
		petList.add(pet1);
		petList.add(pet2);
		petList.add(pet3);
		petList.add(pet4);
		petList.add(pet5);
		
		llayer.data.preExistTaskList = petList;
		UserTask ut1 = new UserTask("ut1", "testCat", LocalDateTime.of(2017, 11, 25, 23, 59),TaskTypeEnum.QUEST);
		UserTask ut2 = new UserTask("ut2", "testCat", LocalDateTime.of(2017, 11, 25, 23, 59),TaskTypeEnum.QUIZ);
		UserTask ut3 = new UserTask("ut3", "testCat", LocalDateTime.of(2017, 11, 25, 23, 59),TaskTypeEnum.TEST);
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
		llayer.data.userData.put("semStartDay", "1");
		llayer.data.userData.put("semEndYear", "2017");
		llayer.data.userData.put("semEndMonth", "12");
		llayer.data.userData.put("semEndDay", "31");
		
	}
	public static void testTaskType(){
		UserTask task = new UserTask("testTask", "testCat", LocalDateTime.of(2017, 11, 25, 23, 59),TaskTypeEnum.READING);
		task.type.setPageNumer(30);
		System.out.println(task.type.getHourRequired());
		UserTask task1 = new UserTask("testTask", "testCat", LocalDateTime.of(2017, 11, 25, 23, 59),TaskTypeEnum.ESSAY);
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
		
		UserTask task = new UserTask("testTask", "testCat", LocalDateTime.of(2017, 11, 25, 23, 59),TaskTypeEnum.PROBLEMSET);
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
