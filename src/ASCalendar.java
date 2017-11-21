import java.util.ArrayList;

public class ASCalendar {
	protected ArrayList<ASMonth> currentView; //ArrayList of Months in the Semester
	
	public ASCalendar() {
		 currentView  = new ArrayList<ASMonth>();
	}
	
    public void changeCurrentView(){ // Edit the current semester-TO-BE implemented
    	
    }
    
    public String toString() {	
    	String retVal = "CURRENT SEMESTER CALENDAR \n";
    	for (int i = 0; i < currentView.size(); i++) {
    		retVal += currentView.get(i).toString() + "\n";
    	}
    	return retVal;
    }
}
