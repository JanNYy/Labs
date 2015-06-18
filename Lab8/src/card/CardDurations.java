package courses.labs.card;

import courses.labs.type.TypeCardDurations;

import java.util.Calendar;

//Карточка на срок действия
public class CardDurations extends Card {

    public CardDurations(int id, Calendar startDate, TypeCardDurations cardType) {
        cardID = id;
        cardDays = cardType.getDays();
        cardTimes = cardType.getTimes();
        dateBegin = Calendar.getInstance();
        dateBegin = (Calendar)startDate.clone();
        dateBegin.set(Calendar.HOUR_OF_DAY,cardTimes.getHoursBegin());
        dateBegin.set(Calendar.MINUTE, cardTimes.getMinutesBegin());
        dateBegin.set(Calendar.SECOND,0);
        dateEnd = Calendar.getInstance();
        dateEnd.add(startDate.DAY_OF_MONTH, cardType.getDaysNumber().getNumOfDays() - 1);
        dateEnd.set(Calendar.HOUR_OF_DAY, cardTimes.getHoursEnd());
        dateEnd.set(Calendar.MINUTE,cardTimes.getMinutesEnd());
        dateEnd.set(Calendar.SECOND,0);
    }

    public String toString() {
        return "ID: "+cardID+". Card from "+ dateFormat.format(dateBegin.getTime())+" to "+dateFormat.format(dateEnd.getTime())+" on the "+cardDays + ". Valid hours: "+cardTimes.toString();
    }

}
