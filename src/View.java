import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;
import java.time.DayOfWeek;
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
  
   //temp for testing
   public static void setupLogicLayer(LogicLayer llayer) {
      System.out.println("inside setup");
      ArrayList<PreExistTask> petList = new ArrayList<PreExistTask>();
      /*
      
      petList.add(new PreExistTask("MONDAY SLEEP", DayOfWeek.MONDAY, LocalTime.of(0, 0), LocalTime.of(9, 0), null));
      petList.add(new PreExistTask("MONDAY PET1", DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(2, 0), null));
      petList.add(new PreExistTask("MONDAY PET2", DayOfWeek.MONDAY, LocalTime.of(17, 0), LocalTime.of(22, 0), null));
      petList.add(new PreExistTask("TUESDAY SLEEP", DayOfWeek.TUESDAY, LocalTime.of(0, 0), LocalTime.of(9, 0), null));
      petList.add(new PreExistTask("TUESDAY PET1", DayOfWeek.TUESDAY, LocalTime.of(12, 0), LocalTime.of(16, 0), null));
      petList.add(new PreExistTask("TUESDAY PET2", DayOfWeek.TUESDAY, LocalTime.of(18, 0), LocalTime.of(21, 0), null));
      petList.add(new PreExistTask("WEDNESDAY SLEEP", DayOfWeek.WEDNESDAY, LocalTime.of(0, 0), LocalTime.of(9, 0), null));
      petList.add(new PreExistTask("WEDNESDAY PET1", DayOfWeek.WEDNESDAY, LocalTime.of(9, 0), LocalTime.of(11, 0), null));
      petList.add(new PreExistTask("WEDNESDAY PET2", DayOfWeek.WEDNESDAY, LocalTime.of(13, 0), LocalTime.of(17, 0), null));
      petList.add(new PreExistTask("THURSDAY SLEEP", DayOfWeek.THURSDAY, LocalTime.of(0, 0), LocalTime.of(9, 0), null));
      petList.add(new PreExistTask("THURSDAY PET1", DayOfWeek.THURSDAY, LocalTime.of(17, 0), LocalTime.of(8, 0), null));
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
   */
   
      llayer.data.preExistTaskList = petList;
      UserTask ut1 = new UserTask("ut1", LocalDateTime.now(), LocalDateTime.now().plusDays(3), TaskTypeEnum.QUEST);
      UserTask ut2 = new UserTask("ut2", LocalDateTime.now(), LocalDateTime.of(2017, 11, 25, 23, 59),TaskTypeEnum.QUIZ);
      UserTask ut3 = new UserTask("ut3", LocalDateTime.now().plusDays(7), LocalDateTime.of(2017, 12, 10, 23, 59),TaskTypeEnum.TEST);
      ut1.hoursLeft = 20;
      ut2.hoursLeft = 20;
      ut3.hoursLeft = 20;
      ArrayList<UserTask> utList = new ArrayList<UserTask>();
      //utList.add(ut1);
      //utList.add(ut2);
      //utList.add(ut3);
      llayer.data.priorityUserTaskList = utList;
      llayer.data.userData.put("semStartYear", "2017");
      llayer.data.userData.put("semStartMonth", "9");
      llayer.data.userData.put("semStartDay", "1");
      llayer.data.userData.put("semEndYear", "2017");
      llayer.data.userData.put("semEndMonth", "12");
      llayer.data.userData.put("semEndDay", "15");
   
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
      displayField.setText("TEST STRING \nTEST STRING\nTEST STRING\n");
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
      JMenu MENU, menu2;
        
        //menu1 and menu2 menu items
      JMenuItem createNewTask, viewTasks, m2Item1, m2Item2,createPreTask;
        
        //Initialize menu items
      createNewTask = new JMenuItem("Create new task");
      createNewTask.addActionListener(this);
      createPreTask = new JMenuItem("Create Pre-Existing Task");
      createPreTask.addActionListener(this);
        
      viewTasks = new JMenuItem("View tasks");
      viewTasks.addActionListener(this);
      m2Item1 = new JMenuItem("m2Item1");
      m2Item2 = new JMenuItem("m2Item2");
        
        //Initialize menus and menu items
      MENU = new JMenu("MENU");
      MENU.add(createNewTask);
      MENU.add(createPreTask);
      MENU.add(viewTasks);
        
      menu2 = new JMenu("menu2");
      menu2.add(m2Item1);
      menu2.add(m2Item2);
        
        //Initialize menu bar and add menus
      menuBar = new JMenuBar();
      menuBar.add(MENU);
      menuBar.add(menu2);
        
      return menuBar;
   }
    
   @Override
    public void actionPerformed(ActionEvent e) {
        //Check for which menu item fired the event and do something
      if(e.getActionCommand().equals("Create new task")) {
         createNewTask();
      }
      else if(e.getActionCommand().equals("View tasks")) {
         viewTasks();
      }
      else if(e.getActionCommand().equals("Create Pre-Existing Task"))
      {
         createPreTask();
      }
   }
   private void createPreTask()
   {
      JFrame frame = new JFrame("Create Pre-Existing Task");
      frame.setVisible(true);
      frame.setSize(600, 600);
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(6,2));
      
      JLabel nameLabel = new JLabel("Enter task name");
      JTextField nameBox = new JTextField(10);
      JLabel dayLabel = new JLabel("Select which day");
      String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
      JComboBox dayBox = new JComboBox(days);
      JLabel startLabel = new JLabel("Enter start time (--:--)");
      JTextField startBox = new JTextField(10);
      JLabel endLabel = new JLabel("Enter end time (--:--)");
      JTextField endBox= new JTextField(10);
      JLabel lastLabel = new JLabel("Enter end date (MM/DD/YYYY)");
      JTextField lastBox = new JTextField(10);
      JButton addButton = new JButton("Add Pre-Existing Task");
      addButton.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent e){
            
               String taskName = nameBox.getText();
               String day =(String)dayBox.getSelectedItem();
               String start = startBox.getText();
               String end = endBox.getText();
               String last = lastBox.getText();
               
               System.out.println(day);
               System.out.println(taskName);
               int startHour = Integer.parseInt(start.substring(0,2));
               int startMin = Integer.parseInt(start.substring(3));
               int endHour = Integer.parseInt(end.substring(0,2));
               int endMin = Integer.parseInt(end.substring(3));
               
               int lastYear = Integer.parseInt(last.substring(6));
               int lastMonth = Integer.parseInt(last.substring(0,2));
               int lastDay = Integer.parseInt(last.substring(3,5));
              
               LocalTime startTime = LocalTime.of(startHour,startMin);
               LocalTime endTime = LocalTime.of(endHour,endMin);
               LocalDateTime endDay = LocalDateTime.of(lastYear,lastMonth,lastDay,endHour,endMin);
               switch(day)
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
               
               updateMonth();
            
            }
         });     
   
      
      panel.add(nameLabel);
      panel.add(nameBox);
      panel.add(dayLabel);
      panel.add(dayBox);
      panel.add(startLabel);
      panel.add(startBox);
      panel.add(endLabel);
      panel.add(endBox);
      panel.add(lastLabel);
      panel.add(lastBox);
      panel.add(new JLabel());
      panel.add(addButton);
      frame.add(panel);
   
   }
   
   private void createNewTask() {
      //Create new task       
      JFrame frame = new JFrame("Create User Task");
      frame.setVisible(true);
      frame.setSize(400, 400);
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(5,1));
     //create components
    
     //subpanel1 for getting date
      JPanel subpanel1 = new JPanel();
      String[] monthOptions = {"1","2","3","4","5","6","7","8","9","10",
         "11","12"};
      String[] dayOptions={
         "1","2","3","4","5","6","7","8","9","10",
         "11","12","13","14","15","16","17","18","19","20",
         "21","22","23","24","25","26","27","28","29","30","31"};
      JComboBox months = new JComboBox(monthOptions);
      JComboBox days = new JComboBox(dayOptions);
      String[] yearOptions={"2017","2018","2019","2020"};
      JComboBox years = new JComboBox(yearOptions);
      subpanel1.add(new JLabel("Select due date:"));
      subpanel1.add(months);
      subpanel1.add(days);
      subpanel1.add(years);
      //end subpanel1
      
      //subpanel2 for getting time due
      JPanel subpanel2 = new JPanel();
      String[] hourOptions={
         "1","2","3","4","5","6","7","8","9","10",
         "11","12"};     
      String[] minOptions={
         "1","2","3","4","5","6","7","8","9","10",
         "11","12","13","14","15","16","17","18","19","20",
         "21","22","23","24","25","26","27","28","29","30",
         "31","32","33","34","35","36","37","38","39","40",
         "41","42","43","44","45","46","47","48","49","50",
         "51","52","53","54","55","56","57","58","59","60"};
      JComboBox hours = new JComboBox(hourOptions);
      JComboBox mins = new JComboBox(minOptions);
      subpanel2.add(new JLabel("Select time due:"));
      subpanel2.add(hours);
      subpanel2.add(mins);
       //end subpanel2
         
      //subpanel3 for getting name
      JPanel subpanel3 = new JPanel();
      JLabel nameLabel = new JLabel("Enter task name:");
      JTextField nameBox = new JTextField(10);
      subpanel3.add(nameLabel);
      subpanel3.add(nameBox);
      //end subpanel3
      
      //panel4 for getting type of task
      JPanel subpanel4 = new JPanel();
      JLabel typeLabel = new JLabel("Select type of task:");
      String[] types = {"Reading","Presentation","Quest","Quiz","Test","Essay","Study","Prelab","Problem set","Prelab"};
      JComboBox typeBox = new JComboBox(types);
      subpanel4.add(typeLabel);
      subpanel4.add(typeBox);
      
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
                        llayer.addUserTask(taskName,start,end,TaskTypeEnum.READING);       
                        break;
                     }
                  case "presentation":
                     {
                        String duration= JOptionPane.showInputDialog("Enter duration of presentation");
                        llayer.addUserTask(taskName,start,end,TaskTypeEnum.PRESENTATION); 
                        break;
                     }
                  case "quest":
                     {
                        String duration= JOptionPane.showInputDialog("Enter duration of quest");
                        llayer.addUserTask(taskName,start,end,TaskTypeEnum.QUEST); 
                        break;
                     }
                  case "quiz":
                     {
                        String duration= JOptionPane.showInputDialog("Enter duration of quiz");
                        llayer.addUserTask(taskName,start,end,TaskTypeEnum.QUIZ); 
                        break;
                     }
                  case "test":
                     {
                        String duration= JOptionPane.showInputDialog("Enter duration of test");
                        llayer.addUserTask(taskName,start,end,TaskTypeEnum.TEST); 
                        break;
                     }
                  case "essay":
                     {
                        String wordCount= JOptionPane.showInputDialog("Enter required word count");
                        llayer.addUserTask(taskName,start,end,TaskTypeEnum.ESSAY); 
                        break;
                     }
                  case "study":
                     {
                        String credits= JOptionPane.showInputDialog("Enter number of credit hours");
                        llayer.addUserTask(taskName,start,end,TaskTypeEnum.STUDY); 
                        break;
                     }
                  
                  case "prelab":
                     {
                        String duration= JOptionPane.showInputDialog("Enter duration of prelab ");
                        llayer.addUserTask(taskName,start,end,TaskTypeEnum.PRELAB); 
                        break;
                     }
                  case "problem set":
                     {
                        String numberOfQuestions= JOptionPane.showInputDialog("Enter number of questions ");
                        llayer.addUserTask(taskName,start,end,TaskTypeEnum.PROBLEMSET); 
                        break;
                     }
                  case "project":
                     llayer.addUserTask(taskName,start,end,TaskTypeEnum.PROJECT); 
                     break;
               
                  default:
               
               }
               updateMonth();
            }
         });
      subpanel5.add(addButton);     
     
      //add components
      panel.add(subpanel3);
      panel.add(subpanel4);
      panel.add(subpanel1);
      panel.add(subpanel2);
      panel.add(subpanel5);
      frame.add(panel);    
   }
     
   private void deleteTask() {
        //Delete task
      System.out.println("TASK DELETED.");
        
   }
    
   private void viewTasks() {
        //View Task
      System.out.println("VIEW TASK.");
      JFrame frame = new JFrame("View Tasks");
      frame.setVisible(true);
      frame.setSize(600, 600);
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
