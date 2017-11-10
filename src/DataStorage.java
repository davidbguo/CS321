import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DataStorage {
	//protected Event [] currentEvents;
	protected ArrayList<PreExistTask> preExistTaskList = new ArrayList<PreExistTask>();
	protected ArrayList<UserTask> priorityUserTaskList = new ArrayList<UserTask>();
	protected HashMap<String, String> userData = new HashMap<String, String>();
	protected ASMonth[] archivedMonths;
	
	
	
	//constructor
	public DataStorage(ArrayList<PreExistTask> preExistTaskList){
		this.preExistTaskList = preExistTaskList;
	}

	public boolean saveToFile(String filename){
		boolean writeSuccess = false;
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			writeSuccess = true;
			oos.close();
		} catch(Exception e) {
			writeSuccess = false;
		}
		return writeSuccess;
	}

	public static DataStorage readFromFile(String target){
		boolean readSuccess = false;
		DataStorage data1 = null;
		try {
			FileInputStream fis = new FileInputStream(target);
			ObjectInputStream ois = new ObjectInputStream(fis);
			data1 = (DataStorage) ois.readObject();
			ois.close();
		} catch (Exception e) {
			readSuccess = false;
		}
		return data1;
	}
}
