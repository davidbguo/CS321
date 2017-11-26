import java.util.Comparator;

/*
 * Comparator for Event
 * Methods:
 * 			compare(Event, Event), compares the priority value (based on endDateTime) for Events
 * For FrontEnd:
 * 			You probably don't need this. If you really do want to sort Events, go ahead and use this.
 */

public class SortByPriority implements Comparator<Event> {

	@Override
	public int compare(Event arg0, Event arg1) {
		return (int)(arg0.priority - arg1.priority)*10;
	}

}
