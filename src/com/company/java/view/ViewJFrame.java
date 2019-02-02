package com.company.java.view;
import com.company.java.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewJFrame extends JFrame implements ActionListener, OnUpDateView
{

    private JButton btnAddMoney;
    private JButton btnGetMoney;
    private JLabel balance;

    public interface ViewCallBack
    {
        void addMoney(int money);
        void getMoney(int money);
    }

    private static final int CODE_ERROR = -1;
    private final ViewCallBack viewCallBack;

    public ViewJFrame(ViewCallBack viewCallBack)
    {
        this.viewCallBack = viewCallBack;
        initView();
    }

    private void initView()
    {
        this.setTitle("Bank");
        this.setSize(new Dimension(200,130));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);

        btnAddMoney = new JButton("Add money");
        btnGetMoney = new JButton("Get money");
        balance = new JLabel();

        balance.setBounds(50,20,100,10);
        btnAddMoney.setBounds(30,40,150,20);
        btnGetMoney.setBounds(30,70,150,20);
        btnGetMoney.addActionListener(this);
        btnAddMoney.addActionListener(this);

        this.add(btnGetMoney);
        this.add(btnAddMoney);
        this.add(balance);

        this.setVisible(true);
    }

    private void alertAddMoney()
    {
        String sum = JOptionPane.showInputDialog(this,"Enter sum:");

        int valueSum = convertToInt(sum);
        if(valueSum != CODE_ERROR)
            addMoneyToBalance(valueSum);
    }



    private int convertToInt(String sum)
    {
        int value;
        try
        {
            value = Integer.parseInt(sum);
        }catch (Exception e)
        {
            JOptionPane.showMessageDialog(this,"Enter Number!");
            return CODE_ERROR;
        }

        if(value <= 0)
        {
            JOptionPane.showMessageDialog(this,"Can not be less then zero or equals zero!");
            return CODE_ERROR;
        }else
            return value;
    }

    private void alertGetMoney()
    {
        String sum = JOptionPane.showInputDialog(this,"Enter sum what you want to get:");

        int value = convertToInt(sum);

        if(value != CODE_ERROR)
            getMoneyFromBalance(value);
    }

    private void getMoneyFromBalance(int value)
    {
        viewCallBack.getMoney(value);
    }

    @Override
    public void upDateView(Transaction transaction) {
        switch (transaction.getStatus())
        {
            case Transaction.TRANSACTION_SUCCESSFUL_GET_MONEY:
                balance.setText("Balance:"+transaction.getRemained());
                JOptionPane.showMessageDialog(this,"You got:"+transaction.getReceivedSum()+
                        "\nYour balance is:"+transaction.getRemained());
                break;
            case Transaction.TRANSACTION_BALANCE_IS_ZERO:
                balance.setText("Balance:0");
                JOptionPane.showMessageDialog(this,"Your balance is zero!");
                break;
            case Transaction.TRANSACTION_FAILED:
                balance.setText("Balance:"+transaction.getRemained());
                JOptionPane.showMessageDialog(this,"You dont have such money.\nYou balance is:"+transaction.getRemained());
                break;
            case Transaction.TRANSACTION_TEST:
                balance.setText("Balance:"+transaction.getRemained());
                break;
            case Transaction.TRANSACTION_SUCCESSFUL_POST_MONEY:
                balance.setText("Balance:"+transaction.getRemained());
                JOptionPane.showMessageDialog(this,"You deposited funds on:"+transaction.getRemained());
                break;
        }
    }

    private void addMoneyToBalance(int valueSum)
    {
       viewCallBack.addMoney(valueSum);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnAddMoney)
            alertAddMoney();
        else if(e.getSource() == btnGetMoney)
            alertGetMoney();
    }
}
