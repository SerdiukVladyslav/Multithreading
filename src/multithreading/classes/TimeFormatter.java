package multithreading.classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormatter {
    public static String convertToTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:SSS");
        return sdf.format(new Date(time));
    }
}
