/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ruba Hassan
 */
public class Mempool {
    private Transaction[] transactions;
    private int capacity;
    private int count;

    public Mempool(int capacity) {
        this.capacity = capacity;
        transactions=new Transaction [capacity];
        count=0;
    }
    public void addTx(Transaction t){
        if(count<capacity){
            transactions[count]=t;
            count++;
        }
    }

    public Transaction[] getTransactions() {
        Transaction[] mempoolTransactions = new Transaction[count];
        for (int i = 0; i < count; i++) {
            mempoolTransactions[i] = transactions[i];
        }
        return mempoolTransactions;
    }

    public int getNbOfTransactions() {
        return count;
    }
    
    public void Reset() {
        count = 0;
    }
}
