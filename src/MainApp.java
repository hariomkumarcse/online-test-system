import java.util.*;

public class MainApp {

    // ✅ Safe input method
    public static int getSafeInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("❌ Invalid input! Enter a number: ");
            sc.next();
        }
        return sc.nextInt();
    }

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int ch;

        do {
            System.out.println("\n1.Teacher Register");
            System.out.println("2.Teacher Login");
            System.out.println("3.Subject Head Register");
            System.out.println("4.Subject Head Login");
            System.out.println("5.Exit");

            ch = getSafeInt(sc);
            sc.nextLine();

            switch (ch) {

                case 1:
                    LoginSystem.registerUser("TH");
                    break;

                case 2:
                    if (LoginSystem.login("TH")) {

                        int t;
                        do {
                            System.out.println("\n--- Teacher Menu ---");
                            System.out.println("1.Add Question");
                            System.out.println("2.View Question");
                            System.out.println("3.Update Question");
                            System.out.println("4.Logout");

                            t = getSafeInt(sc);
                            sc.nextLine();

                            switch (t) {

                                case 1:
                                    QuestionManager.addQuestion();
                                    break;

                                case 2:
                                    int v;
                                    do {
                                        System.out.println("\n1.All Questions");
                                        System.out.println("2.Approved Questions");
                                        System.out.println("3.Rejected Questions");
                                        System.out.println("4.Back");

                                        v = getSafeInt(sc);
                                        sc.nextLine();

                                        switch (v) {
                                            case 1: QuestionManager.viewQuestions(1); break;
                                            case 2: QuestionManager.viewQuestions(2); break;
                                            case 3: QuestionManager.viewQuestions(3); break;
                                            case 4: break;
                                            default:
                                                System.out.println("Enter valid option!");
                                        }

                                    } while (v != 4);
                                    break;

                                case 3:
                                    QuestionManager.updateQuestion();
                                    break;

                                case 4:
                                    break;

                                default:
                                    System.out.println("Enter valid option!");
                            }

                        } while (t != 4);
                    }
                    break;

                case 3:
                    LoginSystem.registerUser("SH");
                    break;

                case 4:
                    if (LoginSystem.login("SH")) {

                        int h;
                        do {
                            System.out.println("\n1.View Questions");
                            System.out.println("2.Verify Question");
                            System.out.println("3.Logout");

                            h = getSafeInt(sc);
                            sc.nextLine();

                            switch (h) {
                                case 1: QuestionManager.viewQuestions(1); break;
                                case 2: VerificationSystem.verifyQuestion(); break;
                                case 3: break;
                                default:
                                    System.out.println("Enter valid option!");
                            }

                        } while (h != 3);
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Enter valid option!");
            }

        } while (ch != 5);
    }
}