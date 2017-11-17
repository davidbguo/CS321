import java.util.Comparator;

class SortByDay implements Comparator<SubTask>
	{
	    // Used for sorting in ascending order of
	    // roll name
	    public int compare(SubTask a, SubTask b)
	    {
	        if (a.day == null)
	        	return -1;
	        if (b.day == null)
	        	return 1;
	    	return a.day.compareTo(b.day);
	    }
	}