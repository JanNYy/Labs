package courses.labs.card;

import courses.labs.type.TimesOfDay;
import courses.labs.type.TypeCardNumberOfPasses;

import java.util.Calendar;

//Карточка на количество поездок
public class CardNumberOfPasses extends Card {

    public int cardPasses;

    public CardNumberOfPasses(int id, Calendar startDate, Calendar lastDate, TypeCardNumberOfPasses type) {
        cardID = id;
        cardTimes = TimesOfDay.AllDay;
        cardDays = type.getDays();
        cardPasses = type.getPasses().getNumberOfPasses();
        dateBegin = Calendar.getInstance();
        dateBegin = (Calendar)startDate.clone();
        dateBegin.set(Calendar.HOUR_OF_DAY,cardTimes.getHoursBegin());
        dateBegin.set(Calendar.MINUTE,cardTimes.getMinutesBegin());
        dateBegin.set(Calendar.SECOND,0);
        dateEnd = Calendar.getInstance();
        dateEnd = (Calendar)lastDate.clone();
        dateEnd.set(Calendar.HOUR_OF_DAY,cardTimes.getHoursEnd());
        dateEnd.set(Calendar.MINUTE,cardTimes.getMinutesEnd());
        dateEnd.set(Calendar.SECOND,0);
    }

    public boolean doPayment() {
        if (cardPasses > 0){
            cardPasses -= 1;
            return true;
        }
        return false;
    }

    public String toString() {
        return "ID: "+cardID+". Card from "+ dateFormat.format(dateBegin.getTime())+" to "+dateFormat.format(dateEnd.getTime())+ " for "+ cardPasses + " passes on "+cardDays;
    }

}
