import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;	
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.*;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JList;

import java.util.HashMap;

/**
 *
 * @author aasan
 */
public class View implements ListSelectionListener, ActionListener {

   DefaultTableModel model;
   JTable table;
   Calendar cal = new GregorianCalendar();
   JLabel label;         //label for month and year
   JPanel panel;         //
   JScrollPane pane;     //contains table
   JTextArea displayField;
   HashMap<Integer, ASDay> currCalendar;
   LogicLayer llayer = new LogicLayer();
	//USED FOR MAKING JCOMBOBOXES
   String[] hourOptions={"00",
   		"01","02","03","04","05","06","07","08","09","10",
   		"11","12","13","14","15","16","17","18","19","20",
   		"21","22","23"};     
   String[] minOptions={"00",
   		"01","02","03","04","05","06","07","08","09","10",
   		"11","12","13","14","15","16","17","18","19","20",
   		"21","22","23","24","25","26","27","28","29","30",
   		"31","32","33","34","35","36","37","38","39","40",
   		"41","42","43","44","45","46","47","48","49","50",
   		"51","52","53","54","55","56","57","58","59"};
   String[] monthOptions = {"01","02","03","04","05","06","07","08","09","10",
   		"11","12"};
   String[] dayOptions={
   		"01","02","03","04","05","06","07","08","09","10",
   		"11","12","13","14","15","16","17","18","19","20",
   		"21","22","23","24","25","26","27","28","29","30","31"};
   String[] yearOptions = {"2017", "2018", "2019", "2020", "2021"};

