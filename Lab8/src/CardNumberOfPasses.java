package courses.labs;

import java.util.Calendar;

//Карточка на количество поездок
public class CardNumberOfPasses extends Card {

    public int cardPasses;

    public CardNumberOfPasses(int id, Calendar startDate, Calendar lastDate, DaysOfWeek daysPasses, NumberOfPasses numPasses) {
        cardID = id;
        cardTimes = TimesOfDay.AllDay;
        cardDays = daysPasses;
        cardPasses = numPasses.getNumberOfPasses();
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
        return "ID: "+cardID+". Card from "+CardSystem.dateFormat.format(dateBegin.getTime())+" to "+CardSystem.dateFormat.format(dateEnd.getTime())+ " for "+ cardPasses + " passes on "+cardDays;
    }

}
