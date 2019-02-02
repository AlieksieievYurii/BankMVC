package com.company.java.model;

import com.company.java.Balance;
import com.company.java.Controller.ConnectionDB;
import com.company.java.Transaction;
import java.io.*;

public class ModelDB implements ConnectionDB
{
    public interface DBCallBack
    {
        void balance(int balance);
        void transaction(Transaction transaction);
    }
    private final static String NAME_FILE = "Balance.txt";
    private Balance balance;
    private final DBCallBack dbCallBack;

    public ModelDB(DBCallBack dbCallBack) {
        this.dbCallBack = dbCallBack;
        readBalance();
        dbCallBack.balance(balance.getBalance());
    }

    @Override
    public void addToBalance(int money)
    {
        readBalance();
        balance.addToBalance(money);
        writeBalance();
        dbCallBack.transaction(new Transaction(Transaction.TRANSACTION_SUCCESSFUL_POST_MONEY,0,balance.getBalance()));
    }

    private void readBalance()
    {
        try {
            FileInputStream f = new FileInputStream(new File(NAME_FILE));
            ObjectInputStream o = new ObjectInputStream(f);
            balance = (Balance) o.readObject();
            f.close();
            o.close();
        } catch (IOException | ClassNotFoundException e) {
            balance = new Balance(Balance.DEFAULT_BALANCE);
        }
    }

    private void writeBalance()
    {

        try {
            FileOutputStream f = new FileOutputStream(new File(NAME_FILE));
            ObjectOutputStream o = new ObjectOutputStream(f);
            if(balance != null)
                o.writeObject(balance);
            f.close();
            o.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getMoneyFromBalance(int value){
        readBalance();
        if(balance.isBalanceZero())
            dbCallBack.transaction(new Transaction(Transaction.TRANSACTION_BALANCE_IS_ZERO,0,0));
        else if(balance.checkBalance(value))
        {
            int gettedMoney = balance.getFromBalance(value);
            writeBalance();
            dbCallBack.transaction(new Transaction(Transaction.TRANSACTION_SUCCESSFUL_GET_MONEY,gettedMoney,balance.getBalance()));
        }else
            dbCallBack.transaction(new Transaction(Transaction.TRANSACTION_FAILED,0,balance.getBalance()));
    }
}
