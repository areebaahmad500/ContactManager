package ContactManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ContactManager1 extends JFrame {
    private ArrayList<contact> contacts = new ArrayList<>();
    private ContactOperations operations = new ContactOperations();

    private JTextField nameField, phoneField, emailField;
    private JTextField searchField, editField, deleteField;
    private JTextArea displayArea;

    public ContactManager1() {
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

        searchField = new JTextField();
        JButton searchButton = new JButton("Search");
        actionPanel.add(new JLabel("Search by Name:"));
        actionPanel.add(searchField);
        actionPanel.add(searchButton);

        editField = new JTextField();
        JButton editButton = new JButton("Edit");
        actionPanel.add(new JLabel("Edit Contact (Name):"));
        actionPanel.add(editField);
        actionPanel.add(editButton);

        deleteField = new JTextField();
        JButton deleteButton = new JButton("Delete");
        actionPanel.add(new JLabel("Delete Contact by Name:"));
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
        operations.addContact(nameField.getText(), phoneField.getText(),emailField.getText(), contacts,displayArea, this );
        clearFields();
    }

    private void searchContact() {
        operations.searchContact( searchField.getText(),contacts,displayArea,this);
    }

    private void editContact() {
        operations.editContact(editField.getText(),  contacts, displayArea,this);
    }

    private void deleteContact() {
        operations.deleteContact(
                deleteField.getText(), contacts,  displayArea,  this);
    }

    private void displayAllContacts() {
        operations.displayAllContacts(contacts, displayArea, this);
    }

    private void clearFields() {
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        searchField.setText("");
        editField.setText("");
        deleteField.setText("");
    }

    public static void main(String[] args) {
        new ContactManager1();
    }
}
