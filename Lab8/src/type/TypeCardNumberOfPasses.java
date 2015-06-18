package courses.labs.type;

public enum TypeCardNumberOfPasses {

    Weekday10Passes(DaysOfWeek.Weekdays, NumberOfPasses.TenTimes),
    Weekday20Passes(DaysOfWeek.Weekdays,NumberOfPasses.TwentyTimes),
    Weekday50Passes(DaysOfWeek.Weekdays,NumberOfPasses.FiftyTimes),
    Weekday100Passes(DaysOfWeek.Weekdays,NumberOfPasses.OneHundredTimes),

    Weekend10Passes(DaysOfWeek.Weekends,NumberOfPasses.TenTimes),
    Weekend20Passes(DaysOfWeek.Weekends,NumberOfPasses.TwentyTimes),
    Weekend50Passes(DaysOfWeek.Weekends,NumberOfPasses.FiftyTimes),
    Weekend100Passes(DaysOfWeek.Weekends,NumberOfPasses.OneHundredTimes);

    private DaysOfWeek days;
    private NumberOfPasses passes;

    TypeCardNumberOfPasses(DaysOfWeek daysOfWeek, NumberOfPasses numOfPasses) {
        days = daysOfWeek;
        passes = numOfPasses;
    }

    public DaysOfWeek getDays() {
        return days;
    }

    public NumberOfPasses getPasses() {
        return passes;
    }
}
