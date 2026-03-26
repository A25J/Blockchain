import java.util.ArrayList;

public class test { 
    public static void main(String[] args) {

        //Create Blocks array
        ArrayList<Block> blockchain = new ArrayList<>();

        //Initialize Variables
        int numberOfWallets = 5;
        int NbOfTransactions = 100;
        int memCapacity = 10;

        //Create wallets
        Wallet[] wallets = new Wallet[5];
        for (int i = 0; i < numberOfWallets; i++) {
            String wID = generateID();
            wallets[i] = new Wallet(wID, 100);
            System.out.println("Wallet" + (i + 1) + " ID: " + wID);
        }

        //Create Genesis Block
        int blockID=0;
        String prevHash="0";
        Block currBlock = new Block(blockID, prevHash);
        blockchain.add(currBlock);
        System.out.println("\nGenesis Block:");
        System.out.println(currBlock.toString());

        //Create Mempool
        Mempool mem = new Mempool(memCapacity);

        //Create Transactions for genesis block
        for (int i = 0; i < memCapacity && i < NbOfTransactions; i++) {
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
            if (amount <= fromWallet.getBalance()) { //amount is enough in fromWallet
                Transaction t = new Transaction(fromID, toID, amount);
                fromWallet.subtractBalance(amount);
                toWallet.addBalance(amount);
                //add Tx to mempool
                mem.addTx(t);

                //Display current mempool content
                Transaction[] memContent = mem.getTransactions();
                System.out.println("\nMemPool transactions: ");
                for (int j = 0; j < memContent.length; j++) {
                    System.out.println((j + 1) + ") " + memContent[j].toString());
                }
            } else { //amount not enough
                i--;
            }
        }
        if (mem.getNbOfTransactions() == memCapacity) {
            Transaction[] transactions = mem.getTransactions();
            currBlock.setTransactions(transactions);
            System.out.println("\nGenesis Block "+blockID+" filled.");
            mem.Reset();
        }
        

        //Create Transactions if nb of transactions > memcapacity (>10) 
        for (int i = memCapacity; i < NbOfTransactions; i++) {
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
            if (amount <= fromWallet.getBalance()) { //amount is enough in fromWallet
                Transaction t = new Transaction(fromID, toID, amount);
                fromWallet.subtractBalance(amount);
                toWallet.addBalance(amount);
                if (mem.getNbOfTransactions() < memCapacity) { //if there is capacity add Tx to mempool
                    mem.addTx(t);

                    //Display current mempool content
                    Transaction[] memContent = mem.getTransactions();
                    System.out.println("\nMemPool transactions: ");
                    for (int j = 0; j < memContent.length; j++) {
                        System.out.println((j + 1) + ") " + memContent[j].toString());
                    }

                } else { //if mempool full, create new block, add mempool transactions to new block, add to block chain
                    prevHash = currBlock.getHash();
                    blockID++;
                    currBlock = new Block(blockID, prevHash);

                    //create new block
                    System.out.println("\nNew Block Created: ");
                    System.out.println(currBlock.toString());

                    //fill with mempool transactions
                    Transaction[] transactions = mem.getTransactions();
                    currBlock.setTransactions(transactions);
                    System.out.println("\nBlock " + blockID + " filled with transactions");
                    //add to blockchain
                    blockchain.add(currBlock);
                    
                    //reset mempool
                    mem.Reset();
                    //add new tx to mempool
                    mem.addTx(t);

                    //Display current mempool content
                    Transaction[] memContent = mem.getTransactions();
                    System.out.println("\nMemPool transactions: ");
                    for (int j = 0; j < memContent.length; j++) {
                        System.out.println((j + 1) + ") " + memContent[j].toString());
                    }
                }
            } else { //amount isn't enough in fromWallet
                i--; //skip this iteration (try new random wallets)
            }
        }
        
        //Final Block (if mempool is full)
        if (mem.getNbOfTransactions() == memCapacity) {
            prevHash = currBlock.getHash();
            blockID++;
            currBlock = new Block(blockID, prevHash);

            //create new block
            System.out.println("\nNew Block Created: ");
            System.out.println(currBlock.toString());

            //fill with mempool transactions
            Transaction[] transactions = mem.getTransactions();
            currBlock.setTransactions(transactions);
            System.out.println("\nBlock " + blockID + " filled with transactions");
            //add to blockchain
            blockchain.add(currBlock);

            //reset mempool
            mem.Reset();
        }
        
        //Display final blockchain
        System.out.println("\nFinal BlockChain Content: ");
        System.out.println("-----------------------------");
        System.out.println(blockchain);
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
