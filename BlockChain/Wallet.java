import java.util.ArrayList;

class Wallet {
    private String walletId;
    private double balance = 0;

    public Wallet(String id, int initialBalance) {
        this.walletId = id;
        this.balance = initialBalance;
    }

    // Getters
    public String getWalletId() {
        return walletId;
    }

    public double getBalance() {
        return balance;
    }

    // Balance operations
    public void addBalance(double amount) {
        this.balance += amount;
    }

    public void subtractBalance(double amount) {
        this.balance -= amount;
    }
}
