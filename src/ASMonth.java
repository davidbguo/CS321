import java.nio.file.Path;
import java.time.*;
import java.util.ArrayList;

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
}
