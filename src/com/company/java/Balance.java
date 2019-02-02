package com.company.java;

import java.io.Serializable;

public class Balance implements Serializable
{
    public static final int DEFAULT_BALANCE = 10000;
    private int balance;

    public Balance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void addToBalance(int money)
    {
        balance = balance + money;
    }

    public boolean isBalanceZero()
    {
        return balance == 0;
    }

    public boolean checkBalance(int money)
    {
        return money <= balance;
    }

    public int getFromBalance(int money)
    {
        balance = balance - money;
        return money;
    }
}
