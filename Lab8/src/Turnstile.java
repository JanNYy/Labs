package courses.labs;

//��������
public class Turnstile {

    //�������, ��������� � ����������
    private CardSystem turnstileSystem;
    //false - �������� ������, true - ������
    private boolean gateway = false;

    //��� �������� ������� "��������" - �� ������ ���� ������ � �������
    public Turnstile(CardSystem system) {
        turnstileSystem = system;
    }

    public CardSystem getTurnstileSystem() {
        return turnstileSystem;
    }

    //������� ���������
    private void display(String msg) {
        System.out.println(msg);
    }

    private boolean isValid(Card verifiableCard) throws MyLinkedList.LinkedListException {
        //�������� ������� �������� � ������ ���� ��������
        if (turnstileSystem.findListAllCards(verifiableCard) == -1) return false;
        //�������� ��������� � �������� ���� �� �������� (��������� � ���������)
        if (verifiableCard.dateBegin.compareTo(turnstileSystem.getSystemDate()) > 0) return false;
        if (verifiableCard.dateEnd.compareTo(turnstileSystem.getSystemDate()) < 0) return false;
        //�������� ����� ������
        if (verifiableCard.cardTimes.getHoursBegin() > turnstileSystem.getSystemDate().getTime().getHours()) return false;
        if (verifiableCard.cardTimes.getHoursBegin() == turnstileSystem.getSystemDate().getTime().getHours())
            if (verifiableCard.cardTimes.getMinutesBegin() > turnstileSystem.getSystemDate().getTime().getMinutes()) return false;
        if (verifiableCard.cardTimes.getHoursEnd() < turnstileSystem.getSystemDate().getTime().getHours()) return false;
        if (verifiableCard.cardTimes.getHoursEnd() == turnstileSystem.getSystemDate().getTime().getHours())
            if (verifiableCard.cardTimes.getMinutesEnd() < turnstileSystem.getSystemDate().getTime().getMinutes()) return false;
        //�������� ���� ������
        if (verifiableCard.cardDays.getDays()[turnstileSystem.getSystemDate().getTime().getDay()] == 0) return false;
        return true;
    }

    //�������� �������� �, ���� ��� �������, - �������� ����������
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
