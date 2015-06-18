package courses.labs;

import courses.labs.card.Card;
import courses.labs.sys.CardSystem;
import courses.labs.sys.MyLinkedList;
import courses.labs.sys.Turnstile;
import courses.labs.type.TypeCardDurations;
import courses.labs.type.TypeCardNumberOfPasses;

import java.util.Calendar;
import java.util.Random;

public class TestSystem {
    public static void main(String[] args) throws MyLinkedList.LinkedListException, CardSystem.CardSystemException {

        //Демонстрация работы

        CardSystem mySystem = CardSystem.getSystemInstance();
        Turnstile turnstile2 = new Turnstile(mySystem);

        Calendar c = Calendar.getInstance();

        Random random = new Random();

        int numCards = 50;
        Card[] userCards = new Card[numCards];
        int cardType;
        for (int i = 0; i < numCards; i++)
        {
            cardType = random.nextInt(3);
            switch (cardType)
            {
                case 0:
                {
                    //Создать карточку на срок действия
                    cardType = random.nextInt(TypeCardDurations.values().length);
                    userCards[i] = mySystem.createCard(c,TypeCardDurations.values()[cardType]);
                    break;
                }
                case 1:
                {
                    //Создать карточку на количество поездок
                    cardType = random.nextInt(TypeCardNumberOfPasses.values().length);
                    userCards[i] = mySystem.createCard(TypeCardNumberOfPasses.values()[cardType]);
                    break;
                }
                case 2:
                {
                    //Создать карточку на сезон
                    userCards[i] = mySystem.createCard();
                }
            }
        }

        System.out.println("All cards:");
        turnstile2.getTurnstileSystem().printListAllCards();
        System.out.println();

        System.out.println("All cards pass through the turnstile:");
        for (int i = 0; i < userCards.length; i++)
            turnstile2.controlPassage(userCards[i]);
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
