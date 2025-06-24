package ContactManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ContactManager extends JFrame {
    private ArrayList<contact> contacts = new ArrayList<>();

    private JTextField nameField, phoneField, emailField;
    private JTextField searchField, editField, deleteField;
    private JTextArea displayArea;

    public ContactManager() {
        setTitle("Contact Manager");
        setSize(700, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add New Contact"));

        nameField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();
        JButton addButton = new JButton("Add Contact");

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Phone:"));
        inputPanel.add(phoneField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);
        inputPanel.add(new JLabel(""));           
        inputPanel.add(addButton);                

       
        JPanel actionPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        actionPanel.setBorder(BorderFactory.createTitledBorder("Actions"));

      
        actionPanel.add(new JLabel("Search by Name:"));
        searchField = new JTextField();
        JButton searchButton = new JButton("Search");
        actionPanel.add(searchField);
        actionPanel.add(searchButton);


        actionPanel.add(new JLabel("Edit Contact (Name):"));
        editField = new JTextField();
        JButton editButton = new JButton("Edit");
        actionPanel.add(editField);
        actionPanel.add(editButton);

   
        actionPanel.add(new JLabel("Delete Contact by Name:"));
        deleteField = new JTextField();
        JButton deleteButton = new JButton("Delete");
        actionPanel.add(deleteField);
        actionPanel.add(deleteButton);

     
        displayArea = new JTextArea(10, 50);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Contact List / Results"));

        JButton displayButton = new JButton("Display All");

    
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.NORTH);
        topPanel.add(actionPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(displayButton, BorderLayout.SOUTH);

    
        addButton.addActionListener(e -> addContact());
        searchButton.addActionListener(e -> searchContact());
        editButton.addActionListener(e -> editContact());
        deleteButton.addActionListener(e -> deleteContact());
        displayButton.addActionListener(e -> displayAllContacts());

        setVisible(true);
    }

    private void addContact() {
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            showMessage("Please fill all fields.");
            return;
        }

        contacts.add(new contact(name, phone, email));
        showMessage("Contact added successfully!");
        clearFields();
    }

    private void searchContact() {
        String name = searchField.getText().trim();
        for (contact c : contacts) {
            if (c.getName().equalsIgnoreCase(name)) {
                displayArea.setText("Found Contact:\n" + c.toString());
                return;
            }
        }
        displayArea.setText("Contact not found.");
    }

    private void editContact() {
        String name = editField.getText().trim();
        for (contact c : contacts) {
            if (c.getName().equalsIgnoreCase(name)) {
                String newPhone = JOptionPane.showInputDialog("Enter new phone:", c.getPhone());
                String newEmail = JOptionPane.showInputDialog("Enter new email:", c.getEmail());
                if (newPhone != null && newEmail != null) {
                    c.setPhone(newPhone);
                    c.setEmail(newEmail);
                    showMessage("Contact updated.");
                    return;
                }
            }
        }
        showMessage("Contact not found to edit.");
    }

    private void deleteContact() {
        String name = deleteField.getText().trim();
        for (contact c : contacts) {
            if (c.getName().equalsIgnoreCase(name)) {
                contacts.remove(c);
                showMessage("Contact deleted.");
                return;
            }
        }
        showMessage("Contact not found to delete.");
    }

    private void displayAllContacts() {
        if (contacts.isEmpty()) {
            displayArea.setText("No contacts available.");
            return;
        }

        StringBuilder builder = new StringBuilder("All Contacts:\n");
        for (contact c : contacts) {
            builder.append(c).append("\n\n");
        }
        displayArea.setText(builder.toString());
    }

    private void clearFields() {
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        searchField.setText("");
        editField.setText("");
        deleteField.setText("");
    }

    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
        
    public static void main(String[] args) {
        new ContactManager1();
    }
}
