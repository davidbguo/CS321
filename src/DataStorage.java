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
	public DataStorage(){}
	
	public int deletePreExistTask(int i){
		this.preExistTaskList.remove(i);
		return i;
	}
	public int deleteUserTask(int i){
		this.priorityUserTaskList.remove(i);
		return i;
	}
	public void editPreExistTask(int i,PreExistTask newTask){
		this.preExistTaskList.remove(i);
		this.preExistTaskList.add(i, newTask);	
	}
	public void editUserTask(int i,UserTask newTask){
		priorityUserTaskList.remove(i);
		priorityUserTaskList.add(i, newTask);
	}
	
	public boolean saveToFile(String filename){
		boolean writeSuccess = false;
		try{
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.preExistTaskList);
			oos.writeObject(this.priorityUserTaskList);
			oos.writeObject(this.userData);
			oos.writeObject(this.archivedMonths);
			oos.close();
		}catch(Exception e){
			writeSuccess = false;
			e.printStackTrace();
		}
		return writeSuccess;
	}
	public static DataStorage readFromFile(String filename){
		DataStorage ds = new DataStorage();
		try{
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			ds.preExistTaskList = (ArrayList<PreExistTask>) ois.readObject();
			ds.priorityUserTaskList = (ArrayList<UserTask>) ois.readObject();
			ds.userData = (HashMap<String, String>) ois.readObject();
			ds.archivedMonths = (ASMonth[]) ois.readObject();
			ois.close();
			fis.close();
		}catch(Exception e){	
			e.printStackTrace();
		}
		return ds;
	}

	
/*
	public boolean saveTreadSuccessoFile(String filename){
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
	
	*/
	
}
