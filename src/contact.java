package ContactManager;
public class contact {
    private String name;
    private String phone;
    private String email;

    public contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;          
 }
        public void setPhone(String phone) {
        	this.phone = phone;
}
public void setEmail(String email) {
    this.email = email;
}
    public String getName() {
        return name;
    }
    public String getPhone() {
    	return phone;
    	}
    public String getEmail() {
    	return email;
    	}

    public String toString() {
        return "Name: " + name + "\nPhone: " + phone + "\nEmail: " + email;
    }
}


