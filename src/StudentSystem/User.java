package StudentSystem;

import java.util.Scanner;

public class User {
    private String name;
    private String password;
    private String ID;
    private String phone;

    public User() {
    }

    public User(String name, String password, String ID, String phone) {
        this.name = name;
        this.password = password;
        this.ID = ID;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void showUser() {
        System.out.printf("%-10s %-10s %-10s %-10s%n", "name", "ID", "phone", "password");
        System.out.println();
        System.out.printf("%-10d %-10s %-10d %-10s%n", getName(), getID(), getPhone(), getPassword());
    }
}

