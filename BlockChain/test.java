
public class test { 
    public static void main(String[] args) {
        Wallet[] wallets = new Wallet[5];
        for (int i = 0; i < 5; i++) {
            String wID = generateID();
            wallets[i] = new Wallet(wID, 100);
            System.out.println("Wallet" + (i + 1) + " ID: " + wID);
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
