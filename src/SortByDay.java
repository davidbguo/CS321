import java.util.Comparator;

/*
 * Comparator for SubTask
 * Methods:
 * 			compare(SubTask, SubTask), compares the LocalDateTime associated for that task
 * For FrontEnd:
 * 			You probably don't need this. If you really do want to sort SubTasks, go ahead and use this.
 */


class SortByDay implements Comparator<SubTask>
	{
	    public int compare(SubTask a, SubTask b)
	    {
	        if (a.day == null)
	        	return -1;
	        if (b.day == null)
	        	return 1;
	    	return a.day.compareTo(b.day);
	    }
	}