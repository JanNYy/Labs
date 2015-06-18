package courses.labs.type;

public enum TypeCardDurations {

    WeekdayFrom9To13(NumberOfDays.OneDay,TimesOfDay.FirstHalf,DaysOfWeek.Weekdays),
    WeekdayFrom13To17(NumberOfDays.OneDay, TimesOfDay.SecondHalf,DaysOfWeek.Weekdays),
    Weekday1Day(NumberOfDays.OneDay, TimesOfDay.AllDay,DaysOfWeek.Weekdays),
    Weekday2Days(NumberOfDays.TwoDays, TimesOfDay.AllDay,DaysOfWeek.Weekdays),
    Weekday5Days(NumberOfDays.FiveDays, TimesOfDay.AllDay,DaysOfWeek.Weekdays),

    WeekendFrom9To13(NumberOfDays.OneDay, TimesOfDay.FirstHalf,DaysOfWeek.Weekends),
    WeekendFrom13To17(NumberOfDays.OneDay, TimesOfDay.SecondHalf,DaysOfWeek.Weekends),
    Weekend1Day(NumberOfDays.OneDay, TimesOfDay.AllDay,DaysOfWeek.Weekends),
    Weekend2Days(NumberOfDays.TwoDays, TimesOfDay.AllDay,DaysOfWeek.Weekends);

    private NumberOfDays daysNumber;
    private TimesOfDay times;
    private DaysOfWeek days;

    TypeCardDurations(NumberOfDays numOfDays, TimesOfDay timesOfDay, DaysOfWeek daysOfWeek) {
        daysNumber = numOfDays;
        times = timesOfDay;
        days = daysOfWeek;
    }

    public NumberOfDays getDaysNumber() {
        return daysNumber;
    }

    public TimesOfDay getTimes() {
        return times;
    }

    public DaysOfWeek getDays() {
        return days;
    }

}
