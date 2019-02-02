package com.company.kotlin

class Transaction(val status: Int, val receivedSum: Int, val remained: Int) {

    companion object {
        const val TRANSACTION_SUCCESSFUL_GET_MONEY = 100
        const val TRANSACTION_SUCCESSFUL_POST_MONEY = 150
        const val TRANSACTION_FAILED = 200
        const val TRANSACTION_BALANCE_IS_ZERO = 300
        const val TRANSACTION_TEST = 400
    }

    override fun toString(): String {
        return "Transaction(status=$status, receivedSum=$receivedSum, remained=$remained)"
    }
}