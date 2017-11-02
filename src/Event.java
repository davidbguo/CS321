import java.time.*;

public abstract class Event {
	protected int priority;
	protected LocalDateTime dateTimeCreated;
	protected String name;
	protected LocalDateTime[] breakdown;
	protected LocalDateTime endDateTime;
}
