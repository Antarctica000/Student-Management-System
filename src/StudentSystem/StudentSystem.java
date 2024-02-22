package StudentSystem;

import java.util.Scanner;
import java.util.ArrayList;

public class StudentSystem {
    //改成final常量增加可读性
    private static final String ADD_STU = "1";
    private static final String DELETE_STU = "2";
    private static final String REVISE_STU = "3";
    private static final String ENQUIRE_STU = "4";
    private static final String EXIT = "5";

    static ArrayList<Student> list = new ArrayList<>();

    static {
        list.add(new Student("juju", 3, 18, "Antarctica"));
    }

    public static void startStudentSystem() {
        //主菜单
        while (true) {
            //初始菜单
            menu();
            //选择功能
            Scanner sc = new Scanner(System.in);
            String option = sc.next();//代码容错率，若用户输入不是数字..
            if (choose(option, list))
                break;
        }
    }

    //初始菜单
    public static void menu() {
        System.out.println("-------------Welcome to Student System-------------");
        System.out.println("1:Add     Student");
        System.out.println("2:Delete  Student");
        System.out.println("3:revise  Student");
        System.out.println("4:enquire Student");
        System.out.println("5:Exit           ");
        System.out.println("Enter your option");
    }

    //选择功能
    public static boolean choose(String option, ArrayList<Student> list) {
        switch (option) {
            case ADD_STU:
                System.out.println("Add student:");
                add(list);
                break;
            case DELETE_STU:
                System.out.println("Delete student:");
                delete(list);
                break;
            case REVISE_STU:
                System.out.println("Revise student:");
                revise(list);
                break;
            case ENQUIRE_STU:
                System.out.println("Enquire student:");
                enquire(list);
                break;
            case EXIT:
                System.out.println("Exit");
                return true;
            default:
                System.out.println("Invalid operation");
        }
        return false;
    }

    //添加功能
    public static void add(ArrayList<Student> list) {
        //录入信息
        Student s = new Student();
        while (true) {
            s.setAll();
            int x = s.getID();
            if (judgeID(list, x)) {
                list.add(s);
                break;
            } else
                System.out.println("ID already exist! Again:");
        }
        System.out.println("Option 1 OK");
    }

    //删除功能
    public static void delete(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the ID:");
        int x = sc.nextInt();
        if (!judgeID(list, x)) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getID() == x)
                    list.remove(i);
            }
            System.out.println("Option 2 OK");
        } else
            System.out.println("ID not exist!");
    }

    //修改功能
    public static void revise(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the ID:");
        int x = sc.nextInt();
        if (!judgeID(list, x)) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getID() == x)
                    list.get(i).setAll();
            }
            System.out.println("Option 3 OK");
        } else
            System.out.println("ID not exist!");
    }

    //查询功能
    public static void enquire(ArrayList<Student> list) {
        if (list.isEmpty())
            System.out.println("No student");
        else {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).showAll();
                System.out.println();
            }
        }
    }

    //判断ID是否存在
    public static boolean judgeID(ArrayList<Student> list, int x) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getID() == x)
                return false;
        }
        return true;
    }
}
