package ContactManager;
import java.io.*;
import java.util.List;

public class FileManager {
    private static final String FILE_NAME = "contacts.txt";

    public static void saveToFile(List<contact> contacts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (contact c : contacts) {
                writer.write(c.getName() + "," + c.getPhone() + "," + c.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving contacts.");
        }
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^(.+)@(.+)$");
    }

    public static boolean isValidPhone(String phone) {
        return phone.matches("^[0-9]{10,11}$");
    }
}

