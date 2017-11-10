import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DataStorage {
	//protected Event [] currentEvents;
	protected ArrayList<PreExistTask> preExistTaskList;
	protected ArrayList<UserTask> priorityUserTaskList;
	protected Map<String, String> userData;
	protected ASMonth[] archivedMonths;
	
	
	
	//constructor
	public DataStorage(){}

	public boolean saveToFile(Path filename, Object data){
		boolean writeSuccess = false;
		try {
			FileOutputStream fos = new FileOutputStream("PlaceHolderTextFileName");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(data);
			writeSuccess = true;
			oos.close();
		} catch(Exception e) {
			writeSuccess = false;
		}
		return writeSuccess;
	}

	public boolean readFromFile(Path filename, String target){
		boolean readSuccess = false;
		try {
			FileInputStream fis = new FileInputStream(target);
			ObjectInputStream ois = new ObjectInputStream(fis);
			DataStorage result = (DataStorage) ois.readObject();
			ois.close();
		} catch (Exception e) {
			readSuccess = false;
		}


		return readSuccess;
	}
}
