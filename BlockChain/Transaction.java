/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ruba Hassan
 */
public class Transaction {
    String fromWallet;
    String toWallet;
    int amount;
    public Transaction(String fromWallet, String toWallet,int amount){
        this.fromWallet=fromWallet;
        this.toWallet=toWallet;
        this.amount=amount;
    }
}
