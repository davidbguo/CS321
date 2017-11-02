import java.time.*;

public abstract class Event {
	int priority;
    LocalDateTime dateTimeCreated;
    String name;
    LocalDateTime[] breakdown;
    LocalDateTime endDateTime;
}
