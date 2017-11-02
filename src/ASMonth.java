import java.nio.file.Path;
import java.time.*;

public class ASMonth {
	protected ASDay [] days;
	protected String name;
    
    //constructor
    public ASMonth(String name) {
    	this.name = name;
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