	//temp for testing
   public static void setupLogicLayer(LogicLayer llayer) {
      System.out.println("inside setup");
      ArrayList<PreExistTask> petList = new ArrayList<PreExistTask>();
   
   
   	//petList.add(new PreExistTask("MONDAY SLEEP", DayOfWeek.MONDAY, LocalTime.of(0, 0), LocalTime.of(9, 0), null));
      petList.add(new PreExistTask("MONDAY PET1", DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0), null));
      petList.add(new PreExistTask("MONDAY PET2", DayOfWeek.MONDAY, LocalTime.of(17, 0), LocalTime.of(22, 0), null));
      petList.add(new PreExistTask("TUESDAY SLEEP", DayOfWeek.TUESDAY, LocalTime.of(0, 0), LocalTime.of(9, 0), null));
      petList.add(new PreExistTask("TUESDAY PET1", DayOfWeek.TUESDAY, LocalTime.of(12, 0), LocalTime.of(16, 0), null));
      petList.add(new PreExistTask("TUESDAY PET2", DayOfWeek.TUESDAY, LocalTime.of(18, 0), LocalTime.of(21, 0), null));
      petList.add(new PreExistTask("WEDNESDAY SLEEP", DayOfWeek.WEDNESDAY, LocalTime.of(0, 0), LocalTime.of(9, 0), null));
      petList.add(new PreExistTask("WEDNESDAY PET1", DayOfWeek.WEDNESDAY, LocalTime.of(9, 0), LocalTime.of(11, 0), null));
      petList.add(new PreExistTask("WEDNESDAY PET2", DayOfWeek.WEDNESDAY, LocalTime.of(13, 0), LocalTime.of(17, 0), null));
      petList.add(new PreExistTask("THURSDAY SLEEP", DayOfWeek.THURSDAY, LocalTime.of(0, 0), LocalTime.of(9, 0), null));
      petList.add(new PreExistTask("THURSDAY PET1", DayOfWeek.THURSDAY, LocalTime.of(17, 0), LocalTime.of(18, 0), null));
      petList.add(new PreExistTask("THURSDAY PET2", DayOfWeek.THURSDAY, LocalTime.of(22, 0), LocalTime.of(23, 0), null));
      petList.add(new PreExistTask("FRIDAY SLEEP", DayOfWeek.FRIDAY, LocalTime.of(0, 0), LocalTime.of(9, 0), null));
      petList.add(new PreExistTask("FRIDAY PET1", DayOfWeek.FRIDAY, LocalTime.of(11, 30), LocalTime.of(16, 45), null));
      petList.add(new PreExistTask("FRIDAY PET2", DayOfWeek.FRIDAY, LocalTime.of(21, 0), LocalTime.of(23, 59), null));
      petList.add(new PreExistTask("SATURDAY SLEEP", DayOfWeek.SATURDAY, LocalTime.of(0, 0), LocalTime.of(9, 0), null));
      petList.add(new PreExistTask("SATURDAY PET1", DayOfWeek.SATURDAY, LocalTime.of(18, 0), LocalTime.of(18, 13), null));
      petList.add(new PreExistTask("SATURDAY PET2", DayOfWeek.SATURDAY, LocalTime.of(21, 0), LocalTime.of(22, 45), null));
      petList.add(new PreExistTask("SUNDAY SLEEP", DayOfWeek.SUNDAY, LocalTime.of(0, 0), LocalTime.of(9, 0), null));
      petList.add(new PreExistTask("SUNDAY PET1", DayOfWeek.SUNDAY, LocalTime.of(14, 0), LocalTime.of(16,29), null));
      petList.add(new PreExistTask("SUNDAY PET2", DayOfWeek.SUNDAY, LocalTime.of(22, 0), LocalTime.of(23, 30), null));
   
   
      llayer.data.preExistTaskList = petList;
      UserTask ut1 = new UserTask("ut1", LocalDateTime.now(), LocalDateTime.now().plusDays(3), TaskTypeEnum.QUEST);
      UserTask ut2 = new UserTask("ut2", LocalDateTime.now(), LocalDateTime.of(2017, 11, 25, 23, 59),TaskTypeEnum.QUIZ);
      UserTask ut3 = new UserTask("ut3", LocalDateTime.now().plusDays(7), LocalDateTime.of(2017, 12, 10, 23, 59),TaskTypeEnum.TEST);
      ut1.hoursLeft = 20;
      ut2.hoursLeft = 20;
      ut3.hoursLeft = 20;
      ArrayList<UserTask> utList = new ArrayList<UserTask>();
      utList.add(ut1);
      utList.add(ut2);
      utList.add(ut3);
      llayer.data.priorityUserTaskList = utList;
      llayer.data.userData.put("semStartYear", ""+LocalDate.now().getYear());
      llayer.data.userData.put("semStartMonth", ""+LocalDate.now().getMonthValue());
      llayer.data.userData.put("semStartDay", ""+1);
      llayer.data.userData.put("semEndYear", ""+LocalDate.now().plusMonths(3).getYear());
      llayer.data.userData.put("semEndMonth", ""+LocalDate.now().plusMonths(3).getMonthValue());
      llayer.data.userData.put("semEndDay", ""+LocalDate.now().plusMonths(3).getDayOfMonth());
   
   }  


   View() {
      label = new JLabel();
      label.setHorizontalAlignment(SwingConstants.CENTER);
   
      JButton b1 = new JButton("<-");
      b1.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent ae) {
                  cal.add(Calendar.MONTH, -1);
                  updateMonth();
               }
            });
   
      JButton b2 = new JButton("->");
      b2.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent ae) {
                  cal.add(Calendar.MONTH, +1);
                  updateMonth();
               }
            });
   
      panel = new JPanel();
      panel.setLayout(new BorderLayout());
      panel.add(b1,BorderLayout.WEST);
      panel.add(label ,BorderLayout.CENTER);
      panel.add(b2,BorderLayout.EAST);
   
      String [] columns = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
      model = new DefaultTableModel(null,columns);
      table = new JTable(model);
      table.setRowHeight(100);
      pane = new JScrollPane(table);
   
   	//Register this object as a listSelectionListener
      table.getSelectionModel().addListSelectionListener(this);
      table.getColumnModel().getSelectionModel().addListSelectionListener(this);
   
   	//Initial display field at the bottom of the calendar
      displayField = new JTextArea();
      displayField.setSize(900,400);
      displayField.setText(" ");
      this.updateMonth();
   
   }

   void updateMonth() {
   
   	/*The GregorianCalendar extends the abstract class Calendar.
      This sets the Day of the month of calendar to the 1st. This is used to 
      determine on which Day of the week the 1st falls.*/
      cal.set(Calendar.DAY_OF_MONTH, 1);
   
   	/*Get month and year and set the month and year label*/
      String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
      int year = cal.get(Calendar.YEAR);
      label.setText(month + " " + year);
   
   	//Get the Day of the week. Number of Days in the current month
   	//and number of the weeks in the month to set number of rows.
      int startDay = cal.get(Calendar.DAY_OF_WEEK);
      int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
      int weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
   
   	//Set the row count using the number of weeks in the month
      model.setRowCount(0);
      model.setRowCount(weeks);
   
   
      int i = startDay-1;     //Because the cells are numbered 0-(n-1) (not 1-n)
      for(int day=1;day<=numberOfDays;day++){
         model.setValueAt(day, i/7 , i%7 );    
         i = i + 1;
      }
   
   }

   @Override
   public void valueChanged(ListSelectionEvent se) {
      this.currCalendar = this.llayer.getCalendarData();
      int row = table.getSelectionModel().getLeadSelectionIndex();
      int rowSize = table.getRowCount();
      System.out.println("ROW COUNT: " + rowSize);
      int col = table.getColumnModel().getSelectionModel().getLeadSelectionIndex();
      System.out.println(row + " " + col);
   
      if(rowSize > row && row >= 0 && col >= 0) {
         Integer day = (Integer) table.getValueAt(row, col);
         if(day != null) {
            String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG_FORMAT, Locale.US);
            int year = cal.get(Calendar.YEAR);
            displayField.setText(month + " " + day + ", " + year);
         
         	//Build a int key out of the date corresponding to the chosen cell
         	//Format: 4 digit year, followed by 2 digit month, followed by 2 digit Day.
         	//Example: Dec 23, 2017 -> 20171223
         	//Example: Jan 1, 2017 -> 20170101
         	//Thus, each key will be unique and 8 digits long
            int key = (year*10000) + (((cal.get(Calendar.MONTH))+1)*100) + day;
            System.out.println(key);
         
         	//Now use this key to retrieve the schedule information pertaining to
         	//the Day chosen.
            if(currCalendar.containsKey(key)) {
               ASDay chosenASDay = currCalendar.get(key);
               displayField.append("\n");
               displayField.append(chosenASDay.toString());
            }
         }
      
         System.out.println(row + " " + col + " " + day);
      }
   }

	/**
	 * Creates returns JMenuBar containing all available functions.
	 * @return a JMenuBar
	 */
   private JMenuBar createMenuBar() {
   	//Declare Variable names and structure of menu
   
   	//The menu bar
      JMenuBar menuBar;
   
   	//Individual menus
      JMenu MENU;
   
   	//menu1 and menu2 menu items
      JMenuItem createNewTask, viewTasks, m2Item1, m2Item2,createPreTask,editTask,editPreTask,deleteTask,deletePreTask,readFromFile,writeToFile,setUpSemester;
   
   	//Initialize menu items
      createNewTask = new JMenuItem("Create User Task");
      createNewTask.addActionListener(this);
      createPreTask = new JMenuItem("Create Pre-Existing Task");
      createPreTask.addActionListener(this);
      editTask = new JMenuItem("Edit User Task");
      editTask.addActionListener(this);
      editPreTask = new JMenuItem("Edit Pre-Existing Task");
      editPreTask.addActionListener(this);
      deleteTask = new JMenuItem("Delete User Task");
      deleteTask.addActionListener(this);
      deletePreTask = new JMenuItem("Delete Pre-Existing Task");
      deletePreTask.addActionListener(this);
   
      readFromFile = new JMenuItem("Read From File");
      readFromFile.addActionListener(this);
      writeToFile = new JMenuItem("Save To File");
      writeToFile.addActionListener(this);
   
      setUpSemester = new JMenuItem("Edit Semester Start/End Dates");
      setUpSemester.addActionListener(this);
   
   
      viewTasks = new JMenuItem("View tasks");
      viewTasks.addActionListener(this);
      m2Item1 = new JMenuItem("m2Item1");
      m2Item2 = new JMenuItem("m2Item2");
   
   	//Initialize menus and menu items
      MENU = new JMenu("MENU");
      MENU.add(createNewTask);
      MENU.add(createPreTask);
      MENU.add(editTask);
      MENU.add(editPreTask);
      MENU.add(deleteTask);
      MENU.add(deletePreTask);
      MENU.add(viewTasks);
      MENU.add(writeToFile);
      MENU.add(readFromFile);
      MENU.add(setUpSemester);
   
   	//Initialize menu bar and add menus
      menuBar = new JMenuBar();
      menuBar.add(MENU);
   
      return menuBar;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
   	//Check for which menu item fired the event and do something
      if(e.getActionCommand().equals("Create User Task")) {
         createNewTask();
         currCalendar = llayer.getCalendarData();
      }
      else if(e.getActionCommand().equals("View tasks")) { 
         viewTasks();
         currCalendar = llayer.getCalendarData();
      }
      else if(e.getActionCommand().equals("Create Pre-Existing Task")) {
         createPreTask();
         currCalendar = llayer.getCalendarData();
      }
      else if(e.getActionCommand().equals("Edit User Task")) {
         editTask();
         currCalendar = llayer.getCalendarData();
      }
      else if(e.getActionCommand().equals("Edit Pre-Existing Task")) {
         editPreTask();
         currCalendar = llayer.getCalendarData();
      }
      else if(e.getActionCommand().equals("Delete User Task")) {
         deleteTask();
         currCalendar = llayer.getCalendarData();
      }
      else if(e.getActionCommand().equals("Delete Pre-Existing Task")) {
         deletePreTask();
         currCalendar = llayer.getCalendarData();
      }
      else if (e.getActionCommand().equals("Edit Semester Start/End Dates")) {
         this.setUpSemester();
         currCalendar = llayer.getCalendarData();
      }
      else if(e.getActionCommand().equals("Save To File")) {
         this.writeToFile();
         currCalendar = llayer.getCalendarData();
      }
      else if(e.getActionCommand().equals("Read From File")) {
         this.readFromFile();
         currCalendar = llayer.getCalendarData();
      }
   
   
   }

	//SET UP THE DURATION OF THE SEMESTER BEGIN DATE TO END DATE.
   private void setUpSemester() {
      System.out.println("SET UP SEMESTER");
      JFrame semesterFrame = new JFrame("Set Up Semester Duration");
      semesterFrame.setSize(600, 150);
      semesterFrame.setVisible(true);
   
   	//Set up content pane
      JPanel panel = new JPanel(new GridLayout(0, 4));
   
   	//Set up labels
      JLabel startLabel = new JLabel("Semester Start Date:  ");
      JLabel monthLabel = new JLabel("Month");
      JLabel dayLabel = new JLabel("Day");
      JLabel yearLabel = new JLabel("Year");
      JLabel emptyLabel = new JLabel(" ");
      JLabel endLabel = new JLabel("Semester End Date:  ");
   
   	//Set up Combo boxes
      JComboBox startMonth = new JComboBox(monthOptions);
      JComboBox endMonth = new JComboBox(monthOptions);
      JComboBox startDay = new JComboBox(dayOptions);
      JComboBox endDay = new JComboBox(dayOptions);
      JComboBox startYear = new JComboBox(yearOptions);
      JComboBox endYear = new JComboBox(yearOptions);
   
   	//Add the labels and fields to panel
      panel.add(emptyLabel);
      panel.add(monthLabel);
      panel.add(dayLabel);
      panel.add(yearLabel);
      panel.add(startLabel);
      panel.add(startMonth);
      panel.add(startDay);
      panel.add(startYear);
      panel.add(endLabel);
      panel.add(endMonth);
      panel.add(endDay);
      panel.add(endYear);
   
   	//Add a close button
      JButton submitButton = new JButton("Submit");
      submitButton.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent e){
                  semesterFrame.dispose();
               	//For start day
                  String startYearStr = (String) startYear.getSelectedItem();
                  String startMonthStr = (String) startMonth.getSelectedItem();
                  String startDayStr = (String) startDay.getSelectedItem();
               
               	//For end date
                  String endYearStr = (String) endYear.getSelectedItem();
                  String endMonthStr = (String) endMonth.getSelectedItem();
                  String endDayStr = (String) endDay.getSelectedItem();
               
               	//Send info to llayer
                  llayer.addUserData("semStartYear", startYearStr);
                  llayer.addUserData("semStartMonth", startMonthStr);
                  llayer.addUserData("semStartDay", startDayStr);
                  llayer.addUserData("semEndYear", endYearStr);
                  llayer.addUserData("semEndMonth", endMonthStr);
                  llayer.addUserData("semEndDay", endDayStr);
                  llayer.setCurrentDays();
                  llayer.createBreakdown();
                  llayer.updateDataCalendar();  
               }
            });
   
      panel.add(submitButton);
   
   	//Add panel to frame
      semesterFrame.setContentPane(panel);
   
   }

	//EDIT A PRE-EXISTING TASK
   private void editPreTask()
   {
   	//SET UP FRAME
      JFrame frame = new JFrame("Edit Pre-Existing Task");
      frame.setVisible(true);
      frame.setSize(500, 500);
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(7,1));
   	//CONVERT ARRAYLIST INTO ARRAY
      PreExistTask[] list = new PreExistTask[llayer.getPETList().size()];
      for(int i=0;i<llayer.getPETList().size();i++)//fill the list
         list[i] = llayer.getPETList().get(i);
      JList taskList = new JList(list);
   
   	//PANEL FOR NAME
      JPanel sub1 = new JPanel();
      JLabel nameLabel = new JLabel("Enter task name");
      JTextField nameBox = new JTextField(10);
      sub1.add(nameLabel);
      sub1.add(nameBox);
   
   	//PANEL FOR DAY
      JPanel sub2 = new JPanel();
      JLabel dayLabel = new JLabel("Select which day");
      String[] daysofweek = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
      JComboBox dayBox = new JComboBox(daysofweek);
      sub2.add(dayLabel);
      sub2.add(dayBox);
   
   	//PANEL FOR START TIME
      JPanel sub3 = new JPanel();
      JLabel startLabel = new JLabel("Enter start time (--:--)");
      JComboBox starthours = new JComboBox(hourOptions);
      JComboBox startmins = new JComboBox(minOptions);
      sub3.add(startLabel);
      sub3.add(starthours);
      sub3.add(startmins);
   
   	//PANEL FOR END TIME
      JPanel sub4 = new JPanel();
      JLabel endLabel = new JLabel("Enter end time (--:--)");
      JComboBox endhours = new JComboBox(hourOptions);
      JComboBox endmins = new JComboBox(minOptions);
      sub4.add(endLabel);
      sub4.add(endhours);
      sub4.add(endmins);
   	/*   
   //PANEL FOR END DATE
      JPanel sub5 = new JPanel();
      JLabel lastLabel = new JLabel("Enter end date (MM/DD/YYYY)");
      JComboBox months = new JComboBox(monthOptions);
      JComboBox days = new JComboBox(dayOptions);
      String[] yearOptions={"2017","2018","2019","2020"};
      JComboBox years = new JComboBox(yearOptions);
      sub5.add(lastLabel);
      sub5.add(months);
      sub5.add(days);
      sub5.add(years);
   	 */
   	//PANEL FOR BUTTON W/ ACTION LISTENER TO EDIT PRETASK
      JPanel sub6 = new JPanel();
      JButton addButton = new JButton("Edit Pre-Existing Task");
      addButton.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent e){
                  String taskName = nameBox.getText();
                  String day = (String)dayBox.getSelectedItem();
                  int startHour = Integer.parseInt((String)starthours.getSelectedItem());
                  int startMin = Integer.parseInt((String)startmins.getSelectedItem());
                  int endHour = Integer.parseInt((String)endhours.getSelectedItem());
                  int endMin = Integer.parseInt((String)endmins.getSelectedItem());
               	//int lastYear = Integer.parseInt((String)years.getSelectedItem());
               	//int lastMonth = Integer.parseInt((String)months.getSelectedItem());
               	//int lastDay = Integer.parseInt((String)days.getSelectedItem());
                  System.out.println("Start hour:" + startHour+ " Start min" + startMin);
                  LocalTime startTime = LocalTime.of(startHour,startMin);
                  LocalTime endTime = LocalTime.of(endHour,endMin);
               	//LocalDateTime endDay = LocalDateTime.of(lastYear,lastMonth,lastDay,endHour,endMin);
                  LocalDateTime endDay = null;
                  PreExistTask task = (PreExistTask)taskList.getSelectedValue();
                  switch(day)
                  {
                     case "Monday":
                        llayer.editPreExistTask(task,taskName,DayOfWeek.MONDAY,startTime,endTime,endDay);
                        break;
                     case "Tuesday":
                        llayer.editPreExistTask(task,taskName,DayOfWeek.TUESDAY,startTime,endTime,endDay);
                        break;
                     case "Wednesday":
                        llayer.editPreExistTask(task,taskName,DayOfWeek.WEDNESDAY,startTime,endTime,endDay);
                        break;
                     case "Thursday":
                        llayer.editPreExistTask(task,taskName,DayOfWeek.THURSDAY,startTime,endTime,endDay);
                        break;
                     case "Friday":
                        llayer.editPreExistTask(task,taskName,DayOfWeek.FRIDAY,startTime,endTime,endDay);
                        break;
                     case "Saturday":
                        llayer.editPreExistTask(task,taskName,DayOfWeek.SATURDAY,startTime,endTime,endDay);
                        break;
                     case "Sunday":
                        llayer.editPreExistTask(task,taskName,DayOfWeek.SUNDAY,startTime,endTime,endDay);
                        break;
                  }
                  frame.dispose();
               }
            });     
      sub6.add(addButton);
   
   	//ADD COMPONENTS TO PANEL
      JScrollPane taskListScrollPane = new JScrollPane(taskList);
      panel.add(taskListScrollPane);
      panel.add(sub1);
      panel.add(sub2);
      panel.add(sub3);
      panel.add(sub4);
   	//panel.add(sub5);
      panel.add(sub6);
      frame.add(panel);
   }

	//EDIT USER TASK
   private void editTask()
   {
      JFrame frame = new JFrame("Edit User Task");
      frame.setVisible(true);
      frame.setSize(600, 600);
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(7,1));
   
   	//CREATE JLIST FOR UT
      UserTask[] list = new UserTask[llayer.getUTList().size()];
      for(int i=0;i<llayer.getUTList().size();i++)//fill the list
         list[i] = llayer.getUTList().get(i);
      JList taskList = new JList(list);
      taskList.setFont(new Font("Arial",Font.BOLD,10));
   
   	//PANEL FOR DATE
      JPanel subpanel1 = new JPanel();
      JComboBox months = new JComboBox(monthOptions);
      JComboBox days = new JComboBox(dayOptions);
      String[] yearOptions={"2017","2018","2019","2020"};
      JComboBox years = new JComboBox(yearOptions);
      subpanel1.add(new JLabel("Select due date:"));
      subpanel1.add(months);
      subpanel1.add(days);
      subpanel1.add(years);
   
   	//PANEL FOR TIME
      JPanel subpanel2 = new JPanel();
      JComboBox hours = new JComboBox(hourOptions);
      JComboBox mins = new JComboBox(minOptions);
      subpanel2.add(new JLabel("Select time due:"));
      subpanel2.add(hours);
      subpanel2.add(mins);
   
   	//PANEL FOR NAME
      JPanel subpanel3 = new JPanel();
      JLabel nameLabel = new JLabel("Enter task name:");
      JTextField nameBox = new JTextField(10);
      subpanel3.add(nameLabel);
      subpanel3.add(nameBox);
   
   	//PANEL FOR TYPE
      JPanel subpanel4 = new JPanel();
      JLabel typeLabel = new JLabel("Select type of task:");
      String[] types = {"Reading","Presentation","Quest","Quiz","Test","Essay","Study","Prelab","Problem set","Prelab"};
      JComboBox typeBox = new JComboBox(types);
      subpanel4.add(typeLabel);
      subpanel4.add(typeBox);
   
   	//PANEL FOR BUTTON
      JPanel subpanel5 = new JPanel();
   	//CREATE BUTTON TO MAKE CHANGES
      JButton button = new JButton("Make Changes");
      button.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent e){
                  String taskName = nameBox.getText();
                  String taskType = (String)typeBox.getSelectedItem();   
                  LocalDateTime start = LocalDateTime.now();
                  int year = Integer.parseInt((String)years.getSelectedItem());
                  int month = Integer.parseInt((String)months.getSelectedItem());
                  int day = Integer.parseInt((String)days.getSelectedItem());
                  int hour = Integer.parseInt((String)hours.getSelectedItem());
                  int minute = Integer.parseInt((String)mins.getSelectedItem());
                  LocalDateTime end = LocalDateTime.of(year,month,day,hour,minute);
                  UserTask task = (UserTask)taskList.getSelectedValue();
               
                  switch(taskType.toLowerCase()){
                     case "reading":
                        {
                           String pageNumbers = JOptionPane.showInputDialog("Enter number of pages");
                           TaskTypeEnum type = TaskTypeEnum.READING;
                           type.setPageNumer(Integer.parseInt(pageNumbers));
                           llayer.editUserTask(task,taskName,start,end,type);       
                           break;
                        }
                     case "presentation":
                        {
                           String duration= JOptionPane.showInputDialog("Enter duration of final presentation in minutes");
                           TaskTypeEnum type = TaskTypeEnum.PRESENTATION;
                           type.setTime(((double) Integer.parseInt(duration))/60.0);
                           llayer.editUserTask(task,taskName,start,end,type); 
                           break;
                        }
                     case "quest":
                        {
                           String creditHours= JOptionPane.showInputDialog("Enter credit hours of course");
                           TaskTypeEnum type = TaskTypeEnum.QUEST;
                           type.setCreditHour(Integer.parseInt(creditHours));
                           llayer.editUserTask(task,taskName,start,end,type); 
                           break;
                        }
                     case "quiz":
                        {
                           String creditHours= JOptionPane.showInputDialog("Enter credit hours of course");
                           TaskTypeEnum type = TaskTypeEnum.QUIZ;
                           type.setCreditHour(Integer.parseInt(creditHours));
                           llayer.editUserTask(task,taskName,start,end,type); 
                           break;
                        }
                     case "test":
                        {
                           String creditHours= JOptionPane.showInputDialog("Enter credit hours of course");
                           TaskTypeEnum type = TaskTypeEnum.TEST;
                           type.setCreditHour(Integer.parseInt(creditHours));
                           llayer.editUserTask(task,taskName,start,end,type); 
                           break;
                        }
                     case "essay":
                        {
                           String wordCount= JOptionPane.showInputDialog("Enter required word count");
                           TaskTypeEnum type = TaskTypeEnum.ESSAY;
                           type.setWordCount(Integer.parseInt(wordCount));
                           llayer.editUserTask(task,taskName,start,end,type); 
                           break;
                        }
                     case "study":
                        {
                           String credits= JOptionPane.showInputDialog("Enter number of credit hours");
                           TaskTypeEnum type = TaskTypeEnum.STUDY;
                           type.setCreditHour(Integer.parseInt(credits));
                           llayer.editUserTask(task,taskName,start,end,type);
                           break;
                        }
                     
                     case "prelab":
                        {
                           String duration= JOptionPane.showInputDialog("Enter duration of Lab in minutes");
                           TaskTypeEnum type = TaskTypeEnum.PRELAB;
                           type.setTime(((double) Integer.parseInt(duration))/60.0);
                           llayer.editUserTask(task,taskName,start,end,type); 
                           break;
                        }
                     case "problem set":
                        {
                           String numberOfQuestions= JOptionPane.showInputDialog("Enter number of questions ");
                           TaskTypeEnum type = TaskTypeEnum.PROBLEMSET;
                           type.setProblems(Integer.parseInt(numberOfQuestions));
                           llayer.editUserTask(task,taskName,start,end,type); 
                           break;
                        }
                     case "project":
                        String numberOfDays = JOptionPane.showInputDialog("Enter number of days for project");
                        TaskTypeEnum type = TaskTypeEnum.PROJECT;
                        type.setTime(Integer.parseInt(numberOfDays));
                        llayer.editUserTask(task,taskName,start,end,type); 
                        break;
                  }
                  frame.dispose();
               }
            });
      subpanel5.add(button);
   
   	//ADD COMPONENTS TO PANEL
      JScrollPane taskListScrollPane = new JScrollPane(taskList);
      panel.add(taskListScrollPane);
      panel.add(subpanel3);
      panel.add(subpanel4);
      panel.add(subpanel1);
      panel.add(subpanel2);
      panel.add(subpanel5);
      frame.add(panel);
   }

	//CREATE A PRE-EXISTING TASK
   private void createPreTask()
   {
   	//CREATE THE FRAME AND PANEL
      JFrame frame = new JFrame("Create Pre-Existing Task");
      frame.setVisible(true);
      frame.setSize(500, 500);
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(6,2));
   
   	//PANEL FOR NAME
      JPanel sub1 = new JPanel();
      JLabel nameLabel = new JLabel("Enter task name");
      JTextField nameBox = new JTextField(10);
      sub1.add(nameLabel);
      sub1.add(nameBox);
   
   	//PANEL FOR DAY
      JPanel sub2 = new JPanel();
      JLabel dayLabel = new JLabel("Select which day");
      String[] daysofweek = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
      JComboBox dayBox = new JComboBox(daysofweek);
      sub2.add(dayLabel);
      sub2.add(dayBox);
   
   	//PANEL FOR START TIME
      JPanel sub3 = new JPanel();
      JLabel startLabel = new JLabel("Enter start time (--:--)");
      JComboBox starthours = new JComboBox(hourOptions);
      JComboBox startmins = new JComboBox(minOptions);
      sub3.add(startLabel);
      sub3.add(starthours);
      sub3.add(startmins);
   
   	//PANEL FOR END TIME
      JPanel sub4 = new JPanel();
      JLabel endLabel = new JLabel("Enter end time (--:--)");
      JComboBox endhours = new JComboBox(hourOptions);
      JComboBox endmins = new JComboBox(minOptions);
      sub4.add(endLabel);
      sub4.add(endhours);
      sub4.add(endmins);
   	/* 
   //PANEL FOR END DATE
      JPanel sub5 = new JPanel();
      JLabel lastLabel = new JLabel("Enter end date (MM/DD/YYYY)");
      JComboBox months = new JComboBox(monthOptions);
      JComboBox days = new JComboBox(dayOptions);
      String[] yearOptions={"2017","2018","2019","2020"};
      JComboBox years = new JComboBox(yearOptions);
      sub5.add(lastLabel);
      sub5.add(months);
      sub5.add(days);
      sub5.add(years);*/
   
   	//PANEL FOR BUTTON W/ ACTION LISTENER TO ADD TASK
      JPanel sub6 = new JPanel();
      JButton addButton = new JButton("Add Pre-Existing Task");
      addButton.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent e){
                  String taskName = nameBox.getText();
                  String day = (String)dayBox.getSelectedItem();
                  int startHour = Integer.parseInt((String)starthours.getSelectedItem());
                  int startMin = Integer.parseInt((String)startmins.getSelectedItem());
                  int endHour = Integer.parseInt((String)endhours.getSelectedItem());
                  int endMin = Integer.parseInt((String)endmins.getSelectedItem());
               	//int lastYear = Integer.parseInt((String)years.getSelectedItem());
               	//int lastMonth = Integer.parseInt((String)months.getSelectedItem());
               	//int lastDay = Integer.parseInt((String)days.getSelectedItem());
                  System.out.println("Start hour:" + startHour+ " Start min" + startMin);
                  LocalTime startTime = LocalTime.of(startHour,startMin);
                  LocalTime endTime = LocalTime.of(endHour,endMin);
               	//LocalDateTime endDay = LocalDateTime.of(lastYear,lastMonth,lastDay,endHour,endMin);
                  LocalDateTime endDay = null;
                  switch(day)//depending on day, add appropriate preExistTask
                  {
                     case "Monday":
                        llayer.addPreExistTask(taskName,DayOfWeek.MONDAY,startTime,endTime,endDay);
                        break;
                     case "Tuesday":
                        llayer.addPreExistTask(taskName,DayOfWeek.TUESDAY,startTime,endTime,endDay);
                        break;
                     case "Wednesday":
                        llayer.addPreExistTask(taskName,DayOfWeek.WEDNESDAY,startTime,endTime,endDay);
                        break;
                     case "Thursday":
                        llayer.addPreExistTask(taskName,DayOfWeek.THURSDAY,startTime,endTime,endDay);
                        break;
                     case "Friday":
                        llayer.addPreExistTask(taskName,DayOfWeek.FRIDAY,startTime,endTime,endDay);
                        break;
                     case "Saturday":
                        llayer.addPreExistTask(taskName,DayOfWeek.SATURDAY,startTime,endTime,endDay);
                        break;
                     case "Sunday":
                        llayer.addPreExistTask(taskName,DayOfWeek.SUNDAY,startTime,endTime,endDay);
                        break;
                  }
                  frame.dispose();
               }
            });     
      sub6.add(addButton);
   
   	//ADD COMPONENTS TO PANEL
      panel.add(sub1);
      panel.add(sub2);
      panel.add(sub3);
      panel.add(sub4);
   	//panel.add(sub5);
      panel.add(sub6);
      frame.add(panel);
   }

   private void createNewTask() {
   	//CREATE FRAME AND PANEL
      JFrame frame = new JFrame("Create User Task");
      frame.setVisible(true);
      frame.setSize(400, 400);
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(5,1));
   
   	//PANEL FOR DATE
      JPanel subpanel1 = new JPanel();
      JComboBox months = new JComboBox(monthOptions);
      JComboBox days = new JComboBox(dayOptions);
      String[] yearOptions={"2017","2018","2019","2020"};
      JComboBox years = new JComboBox(yearOptions);
      subpanel1.add(new JLabel("Select due date:"));
      subpanel1.add(months);
      subpanel1.add(days);
      subpanel1.add(years);
   
   	//PANEL FOR TIME
      JPanel subpanel2 = new JPanel();
      JComboBox hours = new JComboBox(hourOptions);
      JComboBox mins = new JComboBox(minOptions);
      subpanel2.add(new JLabel("Select time due:"));
      subpanel2.add(hours);
      subpanel2.add(mins);
   
   	//PANEL FOR NAME
      JPanel subpanel3 = new JPanel();
      JLabel nameLabel = new JLabel("Enter task name:");
      JTextField nameBox = new JTextField(10);
      subpanel3.add(nameLabel);
      subpanel3.add(nameBox);
   
   	//PANEL FOR TASK TYPE
      JPanel subpanel4 = new JPanel();
      JLabel typeLabel = new JLabel("Select type of task:");
      String[] types = {"Reading","Presentation","Quest","Quiz","Test","Essay","Study","Prelab","Problem set","Project"};
      JComboBox typeBox = new JComboBox(types);
      subpanel4.add(typeLabel);
      subpanel4.add(typeBox);
   
   	//PANEL FOR BUTTON W/ ACTION LISTENER TO ADD TASK
      JPanel subpanel5 = new JPanel();
      JButton addButton = new JButton("Add Task");
      addButton.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent e){
                  String taskName = nameBox.getText();
                  String taskType = (String)typeBox.getSelectedItem();   
                  LocalDateTime start = LocalDateTime.now();
                  int year = Integer.parseInt((String)years.getSelectedItem());
                  int month = Integer.parseInt((String)months.getSelectedItem());
                  int day = Integer.parseInt((String)days.getSelectedItem());
                  int hour = Integer.parseInt((String)hours.getSelectedItem());
                  int minute = Integer.parseInt((String)mins.getSelectedItem());
                  LocalDateTime end = LocalDateTime.of(year,month,day,hour,minute);
                  switch(taskType.toLowerCase())
                  {
                     case "reading":
                        {
                           String pageNumbers = JOptionPane.showInputDialog("Enter number of pages");
                           TaskTypeEnum type = TaskTypeEnum.READING;
                           type.setPageNumer(Integer.parseInt(pageNumbers));
                           llayer.addUserTask(taskName,start,end,type);       
                           break;
                        }
                     case "presentation":
                        {
                           String duration= JOptionPane.showInputDialog("Enter duration of final presentation in minutes");
                           TaskTypeEnum type = TaskTypeEnum.PRESENTATION;
                           type.setTime(((double) Integer.parseInt(duration))/60.0);
                           llayer.addUserTask(taskName,start,end,type); 
                           break;
                        }
                     case "quest":
                        {
                           String creditHours= JOptionPane.showInputDialog("Enter credit hours of course");
                           TaskTypeEnum type = TaskTypeEnum.QUEST;
                           type.setCreditHour(Integer.parseInt(creditHours));
                           llayer.addUserTask(taskName,start,end,type); 
                           break;
                        }
                     case "quiz":
                        {
                           String creditHours= JOptionPane.showInputDialog("Enter credit hours of course");
                           TaskTypeEnum type = TaskTypeEnum.QUIZ;
                           type.setCreditHour(Integer.parseInt(creditHours));
                           llayer.addUserTask(taskName,start,end,type); 
                           break;
                        }
                     case "test":
                        {
                           String creditHours= JOptionPane.showInputDialog("Enter credit hours of course");
                           TaskTypeEnum type = TaskTypeEnum.TEST;
                           type.setCreditHour(Integer.parseInt(creditHours));
                           llayer.addUserTask(taskName,start,end,type); 
                           break;
                        }
                     case "essay":
                        {
                           String wordCount= JOptionPane.showInputDialog("Enter required word count");
                           TaskTypeEnum type = TaskTypeEnum.ESSAY;
                           type.setWordCount(Integer.parseInt(wordCount));
                           llayer.addUserTask(taskName,start,end,type); 
                           break;
                        }
                     case "study":
                        {
                           String credits= JOptionPane.showInputDialog("Enter number of credit hours");
                           TaskTypeEnum type = TaskTypeEnum.STUDY;
                           type.setCreditHour(Integer.parseInt(credits));
                           llayer.addUserTask(taskName,start,end,type);
                           break;
                        }
                     
                     case "prelab":
                        {
                           String duration= JOptionPane.showInputDialog("Enter duration of lab in minutes");
                           TaskTypeEnum type = TaskTypeEnum.PRELAB;
                           type.setTime(((double) Integer.parseInt(duration))/60.0);
                           llayer.addUserTask(taskName,start,end,type); 
                           break;
                        }
                     case "problem set":
                        {
                           String numberOfQuestions= JOptionPane.showInputDialog("Enter number of questions ");
                           TaskTypeEnum type = TaskTypeEnum.PROBLEMSET;
                           type.setProblems(Integer.parseInt(numberOfQuestions));
                           llayer.addUserTask(taskName,start,end,type); 
                           break;
                        }
                     case "project":
                        String numberOfDays = JOptionPane.showInputDialog("Enter number of days for project");
                        TaskTypeEnum type = TaskTypeEnum.PROJECT;
                        type.setTime(Integer.parseInt(numberOfDays));
                        llayer.addUserTask(taskName,start,end,type); 
                        break;
                  }
                  frame.dispose();
               }
            });
      subpanel5.add(addButton);
   
   
   	//ADD COMPONENTS TO PANEL
      panel.add(subpanel3);
      panel.add(subpanel4);
      panel.add(subpanel1);
      panel.add(subpanel2);
      panel.add(subpanel5);
      frame.add(panel);    
   }

	//DELETE USER TASK  
   private void deleteTask() {
   	//CREATE FRAME AND PANEL
      JFrame frame = new JFrame("Delete User Task");
      frame.setVisible(true);
      frame.setSize(600, 600);
      JPanel panel = new JPanel();
   
   	//CONVERT ARRAYLIST INTO ARRAY
      UserTask[] list = new UserTask[llayer.getUTList().size()];
      for(int i=0;i<llayer.getUTList().size();i++)//fill the list
         list[i] = llayer.getUTList().get(i);
      JList taskList = new JList(list);
      taskList.setFont(new Font("Arial",Font.BOLD,10));
   
   	//CREATE BUTTON W/ ACTIONLISTENER
      JButton button = new JButton("Delete Task");
      button.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent e){
               
                  UserTask task = (UserTask)taskList.getSelectedValue();
                  llayer.deleteUserTask(task);
               
                  frame.dispose();
               }
            });
   
   	//ADD COMPONENTS TO PANEL   
      panel.add(new JLabel("Select a task to delete"));
      panel.add(taskList);
      panel.add(button);
      frame.add(panel);
   }

   private void deletePreTask()
   {
   	//CREATE PANEL AND FRAME
      JFrame frame = new JFrame("Delete Pre-Existing Task");
      frame.setVisible(true);
      frame.setSize(500, 500);
      JPanel panel = new JPanel();
   
   	//CONVERT ARRAYLIST INTO ARRAY
      PreExistTask[] list = new PreExistTask[llayer.getPETList().size()];
      for(int i=0;i<llayer.getPETList().size();i++)//fill the list
         list[i] = llayer.getPETList().get(i);
      JList taskList = new JList(list);
      taskList.setFont(new Font("Arial",Font.BOLD,10));
   
   	//CREATE BUTTON WITH ACTION LISTENER
      JButton button = new JButton("Delete Task");
      button.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent e){
               
                  PreExistTask task = (PreExistTask)taskList.getSelectedValue();
                  llayer.deletePreExistTask(task);
               
                  frame.dispose();
               }
            });
   
   	//ADD COMPONENENTS TO PANEL   
      panel.add(new JLabel("Select a Pre-Existing Task to Delete"));
      panel.add(taskList);
      panel.add(button);
      frame.add(panel);
   }

	//VIEW LISTS OF PRE-EXISTING AND USER TASKS
   private void viewTasks() {
   
      System.out.println("VIEW TASK.");
      JFrame frame = new JFrame("View Tasks");
      frame.setVisible(true);
      frame.setSize(600, 600);
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(3,2));
   
   	//CREATE JLIST FOR UT
      UserTask[] list = new UserTask[llayer.getUTList().size()];
      for(int i=0;i<llayer.getUTList().size();i++)//fill the list
         list[i] = llayer.getUTList().get(i);
      JList taskList = new JList(list);
   	//CREATE JLIST FOR PET
      PreExistTask[] plist = new PreExistTask[llayer.getPETList().size()];
      for(int i=0;i<llayer.getPETList().size();i++)//fill the list
         plist[i] = llayer.getPETList().get(i);
      JList ptaskList = new JList(plist);
      taskList.setFont(new Font("Arial",Font.BOLD,10));
      ptaskList.setFont(new Font("Arial",Font.BOLD,10));
   
      //CREATE SUB PANELS AND MAKE SCROLLABLE LISTS
      JPanel sub1 = new JPanel();
      JPanel sub2 = new JPanel();
      sub1.setLayout(new GridLayout(2,1));
      sub2.setLayout(new GridLayout(2,1));
      sub1.add(new JLabel("User Tasks:"));
      sub1.add(new JScrollPane(taskList));
      sub2.add(new JLabel("Pre-Existing Tasks"));
      sub2.add(new JScrollPane(ptaskList));
      
   	//ADD COMPONENTS TO PANEL
      panel.add(sub1);
      panel.add(sub2);
      frame.add(panel);      
   }

   public void writeToFile(){
      this.llayer.writeToFile();
   }
   public void readFromFile(){
      this.llayer.readFromFile();
   }

	/**
	 * Creates and returns a Container containing the everything the user should
	 * see.
	 */
   private Container createContentPane() {
   	//Create the main content panel
      JPanel contentPane = new JPanel(new BorderLayout());
      contentPane.setOpaque(true);
   	//Create a scrolled text area.
      JTextArea output = new JTextArea(5, 30);
      output.setEditable(false);
      JScrollPane scrollPane = new JScrollPane(output);
   
   	//Add the text area to the content pane.
      contentPane.add(scrollPane, BorderLayout.CENTER);
   
   	//TODO ADD CALENDAR AND A TEXT AREA UNDERNEATH IT TO DISPLAY CONTENTS OF
   	//CELLS
   
      return contentPane;
   }

   private void setUpCalendar() {
      setupLogicLayer(llayer);
      llayer.setCurrentDays();
      llayer.createBreakdown();
      currCalendar = llayer.getCalendarData();
   }

   private static void createAndShowGUI() {
   	//Create and set up the window.
      JFrame frame = new JFrame("Calendar Application");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
   	//Create and set up the content pane
      View view = new View();
      frame.setJMenuBar(view.createMenuBar());
      view.setUpCalendar();
   	//frame.setContentPane(view.createContentPane());
   
   	//Set frame size and then show
      frame.setSize(900, 900);
      frame.setVisible(true);
   
      frame.add(view.panel,BorderLayout.NORTH);
      frame.add(view.pane,BorderLayout.CENTER);
      frame.add(view.displayField, BorderLayout.SOUTH);
   
   }

	/**
	 * @param args the command line arguments
	 */

   public static void main(String[] args) {
   	//Schedule a job for the event-dispatching thread:
   	//creating and showing this application's GUI.
      javax.swing.SwingUtilities.invokeLater(
            new Runnable() {
               public void run() {
                  createAndShowGUI();
               }
            });
   }

}
