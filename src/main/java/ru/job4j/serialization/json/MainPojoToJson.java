package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.serialization.java.Contact;
import java.util.ArrayList;
import java.util.List;

public class MainPojoToJson {
    /**
     * JSONObject из json-строки строки
     * JSONArray из ArrayList
     * JSONObject напрямую методом put
     * Выведем результат в консоль
     * Преобразуем объект man в json-строку
     */
    public static void main(String[] args) {

        JSONObject jsonContact = new JSONObject("{\"zipCode\":123,\"phone\":\"+7(924)111-111-11-11\"}");

        List<String> list = new ArrayList<>();
        list.add("Egor");
        list.add("Mia");
        JSONArray jsonChildNames = new JSONArray(list);

        final GsonForMain man = new GsonForMain(21, "Vasiliy",
                new Contact(123, "+7 (994) 345-56-54"), new String[] {"Egor", "Mia"}, false);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", man.getId());
        jsonObject.put("name", man.getName());
        jsonObject.put("contact", man.getContact());
        jsonObject.put("childNames", man.getChildName());
        jsonObject.put("exists", man.isExists());

        System.out.println(jsonObject.toString());

        System.out.println(new JSONObject(man).toString());
    }
}