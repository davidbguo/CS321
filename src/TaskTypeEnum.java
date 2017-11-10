
public enum TaskTypeEnum {
					//modifying attributes
	READING,		//pages the user must read
	PRESENTATION,	//duration of presentation
	QUEST,			//duration of the quest
	QUIZ,			//duration of the quiz
	TEST,			//duration of the test
	ESSAY,			//word count of the essay
	STUDY,			//
	PROBLEMSET,		//
	HOMEWORK,		//
	PROJECT,		//
	TOPIC_RESEARCH,	//
	PRELAB;			//duration of the lab
	//protected double hoursFactor=0;
	protected int pages=0;	//pages for reading
	protected int wordCount=0; //word count for essay
	protected double time = 0;
	
	public void setTime(double time){
		this.time = time;
	}
	public void setPageNumer(int pages){
		this.pages = pages;
	}
	public void setWordCount(int wordCount){
		this.wordCount = wordCount;
	}

	/*
	public void updateHourFactor(int hours){
		this.hoursFactor = hours;
	}
	*/
	public double getHourRequired(){
		double s;
		switch(this){
			case TOPIC_RESEARCH:
				s = 4;	//default of 4 hours
				break;
			case READING:
				if(pages>0)
					s = (double) ((1/60.0)*pages); //1 page per minute
				else
					s = 2; //else 2 hours of reading
				break;
			case PRESENTATION:
				if(time==0) //default 4 hours
					s = 4; 
				else
					s = time * 10; //user inputed time *10
				break;
			case ESSAY:
				if(this.wordCount==0)
					s = 4;
				else
					s = ((this.wordCount)/5.5)/60; // using information from rice.edu 
							//at a pace of 5.5 wpm(words per minute)
				break;
			case QUEST:
				if(time==0)
					s = 6;
				else
					s = 4*time;
				break;
			case QUIZ:
				if(time==0)
					s = 6;
				else
					s = 2*time;
				break;
			case TEST:
				if(time==0)
					s = 12;
				else
					s = 4*time;
				break;
			case STUDY:
				s = 4;
				break;
			case HOMEWORK:
				s = 4;
				break;
			case PROBLEMSET:
				s = 4;
				break;
			case PROJECT:
				s = 4;
				break;
			case PRELAB:
				if(time==0)
					s = .75; //default set at  45 mins
				else
					s = .75*time;
			default:
				s = 0;
			}
		return s;
	}
}
