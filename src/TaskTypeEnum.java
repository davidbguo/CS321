
public enum TaskTypeEnum {
	TOPIC_RESEARCH,
	READING,
	PRESENTATION,
	PROBLEM_SET,
	ESSAY_LONG,
	ESSAY_SHORT,
	QUEST,
	QUIZ,
	TEST,
	STUDY,
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
			case TOPIC_RESEARCH:
				s = 4;
				break;
			case READING:
				s = 4;
				break;
			case PRESENTATION:
				s = 4;
				break;
			case PROBLEM_SET:
				s = 4;
				break;
			case ESSAY_LONG:
				s = 4;
				break;
			case ESSAY_SHORT:
				s = 4;
				break;
			case QUEST:
				s = 4;
				break;
			case QUIZ:
				s = 4;
				break;
			case TEST:
				s = 4;
				break;
			case STUDY:
				s = 4;
				break;
			case HOMEWORK:
				s = 4;
				break;
			case ESSAY:
				s = 4;
				break;
			case PROBLEMSET:
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
