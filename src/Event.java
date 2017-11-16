import java.time.*;
import java.util.*;

<<<<<<< HEAD
public abstract class Event implements java.io.Serializable {
	protected int priority;
=======
public abstract class Event {
	protected double priority;
>>>>>>> 86561d0ce1bd0cea220db5545a611d57d03fd3fc
	protected LocalDateTime dateTimeCreated;
	protected String name;
	protected ArrayList<LocalDateTime> breakdown;
	protected LocalDateTime endDateTime;
}
