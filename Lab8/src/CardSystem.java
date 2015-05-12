package courses.labs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//—итема, выдающа€ карточки. —истема Singleton
// ћожет выдавать карточки 3 разновидностей (на количество дней, количество поездок, сезонный абонемент) и карточки с предустановлеными значен€ми (например, карточка на выходные на 100 поездок)
public class CardSystem {

    private static CardSystem systemInstance = new CardSystem();

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm");

    private Calendar seasonStart; //Ќачало сезона
    private Calendar seasonEnd; // онец сезона

    {
        seasonStart = Calendar.getInstance();
        seasonStart.set(Calendar.MONTH, Calendar.JANUARY);
        seasonStart.set(Calendar.DAY_OF_MONTH,1);
        seasonStart.set(Calendar.HOUR_OF_DAY,7);
        seasonStart.set(Calendar.MINUTE,0);
        seasonStart.set(Calendar.SECOND, 0);

        seasonEnd = Calendar.getInstance();
        seasonEnd.set(Calendar.MONTH, Calendar.AUGUST);
        seasonEnd.set(Calendar.DAY_OF_MONTH,28);
        seasonEnd.set(Calendar.HOUR,5);
        seasonEnd.set(Calendar.MINUTE,0);
        seasonEnd.set(Calendar.SECOND,0);
    }

    //—писки выданных карточек, карточек, которые прошли и не прошли проверку
    private MyLinkedList listAllCards = new MyLinkedList();
    private MyLinkedList listAccessGranted = new MyLinkedList();
    private MyLinkedList listAccessDenied = new MyLinkedList();

    private int globalID = 10000000;

    public static CardSystem getSystemInstance() {
        return systemInstance;
    }

    private CardSystem() {

    }

    public Calendar getSystemDate() {
        return Calendar.getInstance();
    }

    public Date getSeasonStart() {
        return seasonStart.getTime();
    }

    public void setSeasonStart(Calendar newSeasonStart) throws CardSystemException {
        dateCheck(newSeasonStart);
        seasonStart = (Calendar)newSeasonStart.clone();
    }

    public Date getSeasonEnd() {
        return seasonEnd.getTime();
    }

    public void setSeasonEnd(Calendar newSeasonEnd) throws CardSystemException {
        dateCheck(newSeasonEnd);
        seasonEnd = (Calendar)newSeasonEnd.clone();
    }

    public int getGlobalID() {
        return globalID;
    }

    public void printListAllCards() throws MyLinkedList.LinkedListException {
        listAllCards.printList();
    }

    public int findListAllCards(Card requiredCard) throws MyLinkedList.LinkedListException {
        return listAllCards.indexOf(requiredCard);
    }

    public void printListAccessGranted() throws MyLinkedList.LinkedListException {
        listAccessGranted.printList();
    }

    public void addListAccessGranted(Card cardAccessGranted) throws MyLinkedList.LinkedListException {
        listAccessGranted.addElement(cardAccessGranted);
    }

    public boolean isEmptyListAccessGranted() {
        return listAccessGranted.isEmpty();
    }

    public void printListAccessDenied() throws MyLinkedList.LinkedListException {
        listAccessDenied.printList();
    }

    public void addListAccessDenied(Card cardAccessDenied) throws MyLinkedList.LinkedListException {
        listAccessDenied.addElement(cardAccessDenied);
    }

    public boolean isEmptyListAccessDenied() {
        return listAccessDenied.isEmpty();
    }

    private void dateCheck(Calendar date) throws CardSystemException {
        if (date == null) throw new CardSystemException("Date is null");
        if (date.compareTo(getSystemDate()) > 0) throw new CardSystemException("Date is outdated");
        if ((date.compareTo(seasonStart) < 0)||(date.compareTo(seasonEnd) > 0)) throw new CardSystemException("Date is not in the current season");
    }

    private void checkDaysCorrectness(Calendar date, DaysOfWeek days, NumberOfDays numOfDays) throws CardSystemException {
        if ((days == DaysOfWeek.Weekends) && (numOfDays == NumberOfDays.TwoDays) && (date.getTime().getDay() != 6)) throw new CardSystemException("Specified period is incorrect");
        if ((days == DaysOfWeek.Weekdays) && (numOfDays == NumberOfDays.FiveDays) && (date.getTime().getDay() != 1)) throw new CardSystemException("Specified period is incorrect");
        if ((days == DaysOfWeek.Weekdays) && (numOfDays == NumberOfDays.TwoDays) && ((date.getTime().getDay() > 4)) || (date.getTime().getDay() == 0)) throw new CardSystemException("Specified period is incorrect");
        if ((days == DaysOfWeek.Weekdays) && (numOfDays == NumberOfDays.OneDay) && ((date.getTime().getDay() == 6) || (date.getTime().getDay() == 0))) throw new CardSystemException("Specified period is incorrect");
    }

