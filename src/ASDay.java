import java.time.*;

public class ASDay {
	private Event[]events;
	private String[] details;
    private LocalDate date;
    
    public ASDay(LocalDate day) {
    	this.date = day;
    }
    
    
    public String[] getDailyEventsDetails(){
    	return details;
    }
    
    public String[] getDailyEventsSummary(){
    	String[] detailSummary = new String[10]; //placeholder code
    	
    	return detailSummary;
    } 
}
