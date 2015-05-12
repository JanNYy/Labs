package courses.labs;

import java.util.Formatter;

//Диапазон часов, в которые разрешён проход
public enum TimesOfDay {

    FirstHalf(9,0,13,0),
    SecondHalf(13,0,17,0),
    AllDay(9,0,17,0);

    private int hoursBegin;
    private int minutesBegin;
    private int hoursEnd;
    private int minutesEnd;

    private TimesOfDay(int hB, int mB, int hE, int mE) {
        hoursBegin = hB;
        minutesBegin = mB;
        hoursEnd = hE;
        minutesEnd = mE;
    }

    public int getHoursBegin() {
        return hoursBegin;
    }

    public int getMinutesBegin() {
        return minutesBegin;
    }

    public int getHoursEnd() {
        return hoursEnd;
    }

    public int getMinutesEnd() {
        return minutesEnd;
    }

    public String toString() {
        return (hoursBegin + ":"+minutesBegin+" - "+hoursEnd+":"+minutesEnd);
    }
}
