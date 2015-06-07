package courses.labs.thing;

public class Melon {

    private static int id = 0;

    private int melonID;

    public Melon() {
        melonID = ++id;
    }

    public int getID() {
        return melonID;
    }

    @Override
    public String toString() {
        return "Melon "+melonID;
    }

}
