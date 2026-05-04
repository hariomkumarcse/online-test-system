import java.io.*;
import java.util.*;

public class QuestionManager {

    public static void addQuestion() throws Exception {

        File file = new File("questions.txt");
        if (!file.exists()) file.createNewFile();

        Scanner sc = new Scanner(System.in);

        System.out.print("How many questions: ");
        int n = sc.nextInt();
        sc.nextLine();

        FileWriter fw = new FileWriter(file, true);

        for (int i = 0; i < n; i++) {

            System.out.print("\nQuestion ID: ");
            String id = sc.nextLine();

            System.out.print("Question: ");
            String text = sc.nextLine();

            String[] opt = new String[4];
            for (int j = 0; j < 4; j++) {
                System.out.print("Option " + (char)('A' + j) + ": ");
                opt[j] = sc.nextLine();
            }

            System.out.print("Correct Answer: ");
            String ans = sc.nextLine();

            String data = id + "|" + text + "|" +
                    opt[0] + "|" + opt[1] + "|" + opt[2] + "|" + opt[3] +
                    "|" + ans + "|Pending|" +
                    LoginSystem.currentUser + "|" + LoginSystem.currentDept;

            fw.write(data + "\n");
        }

        fw.close();
        System.out.println("Questions Added!");
    }

    public static void viewQuestions(int type) throws Exception {

        File file = new File("questions.txt");
        if (!file.exists()) return;

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {

            String[] d = line.split("\\|");

            if (type == 1 ||
               (type == 2 && d[7].equals("Approved")) ||
               (type == 3 && d[7].equals("Rejected"))) {

                System.out.println("\nID: " + d[0]);
                System.out.println("Q: " + d[1]);
                System.out.println("Status: " + d[7]);
                System.out.println("Teacher: " + d[8]);
            }
        }
        br.close();
    }

    public static void updateQuestion() throws Exception {

        File file = new File("questions.txt");
        if (!file.exists()) return;

        BufferedReader br = new BufferedReader(new FileReader(file));
        List<String> list = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter ID: ");
        String id = sc.nextLine();

        String line;
        boolean found = false;

        while ((line = br.readLine()) != null) {

            String[] d = line.split("\\|");

            if (d[0].equals(id)) {

                if (!d[8].equals(LoginSystem.currentUser)) {
                    System.out.println("Not your question!");
                    list.add(line);
                    continue;
                }

                System.out.print("New Question: ");
                String newQ = sc.nextLine();

                d[1] = newQ;
                d[7] = "Pending";

                list.add(String.join("|", d));
                found = true;

            } else list.add(line);
        }

        br.close();

        FileWriter fw = new FileWriter(file);
        for (String s : list) fw.write(s + "\n");
        fw.close();

        System.out.println(found ? "Updated!" : "Not Found!");
    }
}