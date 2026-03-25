import java.util.ArrayList;

public class test { 
    public static void main(String[] args) {

        //Create Blocks array
        ArrayList<Block> blockchain = new ArrayList<>();

        //Create 5 wallets
        Wallet[] wallets = new Wallet[5];
        for (int i = 0; i < 5; i++) {
            String wID = generateID();
            wallets[i] = new Wallet(wID, 100);
            System.out.println("Wallet" + (i + 1) + " ID: " + wID);
        }

        //Create Genesis Block
        int blockID=0;
        String prevHash="0";
        Block currBlock = new Block(blockID, prevHash);
        System.out.println("\nGenesis Block:");
        System.out.println(currBlock.toString());

        //Create Mempool
        Mempool mem = new Mempool();

        //Create Transactions
        for (int i = 0; i < 100; i++) {
            //get random indices of wallets' array
            int fromWalletIndex = (int) (Math.random() * 5);
            int toWalletIndex = (int) (Math.random() * 5);
            //access from and to wallets
            Wallet fromWallet = wallets[fromWalletIndex];
            Wallet toWallet = wallets[toWalletIndex];
            //get IDs
            String fromID = fromWallet.getWalletId();
            String toID = toWallet.getWalletId();
            //Random amount between 1 and 5
            int amount = (int) (1 + Math.random() * 5);
            //Create transaction if condition satisfies
            if (amount <= fromWallet.getBalance()) {  //amount is enough in fromWallet
                Transaction t = new Transaction(fromID, toID, amount);
                fromWallet.subtractBalance(amount);
                toWallet.addBalance(amount);
                if (mem.getNbOfTransactions() < 10) { //if there is capacity add Tx to mempool
                    mem.addTx(t);
                } else { //if mempool full add mempool transactions to block, add to blockchain, create new block, reset mempool
                    Transaction[] transactions = mem.getTransactions();
                    currBlock.setTransactions(transactions);
                    blockID++;
                    prevHash = currBlock.getHash();
                    blockchain.add(currBlock);
                    //new block
                    currBlock = new Block(blockID, prevHash);
                    System.out.println("\nNew Block Created: ");
                    System.out.println(currBlock.toString());
                    mem.Reset();
                    //add new tx to mempool
                    mem.addTx(t);
                }
            }
            else { //amount isn't enough in fromWallet
                i--; //skip this iteration (try new random wallets)
            }
        }
    }

    public static String generateID() {
        String id = "";
        for (int j = 0; j < 4; j++) { // each ID has 4 chars
            int r = (int) (Math.random() * 62); // 26+26+10 = 62 characters
            char c;
            if (r < 26)
                c = (char) ('A' + r); // A-Z
            else if (r < 52)
                c = (char) ('a' + r - 26); // a-z
            else
                c = (char) ('0' + r - 52); // 0-9
            id += c;
        }
        return id;
    }
}
