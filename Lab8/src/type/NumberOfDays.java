package courses.labs.type;

//Количество дней действия карточки
public enum NumberOfDays {

    OneDay(1),
    TwoDays(2),
    FiveDays(5);

    private int numOfDays;

    NumberOfDays(int number) {
        numOfDays = number;
    }

    public int getNumOfDays() {
        return numOfDays;
    }

}
