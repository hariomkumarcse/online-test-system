import java.io.*;
import java.util.*;

public class LoginSystem {

    public static String currentUser = "";
    public static String currentRole = "";
    public static String currentDept = "";

    public static void registerUser(String role) throws Exception {

        File file = new File("login.txt");
        if (!file.exists()) file.createNewFile();

        Scanner sc = new Scanner(System.in);

        System.out.print("Username: ");
        String user = sc.next();

        System.out.print("Password: ");
        String pass = sc.next();

        System.out.print("Email: ");
        String email = sc.next();

        System.out.print("Department: ");
        String dept = sc.next();

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            String[] d = line.split(",");
            if (d[0].equals(user)) {
                System.out.println("User already exists!");
                br.close();
                return;
            }
        }
        br.close();

        FileWriter fw = new FileWriter(file, true);
        fw.write(user + "," + pass + "," + role + "," + email + "," + dept + "\n");
        fw.close();

        System.out.println(role.equals("TH") ? "Teacher Registered!" : "Subject Head Registered!");
    }

    public static boolean login(String role) throws Exception {

        File file = new File("login.txt");
        if (!file.exists()) return false;

        Scanner sc = new Scanner(System.in);

        System.out.print("Username: ");
        String user = sc.next();

        System.out.print("Password: ");
        String pass = sc.next();

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            String[] d = line.split(",");

            if (d[0].equals(user) && d[1].equals(pass) && d[2].equals(role)) {
                currentUser = user;
                currentRole = role;
                currentDept = d[4];
                System.out.println("Login Successful!");
                br.close();
                return true;
            }
        }

        br.close();
        System.out.println("Invalid Login!");
        return false;
    }
}