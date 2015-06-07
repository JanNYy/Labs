package courses.labs.thing;

public class RedApple extends Apple {

    private static int id = 0;

    private int redAppleID;

    public RedApple() {
        redAppleID = ++id;
    }

    public int getID() {
        return redAppleID;
    }

    @Override
    public String toString() {
        return "Red apple "+redAppleID;
    }

}
