package com.company.kotlin.view

import com.company.java.Transaction
import com.company.kotlin.controller.OnUpDateView
import java.awt.Dimension
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*

class ViewJFrame(private val listenerView: ListenerView) : JFrame(),ActionListener,OnUpDateView
{
    companion object {
        private const val CODE_ERROR: Int = -1
    }

    private var btnAddMoney:JButton
    private var btnGetMoney:JButton
    private var tvBalance:JLabel

    init
    {
        this.title = "Bank"
        this.size = Dimension(200,200)
        this.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        this.setLocationRelativeTo(null)
        this.isResizable = false
        this.layout = null

        btnAddMoney = JButton("Add Money")
        btnGetMoney = JButton("Get Money")
        tvBalance = JLabel("Balance:")

        tvBalance.setBounds(50,20,100,10)
        btnAddMoney.setBounds(30, 40, 150, 20)
        btnGetMoney.setBounds(30, 70, 150, 20)
        btnGetMoney.addActionListener(this)
        btnAddMoney.addActionListener(this)

        this.add(tvBalance)
        this.add(btnAddMoney)
        this.add(btnGetMoney)

        this.isVisible = true
    }

    private fun alertAddMoney()
    {
        val strNum:String = JOptionPane.showInputDialog(this, "Enter sum:")
        val num:Int = convertToInt(strNum)

        if(num != CODE_ERROR)
            addMoneyToBalance(num)
    }

    private fun addMoneyToBalance(num: Int) {
        listenerView.addMoney(num)
    }

    private fun convertToInt(strNum:String) : Int
    {
        val number:Int
        try {
            number = strNum.toInt()
        }catch (e:Exception)
        {
            JOptionPane.showMessageDialog(this, "Enter Number!")
            return CODE_ERROR
        }

        return if(number > 0) number
            else {
            JOptionPane.showMessageDialog(this, "Can not be less then zero or equals zero!")
            CODE_ERROR
        }
    }

    private fun alertGetMoney()
    {
        val strNumber:String = JOptionPane.showInputDialog(this,"Enter sum what you want to get:")
        val number:Int = convertToInt(strNumber)
        if(number != CODE_ERROR)
            getMoneyFromBalance(number)
    }

    private fun getMoneyFromBalance(number: Int) {
        listenerView.getMoney(number)
    }

    override fun upDateView(transaction: Transaction) {
        when(transaction.status)
        {
            Transaction.TRANSACTION_SUCCESSFUL_GET_MONEY -> {
                tvBalance.text = "Balance:${transaction.remained}"
                JOptionPane.showMessageDialog(this, "You got:${transaction.receivedSum}\nYour balance is:${transaction.remained}")
            }
            Transaction.TRANSACTION_SUCCESSFUL_POST_MONEY ->{
                tvBalance.text = "Balance:${transaction.remained}"
                JOptionPane.showMessageDialog(this, "You deposited funds on:${transaction.remained}")
            }
            Transaction.TRANSACTION_FAILED ->
                JOptionPane.showMessageDialog(this, "You don't have such money.\nYou balance is:${transaction.remained}")
            Transaction.TRANSACTION_BALANCE_IS_ZERO ->
            {
                tvBalance.text = "Balance:0"
                JOptionPane.showMessageDialog(this, "Your balance is zero!")
            }
            Transaction.TRANSACTION_TEST ->
            {
                tvBalance.text = "Balance:${transaction.remained}"
            }
        }
    }

    override fun actionPerformed(e: ActionEvent?) {

        when(e!!.source)
        {
            btnAddMoney -> alertAddMoney()
            btnGetMoney -> alertGetMoney()
        }
    }
}