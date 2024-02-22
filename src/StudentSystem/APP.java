package StudentSystem;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class APP {
    //静态代码块修饰，让它在方法开始后只被调用一次
    static ArrayList<User> list = new ArrayList<>();

    static {
        list.add(new User("juju000", "1", "111111111111111111", "11111111111"));
    }

    public static void main(String[] args) {
        //选择功能
        while (true) {
            menu();
            Scanner sc = new Scanner(System.in);
            String option = sc.next();
            if (choose(list, option))
                break;
        }
    }

    //登录界面
    public static void menu() {
        System.out.println("-------------Welcome to Student System!-------------");
        System.out.println("1:Sign  in");
        System.out.println("2:Register");
        System.out.println("3:Forget password");
        System.out.println("4:Exit");
        System.out.println("Enter your choose:");
    }

    //选择功能
    public static boolean choose(ArrayList<User> list, String option) {
        switch (option) {
            case "1":
                System.out.println("Sign in:");
                signIn(list);
                break;
            case "2":
                System.out.println("Register:");
                register(list);
                break;
            case "3":
                System.out.println("Forget password:");
                forgetPassword(list);
                break;
            case "4":
                System.out.println("Exit");
                return true;
            default:
                System.out.println("Invalid option !");
        }
        return false;
    }

    //注册功能
    public static void register(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        User u = new User();
        list.add(u);
        //用户名判断
        while (true) {
            System.out.println("Enter name:");
            String name = sc.next();
            u.setName(name);///???研究清楚此类错误!
            if (judgeName(list, name)) {
                u.setName(name);
                break;
            }
            System.out.println("Invalid! Again:");
        }
        //密码输入两次
        while (true) {
            String p1, p2;
            System.out.println("Enter password");
            p1 = sc.next();
            System.out.println("Enter password again:");
            p2 = sc.next();
            u.setPassword(p1);
            if (p1.equals(p2)) {
                u.setPassword(p1);
                break;
            }
            System.out.println("Not same! Again:");
        }
        //身份证号码验证
        while (true) {
            System.out.println("Enter ID:");
            String ID = sc.next();
            u.setID(ID);
            if (judgeID(ID)) {
                u.setID(ID);
                break;
            }
            System.out.println("Invalid! Again:");
        }
        //手机号验证
        while (true) {
            System.out.println("Enter phone:");
            String p = sc.next();
            u.setPhone(p);
            if (judgePhone(p)) {
                u.setPhone(p);
                break;
            }
            System.out.println("Invalid! Again:");
        }
        System.out.println("Option 1 OK");
    }

    //登录功能
    public static void signIn(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        //输入并判断用户名是否已存在
        String name = checkUser(list);
        if (name.isEmpty())
            return;
        //生成并判断验证码是否正确
        while (true) {
            String s1 = getCode();
            System.out.println("The code:" + s1);
            System.out.println("Enter the code:");
            String s2 = sc.next();
            if (s1.equals(s2))
                break;
            System.out.println("Wrong! Again:");
        }
        //判断用户名和密码是否正确
        int count = 0;
        loop:
        while (true) {
            count++;
            System.out.println("Enter the password:");
            String password = sc.next();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getName().equals(name)) {
                    if (list.get(i).getPassword().equals(password))
                        break loop;//一定要看清楚！循环跳错了导致一大推错误无从下手！
                }
            }
            System.out.println("Wrong! Only " + (3 - count) + " times!");
            if (count == 3)
                return;
        }
        System.out.println("Option 2 OK");
        StudentSystem ss = new StudentSystem();
        ss.startStudentSystem();
    }

    //忘记密码
    public static void forgetPassword(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        //判断用户名是否存在
        String name = checkUser(list);
        if (name.isEmpty())
            return;
        //判断手机号与身份证是否一致
        System.out.println("Enter phone:");
        String phone = sc.next();
        System.out.println("Enter ID:");
        String ID = sc.next();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) {
                if (list.get(i).getPhone().equals(phone) && list.get(i).getID().equals(ID)) {
                    System.out.println("Enter password:");
                    list.get(i).setPassword(sc.next());
                    break;
                } else {
                    System.out.println("Failed!");
                    return;
                }
            }
        }
        System.out.println("Option 3 OK");
    }

    ///输入并判断用户名是否存在
    public static String checkUser(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name:");
        String name = sc.next();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name))
                return name;
        }
        System.out.println("no exit!Register first!");
        return "";
    }

    ///生成验证码
    public static String getCode() {
        ArrayList<Character> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 26; i++) {
            list.add((char) ('a' + i));
            list.add((char) ('A' + i));
        }
        for (int i = 0; i < 4; i++) {
            int x = r.nextInt(52);
            sb.append(list.get(x));
        }
        int x = r.nextInt(10);
        int index = r.nextInt(5);
        sb.insert(index, x);
        return sb.toString();
    }

    ///用户名判断
    public static boolean judgeName(ArrayList<User> list, String name) {
        //长度3~15位
        int length = name.length();
        if (length < 3 || length > 15)
            return false;
        //必须要字母加数字(?)48-57,65-90,97-122
        char[] n = name.toCharArray();
        boolean isLetter = false;
        boolean isNum = false;
        for (int i = 0; i < length; i++) {
            if (n[i] < 48 || (n[i] > 57 && n[i] < 65) || (n[i] > 90 && n[i] < 97) || n[i] > 122)
                return false;
            if (n[i] > 57)
                isLetter = true;
            if (n[i] <= 57)
                isNum = true;
        }
        if (!(isLetter && isNum))
            return false;
        //用户名唯一
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).getName().equals(name))
                return false;
        }
        return true;
    }

    ///ID判断
    public static boolean judgeID(String ID) {
        //长度18
        if (ID.length() != 18)
            return false;
        //不能0开头
        if (ID.charAt(0) == '0')
            return false;
        //前17位必须数字
        char[] ch = ID.toCharArray();
        for (int i = 0; i < ID.length() - 1; i++) {
            if (ch[i] < 48 || ch[i] > 57)
                return false;
        }
        //最后一位必须是数字或者大X小x
        if (!((ch[17] >= 48 && ch[17] <= 57) || ch[17] == 'X' || ch[17] == 'x'))
            return false;
        return true;
    }

    ///手机号判断
    public static boolean judgePhone(String p) {
        //长度11
        if (p.length() != 11)
            return false;
        //不能0开头
        if (p.charAt(0) == '0')
            return false;
        //必须数字
        char[] ch = p.toCharArray();
        for (int i = 0; i < p.length(); i++) {
            if (ch[i] < 48 || ch[i] > 57)
                return false;
        }
        return true;
    }
}
