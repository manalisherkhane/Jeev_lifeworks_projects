import java.sql.*;

/**
 * Handles atomic banking transactions using JDBC transaction management.
 */
public class TransactionManager {

    /**
     * Transfers amount from one account to another atomically.
     * Uses commit/rollback to ensure data integrity.
     */
    public boolean transfer(int fromId, int toId, double amount) {
        String debit  = "UPDATE accounts SET balance = balance - ? WHERE id = ? AND balance >= ?";
        String credit = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
        String logTx  = "INSERT INTO transactions (account_id, type, amount) VALUES (?, ?, ?)";

        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);  // START TRANSACTION

            // Debit sender
            PreparedStatement ps1 = conn.prepareStatement(debit);
            ps1.setDouble(1, amount);
            ps1.setInt(2, fromId);
            ps1.setDouble(3, amount);
            int rowsAffected = ps1.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Insufficient balance or invalid account.");
            }

            // Credit receiver
            PreparedStatement ps2 = conn.prepareStatement(credit);
            ps2.setDouble(1, amount);
            ps2.setInt(2, toId);
            ps2.executeUpdate();

            // Log transaction
            PreparedStatement ps3 = conn.prepareStatement(logTx);
            ps3.setInt(1, fromId);
            ps3.setString(2, "TRANSFER");
            ps3.setDouble(3, amount);
            ps3.executeUpdate();

            conn.commit();  // COMMIT if all steps succeed
            System.out.println("Transfer successful!");
            return true;

        } catch (SQLException e) {
            System.err.println("Transaction failed: " + e.getMessage());
            try { if (conn != null) conn.rollback(); }  // ROLLBACK on failure
            catch (SQLException ex) { ex.printStackTrace(); }
            return false;
        } finally {
            try { if (conn != null) conn.close(); }
            catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public boolean deposit(int accountId, double amount) {
        String sql = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
        String log = "INSERT INTO transactions (account_id, type, amount) VALUES (?, 'DEPOSIT', ?)";
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, amount); ps.setInt(2, accountId);
            ps.executeUpdate();
            PreparedStatement ps2 = conn.prepareStatement(log);
            ps2.setInt(1, accountId); ps2.setDouble(2, amount);
            ps2.executeUpdate();
            conn.commit();
            return true;
        } catch (SQLException e) {
            System.err.println("Deposit failed: " + e.getMessage());
            return false;
        }
    }
}