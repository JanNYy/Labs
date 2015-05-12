package courses.labs;

import java.util.Calendar;
import java.util.Random;

public class TestSystem {

    public static void main(String[] args) throws MyLinkedList.LinkedListException, CardSystemException {

        //Демонстрация работы

        CardSystem mySystem = CardSystem.getSystemInstance();
        Turnstile turnstile2 = new Turnstile(mySystem);

        Calendar c = Calendar.getInstance();

        Random random = new Random();

        int numCards = 50;
        Card[] userCards = new Card[numCards];
        int cardType;
        for (int i = 0; i < numCards; i++) {
            cardType = random.nextInt(CardTypes.values().length);
            userCards[i] = mySystem.createStandardCard(CardTypes.values()[cardType], c);
        }

        Card newCard1 = mySystem.createCard(c, DaysOfWeek.Weekdays, NumberOfPasses.TwentyTimes);
        Card newCard2 = mySystem.createCard();

        System.out.println("All cards:");
        turnstile2.getTurnstileSystem().printListAllCards();
        System.out.println();

        System.out.println("All cards pass through the turnstile:");
        for (int i = 0; i < userCards.length; i++)
            turnstile2.controlPassage(userCards[i]);
        turnstile2.controlPassage(newCard1);
        turnstile2.controlPassage(newCard2);
        System.out.println();

        System.out.println("Cards with Access Granted:");
        if (!turnstile2.getTurnstileSystem().isEmptyListAccessGranted())
        {
            turnstile2.getTurnstileSystem().printListAccessGranted();
        }
        else
        {
            System.out.println("List of Cards with Access Granted is Empty");
        }
        System.out.println();

        System.out.println("Cards with Access Denied:");
        if (!turnstile2.getTurnstileSystem().isEmptyListAccessDenied())
        {
            turnstile2.getTurnstileSystem().printListAccessDenied();
        }
        else
        {
            System.out.println("List of Cards with Access Denied is Empty");
        }

    }

}
