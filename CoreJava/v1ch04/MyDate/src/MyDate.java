import java.time.LocalDate;
import java.util.Date;

public class MyDate {
    public static void main(String[] args) {
        LocalDate newYearEve = LocalDate.of(2019, 12, 31);
        System.out.println(newYearEve.getYear());
        LocalDate aThousandDaysLater = newYearEve.plusDays(1000);
        System.out.println(aThousandDaysLater.getYear());



    }
}
