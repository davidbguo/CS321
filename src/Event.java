import java.time.*;
import java.util.*;

public abstract class Event implements java.io.Serializable {
	protected int priority;
	protected LocalDateTime dateTimeCreated;
	protected String name;
	protected ArrayList<LocalDateTime> breakdown;
	protected LocalDateTime endDateTime;
}
