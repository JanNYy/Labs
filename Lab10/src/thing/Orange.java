package courses.labs.thing;

public class Orange extends Fruit {

    private static int id = 0;

    private int orangeID;

    public Orange() {
        orangeID = ++id;
    }

    public int getID() {
        return orangeID;
    }

    @Override
    public String toString() {
        return "Orange "+orangeID;
    }

}
