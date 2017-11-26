import java.io.*;
import java.nio.file.*;
import java.util.*;

/*
 * Class to hold all data stored, unfortunately not persisted beyond a singular run of the application
 * Variables:
 * 			ArrayList<PreExistTask> preExistTaskList
 * 			ArrayList<UserTask> priorityUserTaskList
 * 			HashMap<String, String> userData, place for miscellaneous data about the user like semester start and end date
 * 			ArrayList<ASMonth> archivedMonths, not used
 * 			ArrayList<ASDay> currentDays, main store of data to be manipulated
 * 			ASCalendar calendar, final output based on currentDays[]
 * Methods
 * 			deletePreExistTask(int)
 * 			deleteUserTask(int)
 * 			editPreExistTask(int)
 * 			editUserTask(int)
 * 			boolean saveToFile(String), not used in final product
 * 			DataStorage readFromFile(String), not used in final product
 * For FrontEnd
 * 			You should never call anything from here. This layer should only be used for LogicLayer
 * 			
 */



public class DataStorage {
	protected ArrayList<PreExistTask> preExistTaskList = new ArrayList<PreExistTask>();
	protected ArrayList<UserTask> priorityUserTaskList = new ArrayList<UserTask>();
	protected HashMap<String, String> userData = new HashMap<String, String>();
	protected ArrayList<ASMonth> archivedMonths = new ArrayList<ASMonth>();
	protected ArrayList<ASDay> currentDays = new ArrayList<ASDay>();
	protected ASCalendar calendar = new ASCalendar();
	

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
	public DataStorage readFromFile(String filename){
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
	
}
