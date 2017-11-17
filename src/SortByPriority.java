import java.util.Comparator;

public class SortByPriority implements Comparator<Event> {

	@Override
	public int compare(Event arg0, Event arg1) {
		return (int)(arg1.priority - arg0.priority)*10;
	}

}
