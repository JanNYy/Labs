package courses.labs;

import java.text.SimpleDateFormat;
import java.util.Calendar;

//����� ��� ��������
public abstract class Card {

    protected int cardID;
    protected Calendar dateBegin;
    protected Calendar dateEnd;
    protected TimesOfDay cardTimes;
    protected DaysOfWeek cardDays;

    public int getID() {
        return cardID;
    }

    //���������� �������� ��� ������������� �������, ���������� ���������� ������� � �.�.
    protected abstract boolean doPayment();

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        if (cardID != card.cardID) return false;
        if (dateBegin != null ? !dateBegin.equals(card.dateBegin) : card.dateBegin != null) return false;
        if (dateEnd != null ? !dateEnd.equals(card.dateEnd) : card.dateEnd != null) return false;
        if (cardTimes != card.cardTimes) return false;
        return cardDays == card.cardDays;
    }

    public int hashCode() {
        int result = cardID;
        result = 31 * result + (dateBegin != null ? dateBegin.hashCode() : 0);
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        result = 31 * result + (cardTimes != null ? cardTimes.hashCode() : 0);
        result = 31 * result + (cardDays != null ? cardDays.hashCode() : 0);
        return result;
    }

}



