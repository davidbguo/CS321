import java.nio.file.*;
import java.util.*;

public class DataStorage {
	private Event [] currentEvents;
    private SortedMap<String, Integer> priorityList;
    private Map<String, String> userHabits;
    private ASMonth[] activeMonths;

    //constructor
    public DataStorage(){}

    public boolean saveToFile(Path filename){
    	boolean writeSuccess = false;
    	
		return writeSuccess;
    }

    public boolean readFromFile(Path filename, String target){
    	boolean readSuccess = false;
    	
    	return readSuccess;
    }
}
