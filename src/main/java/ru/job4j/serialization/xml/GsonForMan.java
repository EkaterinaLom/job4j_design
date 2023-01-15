package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "man")
@XmlAccessorType(XmlAccessType.FIELD)
public class GsonForMan {

    @XmlAttribute
    private int id;
    private String name;
    private Contacted contact;
    private boolean exists;

    @XmlElementWrapper(name = "childNames")
    @XmlElement(name = "childName")
    private String[] childName;

    public GsonForMan() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(Contacted contact) {
        this.contact = contact;
    }

    public void setChildName(String[] childName) {
        this.childName = childName;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public GsonForMan(int id, String name, Contacted contact, String[] childName, boolean exists) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.childName = childName;
        this.exists = exists;
    }

    @Override
    public String toString() {
        return "GsonForMan {"
                + "id=" + id
                + ", name=" + name
                + ", contact=" + contact
                + ", childName=" + Arrays.toString(childName)
                + ", exists=" + exists
                + '}';
    }

    public static void main(String[] args) throws JAXBException {

        final GsonForMan man = new GsonForMan(21, "Vasiliy",
                new Contacted(123, "+7 (994) 345-56-54"), new String[]{"Egor", "Mia"}, false);
        JAXBContext context = JAXBContext.newInstance(GsonForMan.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(man, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
