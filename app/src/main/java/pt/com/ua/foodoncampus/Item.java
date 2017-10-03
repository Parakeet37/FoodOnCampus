package pt.com.ua.foodoncampus;

import java.io.Serializable;
import java.util.ArrayList;

public class Item implements Serializable {
    private static final long serialVersionUID = -1213949467658913456L;
    private String canteen;
    private String meals;
    private static ArrayList<Item> items = new ArrayList<>();

    public Item(String canteen, String meals) {
        this.canteen = canteen;
        this.meals = meals;
    }

    public String getCanteen() {
        return canteen;
    }

    public static ArrayList<Item> getItems() {
        return items;
    }

    public static void addItem(String canteen, String meals) {
        items.add(new Item(canteen, meals));
    }

    public String getMeals() {
        return meals;
    }

    @Override
    public String toString() {
        return getCanteen();
    }

}
