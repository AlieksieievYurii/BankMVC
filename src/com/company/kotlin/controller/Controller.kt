package com.company.kotlin.controller

import com.company.java.Controller.ConnectionDB
import com.company.java.Transaction
import com.company.java.model.ModelDB
import com.company.kotlin.view.ListenerView
import com.company.kotlin.view.ViewJFrame

class Controller() : ListenerView, ModelDB.DBCallBack {
    private var onUpDateView: OnUpDateView = ViewJFrame(this)
    private var connectionDB:ConnectionDB = ModelDB(this)

    override fun addMoney(sum: Int) {
        connectionDB.addToBalance(sum)
    }

    override fun getMoney(sum: Int) {
        connectionDB.getMoneyFromBalance(sum)
    }

    override fun balance(balance: Int)
    {
        onUpDateView.upDateView(Transaction(Transaction.TRANSACTION_TEST,0,balance))
    }

    override fun transaction(transaction: Transaction) {
        onUpDateView.upDateView(transaction)
    }
}