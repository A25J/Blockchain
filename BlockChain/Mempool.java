/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.ArrayList;

/**
 *
 * @author Ruba Hassan & Alaa Janbieh
 */
public class Mempool {
    private ArrayList <Transaction> transactions;
    private int capacity;

    public Mempool(int capacity) {
        this.capacity = capacity;
        transactions=new ArrayList<Transaction>();
    }

    public void addTx(Transaction t) {
        if (transactions.size()<capacity)
            transactions.add(t);
    }

    public ArrayList <Transaction> getTransactions() {
        return transactions;
    }

    public int getNbOfTransactions() {
        return transactions.size();
    }
    
    public void Reset() {
        transactions.clear();
    }
}
