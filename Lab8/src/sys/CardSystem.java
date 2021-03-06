package courses.labs.sys;

import courses.labs.type.NumberOfDays;
import courses.labs.card.Card;
import courses.labs.card.CardDurations;
import courses.labs.card.CardNumberOfPasses;
import courses.labs.card.CardSeason;
import courses.labs.type.DaysOfWeek;
import courses.labs.type.TypeCardDurations;
import courses.labs.type.TypeCardNumberOfPasses;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//Ситема, выдающая карточки. Система Singleton
// Может выдавать карточки 3 разновидностей (на количество дней, количество поездок, сезонный абонемент) и карточки с предустановлеными значенями (например, карточка на выходные на 100 поездок)
public class CardSystem {

    public static class CardSystemException extends Exception{

        public CardSystemException(String message) {
            super(message);
        }

        public CardSystemException() {

        }

    }

    private static CardSystem systemInstance = new CardSystem();

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm");

    private Calendar seasonStart; //Начало сезона
    private Calendar seasonEnd; //Конец сезона

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

    //Списки выданных карточек, карточек, которые прошли и не прошли проверку
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

    public boolean isCardInListAllCards(Card requiredCard) throws MyLinkedList.LinkedListException {
        return findListAllCards(requiredCard) != -1;
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

    //Создание карточки по сроку действия
    public Card createCard(Calendar cardBegin, TypeCardDurations type) throws MyLinkedList.LinkedListException, CardSystemException {
        dateCheck(cardBegin);
        // Если нужно проверять на соответсвие дней недели карточки с количеством дней по отношению к заданной дате
        //checkDaysCorrectness(cardBegin,type.getDays(),type.getDaysNumber());
        Card newCard = new CardDurations(globalID++,cardBegin,type);
        listAllCards.addElement(newCard);
        return newCard;
    }

    //Создание карточки по количеству поездок
    public Card createCard(TypeCardNumberOfPasses type) throws MyLinkedList.LinkedListException, CardSystemException {
        Card newCard = new CardNumberOfPasses(globalID++,seasonStart,seasonEnd,type);
        listAllCards.addElement(newCard);
        return newCard;
    }

    //Создание карточки на сезон
    public Card createCard() throws MyLinkedList.LinkedListException {
        Card newCard = new CardSeason(globalID++,seasonStart,seasonEnd);
        listAllCards.addElement(newCard);
        return newCard;
    }

}
