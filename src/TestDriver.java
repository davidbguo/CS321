import java.time.*;



public class TestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LogicLayer logicEngine = new LogicLayer();
		//testASDay(logicEngine);	
		testLogicLayer(logicEngine);
		

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

	public static void testLogicLayer(LogicLayer llayer) {
		LocalDate day = LocalDate.of(2015, 12, 31);
		ASDay test = new ASDay(day, llayer.data.preExistTaskList);
		SubTask	s1 = new SubTask("test",LocalTime.of(3, 0),LocalTime.of(8,00));
		SubTask	s2 = new SubTask("test",LocalTime.of(14, 15),LocalTime.of(15,00));
		test.insertSubTask(s1);
		test.insertSubTask(s2);
		
		UserTask task = new UserTask("testTask", "testCat", LocalDateTime.of(2017, 11, 25, 23, 59));
		llayer.breakdownForSingleDay(test, task);
		System.out.println(test.eventsOfDay);
		System.out.println(task.breakdown);
		
	}
	
}
