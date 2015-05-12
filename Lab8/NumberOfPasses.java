package courses.labs;

//Количество проходов
public enum NumberOfPasses {

    TenTimes(10),
    TwentyTimes(20),
    FiftyTimes(50),
    OneHundredTimes(100);

    private int number;

    private NumberOfPasses(int numOfPasses) {
        number = numOfPasses;
    }

    public int getNumberOfPasses() {
        return number;
    }

}
