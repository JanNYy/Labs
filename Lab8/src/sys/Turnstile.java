package courses.labs.sys;

import courses.labs.card.Card;
import courses.labs.sys.CardSystem;
import courses.labs.sys.MyLinkedList;
import sun.util.calendar.Gregorian;

import java.util.GregorianCalendar;

//Турникет
public class Turnstile {

    //Система, связанная с турникетом
    private CardSystem turnstileSystem;
    //false - турникет закрыт, true - открыт
    private boolean gateway = false;

    //При создания объекта "турникет" - он должен быть связан с ситемой
    public Turnstile(CardSystem system) {
        turnstileSystem = system;
    }

    public CardSystem getTurnstileSystem() {
        return turnstileSystem;
    }

    //Дисплей турникета
    private void display(String msg) {
        System.out.println(msg);
    }

    private boolean isValid(Card verifiableCard) throws MyLinkedList.LinkedListException {
        //Проверка наличия карточки в спсике всех выданных
        if (!turnstileSystem.isCardInListAllCards(verifiableCard)) return false;

        //Проверка начальной и конечной даты на карточке (ставнение с системной)
        if (verifiableCard.getDateBegin().compareTo(turnstileSystem.getSystemDate()) > 0) return false;
        if (verifiableCard.getDateEnd().compareTo(turnstileSystem.getSystemDate()) < 0) return false;

        //Проверка часов работы
        if (verifiableCard.getCardTimes().getHoursBegin() > turnstileSystem.getSystemDate().getTime().getHours()) return false;
        if (verifiableCard.getCardTimes().getHoursBegin() == turnstileSystem.getSystemDate().getTime().getHours())
            if (verifiableCard.getCardTimes().getMinutesBegin() > turnstileSystem.getSystemDate().getTime().getMinutes()) return false;
        if (verifiableCard.getCardTimes().getHoursEnd() < turnstileSystem.getSystemDate().getTime().getHours()) return false;
        if (verifiableCard.getCardTimes().getHoursEnd() == turnstileSystem.getSystemDate().getTime().getHours())
            if (verifiableCard.getCardTimes().getMinutesEnd() < turnstileSystem.getSystemDate().getTime().getMinutes()) return false;

        //Проверка дней работы
        if (verifiableCard.getCardDays().getDays()[turnstileSystem.getSystemDate().getTime().getDay()] == 0) return false;
        return true;
    }

    //Проверка карточки и, если она успешна, - турникет пропускает
    public void controlPassage(Card passCard) throws MyLinkedList.LinkedListException {
        if ((isValid(passCard))&&(passCard.doPayment())) {
            display("Card ID "+passCard.getID()+": Access granted");
            gateway = true;
            turnstileSystem.addListAccessGranted(passCard);
        }
        else
        {
            display("Card ID "+passCard.getID()+": Access denied");
            turnstileSystem.addListAccessDenied(passCard);
        }
        gateway = false;
    }

}
