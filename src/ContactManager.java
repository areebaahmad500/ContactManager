package ContactManager;
import java.util.*;

public class ContactManager {
    private List<contact> contacts = new ArrayList<>();

    public void addContact(contact contact) {
        contacts.add(contact);
    }

    public List<contact> getAllContacts() {
        return contacts;
    }

    public contact searchContact(String name) {
        for (contact c : contacts) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    public boolean editContact(String name, String newPhone, String newEmail) {
        contact c = searchContact(name);
        if (c != null) {
            c.setPhone(newPhone);
            c.setEmail(newEmail);
            return true;
        }
        return false;
    }

    public boolean deleteContact(String name) {
        contact c = searchContact(name);
        return contacts.remove(c);
    }
}
