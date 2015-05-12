package courses.labs;

//��� ������, � ������� �������� ��� �������� ������
public enum DaysOfWeek {

    // 1 - ���� � ���� ���� ������ ��������, ����� - 0
    Weekdays(new int[]{0,1,1,1,1,1,0}),
    Weekends(new int[]{1,0,0,0,0,0,1}),
    AllWeek(new int[]{1,1,1,1,1,1,1});

    private int[] days;

    private DaysOfWeek(int[] workDays) {
        days = workDays;
    }

    public int[] getDays() {
        return days;
    }

}
