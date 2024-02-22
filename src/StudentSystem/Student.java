package StudentSystem;

import java.util.Scanner;

public class Student {
    private String name;
    private int ID;
    private int age;
    private String home;

    public Student() {
    }

    public Student(String name, int ID, int age, String home) {
        this.name = name;
        this.ID = ID;
        this.age = age;
        this.home = home;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public void showAll() {
        System.out.printf("%-10s %-10s %-10s %-10s%n", "id", "name", "age", "home");
        System.out.println();
        System.out.printf("%-10d %-10s %-10d %-10s%n", getID(), getName(), getAge(), getHome());
    }

    public void setAll() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name:");
        setName(sc.next());
        System.out.println("Enter age:");
        setAge(sc.nextInt());
        System.out.println("Enter home:");
        setHome(sc.next());
        System.out.println("Enter ID:");
        setID(sc.nextInt());
    }
}

