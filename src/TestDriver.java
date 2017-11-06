import java.time.*;



public class TestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//testASDay();	
		testLogicLayer();
		

	}
	
	public static void testASDay() {
		LocalDate day = LocalDate.of(2015, 12, 31);
		ASDay test = new ASDay(day);
		SubTask	s1 = new SubTask("test",LocalTime.of(13, 0),LocalTime.of(14,00));
		SubTask	s2 = new SubTask("test",LocalTime.of(14, 15),LocalTime.of(15,00));
			/*
				SubTask	s3 = new SubTask("test",LocalTime.of(13, 0),LocalTime.of(14,00));
				SubTask	s4 = new SubTask("test",LocalTime.of(13, 0),LocalTime.of(14,00));
				SubTask	s5 = new SubTask("test",LocalTime.of(13, 0),LocalTime.of(14,00));
			*/
		test.eventsOfDay.add(s1);
		test.eventsOfDay.add(s2);
		System.out.print(test.getOpenTimeSlot());
	}

	public static void testLogicLayer() {
		LogicLayer llayer = new LogicLayer();
		ASDay day = new ASDay(LocalDate.now());
		
		UserTask task = new UserTask("testTask", "testCat", LocalDateTime.of(2017, 11, 25, 23, 59));
		llayer.breakdownForSingleDay(day, task);
		System.out.println(day.eventsOfDay);
		System.out.println(task.breakdown);
		
	}
	
}
