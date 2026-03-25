class Wallet {
    private String walletId;
    private double balance = 0;

    public Wallet(String id, int initialBalance) {
        this.walletId = id;
        this.balance = initialBalance;
    }

    public String getWalletId() {
        return walletId;
    }

    public double getBalance() {
        return balance;
    }

    public void addBalance(double amount) {
        this.balance += amount;
    }

    public void subtractBalance(double amount) {
        this.balance -= amount;
    }
}
