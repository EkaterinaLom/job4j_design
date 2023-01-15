package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.serialization.java.Contact;
import java.util.Arrays;

public class GsonForMain {
    private final int id;
    private final String name;
    private final Contact contact;
    private final String[] childName;
    private final boolean exists;

    public GsonForMain(int id, String name, Contact contact, String[] childName, boolean exists) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.childName = childName;
        this.exists = exists;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getChildName() {
        return childName;
    }

    public boolean isExists() {
        return exists;
    }

    @Override
    public String toString() {
        return "GsonForMain {"
                + "id=" + id
                + ", name=" + name
                + ", contact=" + contact
                + ", childName=" + Arrays.toString(childName)
                + ", exists=" + exists
                + '}';
    }

    public static void main(String[] args) {
        final GsonForMain man = new GsonForMain(21, "Vasiliy",
                new Contact(123, "+7 (994) 345-56-54"), new String[] {"Egor", "Mia"}, false);

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(man));

        final String manJson = "{"
                + "\"id\":21,"
                + "\"name\":Vasiliy,"
                + "\"contact\":"
                + "{"
                + "\"zipCode\":123,"
                + "\"phone\":\"+7 (994) 345-56-54\""
                + "},"
                + "\"childName\":"
                + "[\"Egor\",\"Mia\"],"
                + "\"exists\":false"
                + "}";
        final GsonForMain manMod = gson.fromJson(manJson, GsonForMain.class);
        System.out.println(manMod);
    }
}
