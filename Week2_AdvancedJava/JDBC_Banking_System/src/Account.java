public class Account {
    private int id;
    private String name;
    private String email;
    private double balance;

    // Constructor
    public Account(int id, String name, String email, double balance) {
        this.id = id; this.name = name;
        this.email = email; this.balance = balance;
    }

    // Getters & Setters for all fields
    public int getId()          { return id; }
    public String getName()     { return name; }
    public String getEmail()    { return email; }
    public double getBalance()  { return balance; }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %-20s | Email: %-25s | Balance: ₹%.2f",
                              id, name, email, balance);
    }
}