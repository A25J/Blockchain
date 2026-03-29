/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ruba Hassan
 */
import java.time.Instant;
import java.util.ArrayList;
import java.security.MessageDigest;
public class Block {
    int blockID;
    String currentHash;
    String previousHash;
    String TimeStamp;
    ArrayList <Transaction> transactions;
    public Block(int id,  String pHash){
        blockID=id;
        previousHash=pHash;
        TimeStamp=Instant.now().toString();
        currentHash=applySHA256(blockID+previousHash+TimeStamp+ transactions);
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }
    
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
    
    public String getHash() {
        return currentHash;
    }

     public static String applySHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Apply SHA-256
            byte[] hash = digest.digest(input.getBytes("UTF-8"));

            // Convert byte array to hex string
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);

                if (hex.length() == 1) {
                    hexString.append('0');
                }

                hexString.append(hex);
            }

            return hexString.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String toString(){
        return "\nBlock data{\nBlock id: " + blockID + "\nPrevious Block hash: " +previousHash
        +"\nCurrent Hash: " +currentHash+"\nTime Stamp: " +TimeStamp+"}";
    }
}
