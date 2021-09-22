import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * @author Andy Ron
 */
public class CalendarTest2 {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int today = date.getDayOfMonth();

        date = date.minusDays(today - 1);
        DayOfWeek dw = date.getDayOfWeek();
        int dwv = dw.getValue();

        System.out.println("Mon Tue Wed Thu Fri Sat Sun");

        for (int i = 1; i < dwv; i++) {
            System.out.print("    ");
        }

        while (date.getMonthValue() == month) {
            System.out.printf("%3d", date.getDayOfMonth());
            if (date.getDayOfMonth() == today) {
                System.out.print("*");
            } else {
                System.out.print(" ");
            }

            if (date.getDayOfWeek().getValue() == 7) {
                System.out.println();
            }

            date = date.plusDays(1);
        }

    }
}
