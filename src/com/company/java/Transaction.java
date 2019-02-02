package com.company.java;

public class Transaction
{
    public static final int TRANSACTION_SUCCESSFUL_GET_MONEY = 100;
    public static final int TRANSACTION_SUCCESSFUL_POST_MONEY = 150;
    public static final int TRANSACTION_FAILED = 200;
    public static final int TRANSACTION_BALANCE_IS_ZERO = 300;
    public static final int TRANSACTION_TEST = 400;

    private final int status;
    private final int receivedSum;
    private final int remained;

    public Transaction(int status, int receivedSum, int remained) {
        this.status = status;
        this.receivedSum = receivedSum;
        this.remained = remained;
    }

    public int getStatus() {
        return status;
    }

    public int getReceivedSum() {
        return receivedSum;
    }

    public int getRemained() {
        return remained;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "status=" + status +
                ", receivedSum=" + receivedSum +
                ", remained=" + remained +
                '}';
    }
}
