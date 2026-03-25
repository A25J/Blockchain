/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ruba Hassan
 */
public class Mempool {
    private Transaction [] transactions;
    private int count;
    public Mempool(){
        transactions=new Transaction [10];
        count=0;
    }
    public void addTx(Transaction t){
        if(count<10){
            transactions[count]=t;
            count++;
        }
    }
    public Transaction [] getTransactions(){
        return transactions;
    }

    public int getNbOfTransactions() {
        return count;
    }
    
    public void Reset() {
        count = 0;
    }
}
