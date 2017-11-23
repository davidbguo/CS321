
/*
 * Our enum class to define specific types of TaskTypes
 * ENUMS: with hours suggested linked to each type
 * 			READING 
 * 				value depends on # of pages, default 2 hours
 * 			PRESENTATION
 * 				value depends on required length of presentation itself, default 4 hours
 * 			QUEST
 * 				value depends on credit hours of class, default 9 hours
 * 			QUIZ
 * 				value depends on credit hours of class, default 6 hours
 * 			TEST
 * 				value depends on credit hours of class, default 12 hours
 * 			ESSAY
 * 				value depends on work count required, default 4 hours
 * 			STUDY, normal studying through out the week for a class
 * 				value depends on credit hours of class, default 9 hours
 * 			PRELAB
 * 				value is depends on hours length of labtime, default is 0.75 hours
 * 			PROBLEMSET
 * 				value depends on # of problems, default 5 hours
 * 			PROJECT
 * 				Due date over two weeks won't be tracked otherwise value always 15
 * Variables: all used to created hours estimated
 * 			int pages
 * 			int wordCount
 * 			int creditHour
 * 			int problems
 * 			double time
 * Methods:
 * 			setTime(double), technically don't need to be used, since vars are accessible by all
 * 			setPageNumber(int), technically don't need to be used, since vars are accessible by all
 * 			setWordCount(int), technically don't need to be used, since vars are accessible by all
 * 			setAttributes(double, int, int), technically don't need to be used, since vars are accessible by all
 * 			double getHourRequired(), calculated suggested hours
 * For FrontEnd:
 * 			You likely shouldn't need to use any code directly from this class. This is for LogicLayer.
 * 			However, to be aware of all the types of UserTasks we are trying to support and provide ways 
 * 			for the user to properly input the necessary extra information needed to calculate an accurate hours suggested
 */

public enum TaskTypeEnum {
					//modifying attributes
	READING,		//pages the user must read
	PRESENTATION,	//duration of presentation
	QUEST,			//duration of the quest
	QUIZ,			//duration of the quiz
	TEST,			//duration of the test
	ESSAY,			//word count of the essay
	STUDY,			//credit hours
	PRELAB, 		//duration of the lab
	PROBLEMSET,		//number question 6 question per hour // adapt to user
	PROJECT;		// > two weeks won't be track otherwise 10 hours per week
	//protected double hoursFactor=0;
	protected int pages=0;	//pages for reading
	protected int wordCount=0; //word count for essay
	protected double time = 0;
	protected int creditHour = 0;
	protected int problems = 0;
	public void setTime(double time){
		this.time = time;
	}
	public void setPageNumer(int pages){
		this.pages = pages;
	}
	public void setWordCount(int wordCount){
		this.wordCount = wordCount;
	}
	public void setAttributes(double time, int pages, int wordCount){
		this.time = time;
		this.pages = pages;
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
				if(time==0)
					s = 3;
				else
					s = 3 * this.creditHour;
				break;
			case PROBLEMSET:
				if(this.problems == 0)
					s = 5;
				else
					s = this.problems/6;
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
