import java.io.*;
import java.util.*;

public class VerificationSystem {

    public static void verifyQuestion() throws Exception {

        File file = new File("questions.txt");
        if (!file.exists()) return;

        BufferedReader br = new BufferedReader(new FileReader(file));
        List<String> list = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter ID: ");
        String id = sc.nextLine();

        System.out.print("Status (Approved/Rejected): ");
        String status = sc.nextLine();

        System.out.print("Feedback: ");
        String fb = sc.nextLine();

        String line;
        boolean found = false;

        while ((line = br.readLine()) != null) {

            String[] d = line.split("\\|");

            if (d[0].equals(id)) {
                d[7] = status;
                list.add(String.join("|", d));
                found = true;
            } else list.add(line);
        }

        br.close();

        FileWriter fw = new FileWriter(file);
        for (String s : list) fw.write(s + "\n");
        fw.close();

        FileWriter log = new FileWriter("verification.txt", true);
        log.write(id + "|" + status + "|" + fb + "\n");
        log.close();

        System.out.println(found ? "Done!" : "Not Found!");
    }
}