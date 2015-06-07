package courses.labs;

import courses.labs.thing.*;

import java.util.ArrayList;
import java.util.List;

public class GenericsRunner {

    public static Apple createApple() {
        return new Apple();
    }

    public static Orange createOrange() {
        return new Orange();
    }

    public static RedApple createRedApple() {
        return new RedApple();
    }

    public static Melon createMelon() {
        return new Melon();
    }

    public static void main(String[] args) {

        //fromArrayToCollection
        String[] strArray = new String[]{"one","two","three","four","five"};
        ArrayList<String> strList = new ArrayList<>();
        Generics.fromArrayToCollection(strArray,strList);
        System.out.println(strList);

        //copyAll
        int fruitNumber = 5;
        List<Fruit> fr = new ArrayList<Fruit>(fruitNumber);
        List<Apple> app = new ArrayList<Apple>(fruitNumber);
        List<Orange> or = new ArrayList<Orange>(fruitNumber);
        List<RedApple> rapp = new ArrayList<RedApple>();
        List<Melon> mel = new ArrayList<Melon>(fruitNumber);
        for (int i = 0; i < fruitNumber; i++) {
            app.add(createApple());
            or.add(createOrange());
            rapp.add(createRedApple());
            mel.add(createMelon());
        }
        Generics.copyAll(or, fr);
        System.out.println(fr);
        Generics.copyAll(rapp, fr);
        System.out.println(fr);
        Generics.copyAll(rapp, app);
        System.out.println(app);
        Generics.copyAll(rapp, rapp);
        System.out.println(rapp);
        //Generics.copyAll(fr, or);
        //Generics.copyAll(null,mel);
        //Generics.copyAll(rapp,null);
        //Generics.copyAll(fr,mel);
        //Generics.copyAll(mel,fr);

        //greaterThanSpecified
        System.out.println(Generics.greaterThanSpecified(strList, "one"));

    }

}
