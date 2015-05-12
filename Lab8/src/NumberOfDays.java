package courses.labs;

//Количество дней действия карточки
public enum NumberOfDays {

    OneDay(1),
    TwoDays(2),
    FiveDays(5);

    private int numOfDays;

    private NumberOfDays(int number) {
        numOfDays = number;
    }

    public int getNumOfDays() {
        return numOfDays;
    }

}
