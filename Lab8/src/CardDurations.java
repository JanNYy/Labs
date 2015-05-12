package courses.labs;

import java.util.Calendar;

//Карточка на срок действия
public class CardDurations extends Card {

    //public TimesOfDay cardTimes;

    public CardDurations(int id, Calendar startDate, NumberOfDays days, DaysOfWeek daysPasses, TimesOfDay times) {
        cardID = id;
        cardDays = daysPasses;
        cardTimes = times;
        dateBegin = Calendar.getInstance();
        dateBegin = (Calendar)startDate.clone();
        dateBegin.set(Calendar.HOUR_OF_DAY,cardTimes.getHoursBegin());
        dateBegin.set(Calendar.MINUTE, cardTimes.getMinutesBegin());
        dateBegin.set(Calendar.SECOND,0);
        dateEnd = Calendar.getInstance();
        dateEnd.add(startDate.DAY_OF_MONTH, days.getNumOfDays() - 1);
        dateEnd.set(Calendar.HOUR_OF_DAY, cardTimes.getHoursEnd());
        dateEnd.set(Calendar.MINUTE,cardTimes.getMinutesEnd());
        dateEnd.set(Calendar.SECOND,0);
    }

    public boolean doPayment() {
        return true;
    }

    public String toString() {
        return "ID: "+cardID+". Card from "+CardSystem.dateFormat.format(dateBegin.getTime())+" to "+CardSystem.dateFormat.format(dateEnd.getTime())+" on the "+cardDays + ". Valid hours: "+cardTimes.toString();
    }

}
