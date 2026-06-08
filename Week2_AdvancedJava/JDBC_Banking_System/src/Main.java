import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        TransactionManager tm = new TransactionManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== JDBC Banking System ===");
            System.out.println("1. Create Account");
            System.out.println("2. View All Accounts");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Delete Account");
            System.out.println("6. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("Name: "); String name = sc.next();
                    System.out.print("Email: "); String email = sc.next();
                    System.out.print("Initial Balance: "); double bal = sc.nextDouble();
                    System.out.println(dao.createAccount(name, email, bal)
                        ? "Account created!" : "Failed.");
                }
                case 2 -> dao.getAllAccounts().forEach(System.out::println);
                case 3 -> {
                    System.out.print("Account ID: "); int id = sc.nextInt();
                    System.out.print("Amount: "); double amt = sc.nextDouble();
                    tm.deposit(id, amt);
                }
                case 4 -> {
                    System.out.print("From ID: "); int from = sc.nextInt();
                    System.out.print("To ID: "); int to = sc.nextInt();
                    System.out.print("Amount: "); double amt = sc.nextDouble();
                    tm.transfer(from, to, amt);
                }
                case 5 -> {
                    System.out.print("Account ID to delete: "); int id = sc.nextInt();
                    System.out.println(dao.deleteAccount(id) ? "Deleted." : "Failed.");
                }
                case 6 -> { System.out.println("Goodbye!"); return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}