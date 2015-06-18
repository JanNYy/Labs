package courses.labs.type;

//���������� ���� �������� ��������
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
