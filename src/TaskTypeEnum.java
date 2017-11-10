
public enum TaskTypeEnum {
	PROBLEMSET, HOMEWORK, ESSAY, PROJECT;
	protected int hours;
	
	public void updateHours(int hours){
		this.hours = hours;
	}
}
