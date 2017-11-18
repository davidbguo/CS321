import java.util.ArrayList;

public class ASCalendar {
	protected ArrayList<ASMonth> currentView;
	
	public ASCalendar() {
		 currentView  = new ArrayList<ASMonth>();
	}
	
    public void changeCurrentView(){
    	
    }
    
    public void printToConsole() {
    	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    	System.out.println("CALENDAR");
    	System.out.println("");
    	for (int i = 0; i < currentView.size(); i++) {
    		currentView.get(i).printMonth();
    	}
    }
}
