package com.company.java.Controller;

import com.company.java.Transaction;
import com.company.java.model.ModelDB;
import com.company.java.view.OnUpDateView;
import com.company.java.view.ViewJFrame;

public class ControllerBank implements ModelDB.DBCallBack, ViewJFrame.ViewCallBack
{
    private ConnectionDB connectionDB;
    private OnUpDateView onUpDateView;
    public ControllerBank()
    {
        this.onUpDateView = new ViewJFrame(this);
        this.connectionDB = new ModelDB(this);
    }

    @Override
    public void balance(int balance) {
        onUpDateView.upDateView(new Transaction(Transaction.TRANSACTION_TEST,0,balance));
    }

    @Override
    public void transaction(Transaction transaction) {
        onUpDateView.upDateView(transaction);
    }

    @Override
    public void addMoney(int money) {
        connectionDB.addToBalance(money);
    }

    @Override
    public void getMoney(int money) {
        connectionDB.getMoneyFromBalance(money);
    }
}
