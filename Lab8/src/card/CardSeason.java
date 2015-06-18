package courses.labs.card;

import courses.labs.sys.CardSystem;
import courses.labs.type.DaysOfWeek;
import courses.labs.type.TimesOfDay;

import java.util.Calendar;

//Карточка на сезон
public class CardSeason extends Card {

    public CardSeason(int id, Calendar startDate, Calendar lastDate) {
        cardID = id;
        cardDays = DaysOfWeek.AllWeek;
        cardTimes = TimesOfDay.AllDay;
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

    public String toString() {
        return "ID: "+cardID+". Card for a season from "+ dateFormat.format(dateBegin.getTime())+ " to "+dateFormat.format(dateEnd.getTime());
    }


}
