package courses.labs.card;

import courses.labs.type.DaysOfWeek;
import courses.labs.type.TimesOfDay;

import java.text.SimpleDateFormat;
import java.util.Calendar;

//Общий вид карточек
public abstract class Card {

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm");

    protected int cardID;
    protected Calendar dateBegin;
    protected Calendar dateEnd;
    protected TimesOfDay cardTimes;
    protected DaysOfWeek cardDays;

    public int getID() {
        return cardID;
    }

    public Calendar getDateBegin() {
        return dateBegin;
    }

    public Calendar getDateEnd() {
        return dateEnd;
    }

    public TimesOfDay getCardTimes() {
        return cardTimes;
    }

    public DaysOfWeek getCardDays() {
        return cardDays;
    }

    //Определяет действия при необходимости платежа, уменьшения количества поездок и т.д.
    public boolean doPayment() {
        return true;
    }

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




