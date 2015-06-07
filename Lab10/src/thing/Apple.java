package courses.labs.thing;

public class Apple extends Fruit {

    private static int id = 0;

    private int appleID;

    public Apple() {
        appleID = ++id;
    }

    public int getID() {
        return appleID;
    }

    @Override
    public String toString() {
        return "Apple "+appleID;
    }

}
