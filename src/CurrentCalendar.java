
/**
 *
 * @author aasan
 */
import java.util.HashMap;
import java.time.LocalDate;
import java.time.Month;

public class CurrentCalendar {
    protected HashMap<Integer, ASDay> currCalendar;
    
    public CurrentCalendar(int year, int month, int day) {
        currCalendar = new HashMap<Integer, ASDay>();
        int key;
        LocalDate date = LocalDate.of(year, month, day);
        for(int i = 0; i < 10; i++) {
            year = date.getYear();
            month = date.getMonthValue();
            day = date.getDayOfMonth();
            key = (year * 10000) + (month * 100) + day;
            //currCalendar.put(key, new ASDay(year, month, day));
            date = date.plusDays(2);
        }
    }
    
    public HashMap<Integer, ASDay> getCurrCalendar() {
        return currCalendar;
    }
}
