
public enum TaskTypeEnum {
	PROBLEMSET,
	HOMEWORK,
	ESSAY,
	PROJECT;
	protected int hoursFactor;
	
	public void updateHourFactor(int hours){
		this.hoursFactor = hours;
	}
	
	public int getHourFactor(){
		int s;
		switch(this){
			case PROBLEMSET:
				s = 4;
				break;
			case HOMEWORK:
				s = 4;
				break;
			case ESSAY:
				s = 4;
				break;
			case PROJECT:
				s = 4;
				break;
			default:
				s = 0;
			}
		return s;
	}
}
