import java.util.ArrayList;

public class ASCalendar {
	protected ArrayList<ASMonth> currentView;
	
	public ASCalendar() {
		 currentView  = new ArrayList<ASMonth>();
	}
	
    public void changeCurrentView(){
    	
    }
    
    public String toString() {
    	String retVal = "CURRENT SEMESTER CALENDAR \n";
    	for (int i = 0; i < currentView.size(); i++) {
    		retVal += currentView.get(i).toString() + "\n";
    	}
    	return retVal;
    }
}