    //—оздание карточки по сроку действи€
    public Card createCard(Calendar cardBegin, NumberOfDays numOfDays, DaysOfWeek days, TimesOfDay times) throws MyLinkedList.LinkedListException, CardSystemException {
        dateCheck(cardBegin);
        // ≈сли нужно провер€ть на соответсвие дней недели карточки с количеством дней по отношению к заданной дате
        //checkDaysCorrectness(cardBegin,days,numOfDays);
        Card newCard = new CardDurations(globalID++,cardBegin,numOfDays,days,times);
        listAllCards.addElement(newCard);
        return newCard;
    }

    //—оздание карточки по количеству подъЄмов
    public Card createCard(Calendar cardBegin, DaysOfWeek days, NumberOfPasses numOfPasses) throws MyLinkedList.LinkedListException, CardSystemException {
        dateCheck(cardBegin);
        Card newCard = new CardNumberOfPasses(globalID++,cardBegin,seasonEnd,days,numOfPasses);
        listAllCards.addElement(newCard);
        return newCard;
    }

    //—оздание карточки на сезон
    public Card createCard() throws MyLinkedList.LinkedListException {
        Card newCard = new CardSeason(globalID++,seasonStart,seasonEnd);
        listAllCards.addElement(newCard);
        return newCard;
    }

    //—оздание стандартных видов карточек
    public Card createStandardCard(CardTypes typeOfCard, Calendar cardBegin) throws MyLinkedList.LinkedListException, CardSystemException {
        switch (typeOfCard) {
            case WeekdayFrom9To13: return createCard(cardBegin,NumberOfDays.OneDay,DaysOfWeek.Weekdays,TimesOfDay.FirstHalf);
            case WeekdayFrom13To17: return createCard(cardBegin,NumberOfDays.OneDay,DaysOfWeek.Weekdays,TimesOfDay.SecondHalf);
            case Weekday1Day: return createCard(cardBegin,NumberOfDays.OneDay,DaysOfWeek.Weekdays,TimesOfDay.AllDay);
            case Weekday2Days: return createCard(cardBegin,NumberOfDays.TwoDays,DaysOfWeek.Weekdays,TimesOfDay.AllDay);
            case Weekday5Days: return createCard(cardBegin,NumberOfDays.FiveDays,DaysOfWeek.Weekdays,TimesOfDay.AllDay);
            case Weekday10Passes: return createCard(cardBegin,DaysOfWeek.Weekdays,NumberOfPasses.TenTimes);
            case Weekday20Passes: return createCard(cardBegin,DaysOfWeek.Weekdays,NumberOfPasses.TwentyTimes);
            case Weekday50Passes: return createCard(cardBegin,DaysOfWeek.Weekdays,NumberOfPasses.FiftyTimes);
            case Weekday100Passes: return createCard(cardBegin,DaysOfWeek.Weekdays,NumberOfPasses.OneHundredTimes);
            case WeekendFrom9To13: return createCard(cardBegin,NumberOfDays.OneDay,DaysOfWeek.Weekends,TimesOfDay.FirstHalf);
            case WeekendFrom13To17: return createCard(cardBegin,NumberOfDays.OneDay,DaysOfWeek.Weekends,TimesOfDay.SecondHalf);
            case Weekend1Day: return createCard(cardBegin,NumberOfDays.OneDay,DaysOfWeek.Weekends,TimesOfDay.AllDay);
            case Weekend2Days: return createCard(cardBegin,NumberOfDays.TwoDays,DaysOfWeek.Weekends,TimesOfDay.AllDay);
            case Weekend10Passes: return createCard(cardBegin,DaysOfWeek.Weekends,NumberOfPasses.TenTimes);
            case Weekend20Passes: return createCard(cardBegin,DaysOfWeek.Weekends,NumberOfPasses.TwentyTimes);
            case Weekend50Passes: return createCard(cardBegin,DaysOfWeek.Weekends,NumberOfPasses.FiftyTimes);
            case Weekend100Passes: return createCard(cardBegin,DaysOfWeek.Weekends,NumberOfPasses.OneHundredTimes);
            case SeasonPass: return createCard();
            default: throw new IllegalArgumentException();
        }
    }

}
