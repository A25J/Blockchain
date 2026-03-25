import java.util.ArrayList;

class Wallet {
    private String walletId;
    private double balance = 0;

    // Public static list storing ALL wallets
    public static ArrayList<Wallet> wallets = new ArrayList<>();

    // Constructor
    public Wallet(String id, int initialBalance) {
        this.walletId = id;
        this.balance = initialBalance;

        // Automatically add wallet to the global list
        wallets.add(this);
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

    // Transaction method using the global wallets list
    public static boolean updateAfterTransaction(
            String senderId,
            String receiverId,
            double amount
    ) {
        Wallet sender = null;
        Wallet receiver = null;

        // Step 1: Find wallets in the global list
        for (Wallet w : wallets) {
            if (w.getWalletId().equals(senderId)) {
                sender = w;
            }
            if (w.getWalletId().equals(receiverId)) {
                receiver = w;
            }
        }

        // Step 2: Check existence
        if (sender == null || receiver == null) {
            return false;
        }

        // Step 3: Check balance
        if (amount <= 0 || sender.getBalance() < amount) {
            return false;
        }

        // Step 4: Update balances
        sender.subtractBalance(amount);
        receiver.addBalance(amount);

        return true;
    }
}
