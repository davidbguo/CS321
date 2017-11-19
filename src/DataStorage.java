import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DataStorage {
	//protected Event [] currentEvents;
	protected ArrayList<PreExistTask> preExistTaskList = new ArrayList<PreExistTask>();
	protected ArrayList<UserTask> priorityUserTaskList = new ArrayList<UserTask>();
	protected HashMap<String, String> userData = new HashMap<String, String>();
	protected ArrayList<ASMonth> archivedMonths = new ArrayList<ASMonth>();
	protected ArrayList<ASDay> currentDays = new ArrayList<ASDay>();
	protected ASCalendar calendar = new ASCalendar();
	
	
	//constructor
	public DataStorage(){}
	
	public void deletePreExistTask(int i){
		this.preExistTaskList.remove(i);
	}
	public void deleteUserTask(int i){
		this.priorityUserTaskList.remove(i);
	}
	public void editPreExistTask(int i,PreExistTask newTask){
		this.preExistTaskList.remove(i);
		preExistTaskList.add(i, newTask);	
	}
	public void editUserTask(int i,UserTask newTask){
		this.priorityUserTaskList.remove(i);
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
			oos.writeObject(this.currentDays);
			oos.writeObject(this.calendar);
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
			ds.archivedMonths = (ArrayList<ASMonth>) ois.readObject();
			ds.currentDays = (ArrayList<ASDay>) ois.readObject();
			ds.calendar = (ASCalendar) ois.readObject();
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
