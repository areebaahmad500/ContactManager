package ContactManager;
import javax.swing.*;
import java.util.ArrayList;
import java.util.regex.*;

public class ContactOperations {

    public void addContact(String name, String phone, String email,
                           ArrayList<contact> contacts, JTextArea displayArea, JFrame parent) {

        name = name.trim();
        phone = phone.trim();
        email = email.trim();

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            showError("All fields are required (Name, Phone, Email).", parent);
            return;
        }

        if (!isValidPhone(phone)) {
            showError("Phone number must be at least 11 digits.", parent);
            return;
        }

        if (!isValidEmail(email)) {
            showError("Email must be in valid format and contain '@'.", parent);
            return;
        }

        contacts.add(new contact(name, phone, email));
        displayArea.setText(" Contact added successfully!");
    }

    public void searchContact(String nameToSearch, ArrayList<contact> contacts,
                              JTextArea displayArea, JFrame parent) {

        nameToSearch = nameToSearch.trim();
        if (nameToSearch.isEmpty()) {
            showError("Please enter a name to search.", parent);
            return;
        }

        boolean found = false;
        StringBuilder result = new StringBuilder();

        for (contact c : contacts) {
            if (c.getName().toLowerCase().contains(nameToSearch.toLowerCase())) {
                result.append(c.toString()).append("\n\n");
                found = true;
            }
        }

        displayArea.setText(found ? result.toString() : "❌ No contact found with name: " + nameToSearch);
    }

    public void editContact(String nameToEdit, ArrayList<contact> contacts,
                            JTextArea displayArea, JFrame parent) {

        nameToEdit = nameToEdit.trim();
        if (nameToEdit.isEmpty()) {
            showError("Please enter a name to edit.", parent);
            return;
        }

        for (contact c : contacts) {
            if (c.getName().equalsIgnoreCase(nameToEdit)) {

                String newName = showInput("Enter new name:", c.getName(), parent);
                if (newName == null || newName.trim().isEmpty()) {
                    showError("Name cannot be empty.", parent);
                    return;
                }

                String newPhone = showInput("Enter new phone number:", c.getPhone(), parent);
                if (newPhone == null || !isValidPhone(newPhone.trim())) {
                    showError("Phone must be at least 11 digits.", parent);
                    return;
                }

                String newEmail = showInput("Enter new email:", c.getEmail(), parent);
                if (newEmail == null || !isValidEmail(newEmail.trim())) {
                    showError("Email must contain '@' and be valid.", parent);
                    return;
                }

                c.setName(newName.trim());
                c.setPhone(newPhone.trim());
                c.setEmail(newEmail.trim());

                displayArea.setText(" Contact updated:\n" + c.toString());
                return;
            }
        }

        displayArea.setText("Contact not found: " + nameToEdit);
    }

    public void deleteContact(String nameToDelete, ArrayList<contact> contacts,
                              JTextArea displayArea, JFrame parent) {

        nameToDelete = nameToDelete.trim();
        if (nameToDelete.isEmpty()) {
            showError("Please enter a name to delete.", parent);
            return;
        }

        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equalsIgnoreCase(nameToDelete)) {
                int confirm = JOptionPane.showConfirmDialog(parent,
                        "Delete this contact?\n" + contacts.get(i),
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    contacts.remove(i);
                    displayArea.setText(" Contact deleted.");
                }
                return;
            }
        }

        displayArea.setText("❌ Contact not found: " + nameToDelete);
    }

    public void displayAllContacts(ArrayList<contact> contacts, JTextArea displayArea) {
        if (contacts.isEmpty()) {
            displayArea.setText(" No contacts found.");
            return;
        }

        StringBuilder all = new StringBuilder(" All Contacts:\n\n");
        for (contact c : contacts) {
            all.append(c.toString()).append("\n\n");
        }

        displayArea.setText(all.toString());
    }

    private boolean isValidPhone(String phone) {
        return phone.matches("\\d{11,}");
    }

    private boolean isValidEmail(String email) {
        return email.contains("@") &&
               Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email);
    }

    private void showError(String msg, JFrame parent) {
        JOptionPane.showMessageDialog(parent, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private String showInput(String msg, String defaultVal, JFrame parent) {
        String input = JOptionPane.showInputDialog(parent, msg, defaultVal);
        return input != null ? input.trim() : null;
    }
}


