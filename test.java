
public class test {
    public static void main(String[] args) {
        Mempool m = new Mempool();
        Transaction tx0 = new Transaction("1aa4","2aay",4);
        Transaction tx1 = new Transaction("1ba4","2bay",4);
        Transaction tx2 = new Transaction("1ca4","2cay",4);
        Transaction tx3 = new Transaction("1da4","2day",4);
        Transaction tx4 = new Transaction("1ea4","2eay",4);
        Transaction tx5 = new Transaction("1fa4","2fay",4);
        Transaction tx6 = new Transaction("1ga4","2gay",4);
        Transaction tx7 = new Transaction("1ha4","2hay",4);
        Transaction tx8 = new Transaction("1ia4","2iay",4);
        Transaction tx9 = new Transaction("1ja4","2jay",4);

        m.addTx(tx0);
        m.addTx(tx1);
        m.addTx(tx2);
        m.addTx(tx3);
        m.addTx(tx4);
        m.addTx(tx5);
        m.addTx(tx6);
        m.addTx(tx7);
        m.addTx(tx8);
        m.addTx(tx9);

        Block b = new Block(2, "ade4");
        b.setTransactions(m.getTransactions());

        System.out.println(b.toString());


        






    }
}
