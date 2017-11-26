import java.util.ArrayList;

/*
 * Our own version of a calendar object
 * Variables:
 * 			ArrayList<ASMonth> currentView, length is set of semester start and end date from user in data storage
 * Methods:
 * 			Constructor
 * 			String toString(),Prints "Current Semester Calendar" and then loops through toString of all ASMonths in currentView[] 
 * For FrontEnd:
 * 			You might access currentView and toString()
 */

public class ASCalendar implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;

	
	protected ArrayList<ASMonth> currentView; 
	
	public ASCalendar() {
		 currentView  = new ArrayList<ASMonth>();
	}
	  
	public String toString() {	
    	String retVal = "CURRENT SEMESTER CALENDAR \n";
    	for (int i = 0; i < currentView.size(); i++) {
    		retVal += currentView.get(i).toString() + "\n";
    	}
    	return retVal;
    }
}
