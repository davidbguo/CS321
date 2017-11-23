import java.nio.file.Path;
import java.time.*;
import java.util.ArrayList;

/*
 * our version of a Month object
 * Variables:
 * 			final long serialVersionUID, something with serializable. probably unfinished and unused
 * 			ArrayList<ASDay> days
 * 			YearMonth monthID, unique identifier based on a specify Year and Month
 * Methods:
 * 			ASMonth(YearMonth) constructor
 * 			boolean saveToFile(Path fname), not used in final product?
 * 			boolean readFromFile, not use in final product?
 * 			String toString(), prints monthID and then loops toString of all ASDays in days[]
 * For FrontEnd:
 * 			You might access days, monthID, and toString()
 */

public class ASMonth implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ArrayList<ASDay> days = new ArrayList<ASDay>();
	protected YearMonth monthID;
    
    //constructor
    public ASMonth(YearMonth id ) {
    	monthID = id;
    }
    
    public boolean saveToFile(Path fname){
    	boolean writeSuccess = false;
    	
		return writeSuccess;
	}
    
    public boolean readFromFile(Path fname){
    	boolean readSuccess = false;
    	
    	return readSuccess;
    }
    
    public String toString() {
    	String retVal = monthID + "\n";
    	for (int i = 0; i < days.size(); i ++) {
    		retVal += days.get(i).toString() + "\n";
    	}
    	return retVal;
    }
}
