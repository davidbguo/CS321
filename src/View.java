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
      JMenuItem createNewTask, viewTasks, m2Item1, m2Item2;
        
        //Initialize menu items
      createNewTask = new JMenuItem("Create new task");
      createNewTask.addActionListener(this);
        
      viewTasks = new JMenuItem("View tasks");
      viewTasks.addActionListener(this);
      m2Item1 = new JMenuItem("m2Item1");
      m2Item2 = new JMenuItem("m2Item2");
        
        //Initialize menus and menu items
      MENU = new JMenu("MENU");
      MENU.add(createNewTask);
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
   }
    
   private void createNewTask() {
                   //Create new task       
      JFrame frame = new JFrame("Create User Task");
      frame.setVisible(true);
      frame.setSize(600, 600);
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(5,2));
     //create components
      JLabel nameLabel = new JLabel("Enter task name");
      JTextField nameBox = new JTextField(10);
      JLabel typeLabel = new JLabel("Select type of task");
      String[] types = {"Reading","Presentation","Quest","Quiz","Test","Essay","Study","Prelab","Problem set","Prelab"};
      JComboBox typeBox = new JComboBox(types);
      JLabel dateLabel = new JLabel("Enter due date (MM/DD/YYYY)");
      JTextField dateBox = new JTextField(10);
      JLabel timeLabel = new JLabel("Enter time due");
      JTextField timeBox= new JTextField(10);
      JButton addButton = new JButton("Add Task");
      addButton.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent e){
            
            
            
               String taskName = nameBox.getText();
               String taskType = (String)typeBox.getSelectedItem();
               String dueDate = dateBox.getText();
               String timeDue = timeBox.getText();
               LocalDateTime start = LocalDateTime.now();//--:--
               int year = Integer.parseInt(dueDate.substring(6));
               int month = Integer.parseInt(dueDate.substring(0,2));
               int day = Integer.parseInt(dueDate.substring(3,5));
               int hour = Integer.parseInt(timeDue.substring(0,2));
               int minute = Integer.parseInt(timeDue.substring(3));
               LocalDateTime end = LocalDateTime.of(year,month,day,hour,minute);
            
            System.out.println(taskName);
            System.out.println(dueDate);
            System.out.println(timeDue);
            System.out.println(taskType);
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
     
      //add components
      panel.add(nameLabel);
      panel.add(nameBox);
      panel.add(typeLabel);
      panel.add(typeBox);
      panel.add(dateLabel);
      panel.add(dateBox);
      panel.add(timeLabel);
      panel.add(timeBox);
      panel.add(addButton);
      panel.add(new JLabel());
      panel.add(addButton);
   
      
            
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
