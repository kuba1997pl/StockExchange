
package StockExchange.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author jakub
 */
public class Customer implements Serializable{

    private static ArrayList<String> names = new ArrayList<>();

    static {
        names.add("Jan");
        names.add("Jakub");
        names.add("Debil");
        names.add("Chujor");
        names.add("Dziwka");
    }

    private String name;

    public Customer() {
        name = names.get(new Random().nextInt(names.size()));
        names.remove(name);
    }

}
