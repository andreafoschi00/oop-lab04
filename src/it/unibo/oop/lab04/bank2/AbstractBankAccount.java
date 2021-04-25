package it.unibo.oop.lab04.bank2;

import it.unibo.oop.lab04.bank.BankAccount;
import it.unibo.oop.lab04.bank.SimpleBankAccount;

public abstract class AbstractBankAccount implements BankAccount {

    public static final double ATM_TRANSACTION_FEE = 1;
    public static final double MANAGEMENT_FEE = 5;
    
    private double balance;
    private int nTransactions;
    private final int usrID;
    
	public AbstractBankAccount(final double balance, final int usrID) {
		this.balance = balance;
		this.usrID = usrID;
		this.nTransactions = 0;
	}
	
    protected boolean checkUser(final int id) {
        return this.usrID == id;
    }

    protected abstract double computeFee();
    
    protected abstract boolean isWithdrawAllowed(final double amount);
    
	@Override
	public void computeManagementFees(final int usrID) {
		final double feeAmount = computeFee();
		if(checkUser(usrID) && isWithdrawAllowed(feeAmount)) {
			balance -= feeAmount;
			resetTransactions();
		}
	}
	
	protected void resetTransactions() {
		this.nTransactions = 0;
	}

	@Override
	public void deposit(final int usrID, final double amount) {
		this.transactionsOp(usrID, amount);
	}
	
	protected void transactionsOp(final int usrID, final double amount) {
        if (checkUser(usrID)) {
            this.balance += amount;
            this.incTransactions();
        }
	}
	
	private void incTransactions() {
		this.nTransactions++;
	}

	@Override
	public void depositFromATM(final int usrID, final double amount) {
		this.deposit(usrID, amount - AbstractBankAccount.ATM_TRANSACTION_FEE);
	}

	@Override
	public double getBalance() {
		return this.balance;
	}

	@Override
	public int getNTransactions() {
		return this.nTransactions;
	}

	@Override
	public void withdraw(final int usrID, final double amount) {
		if(isWithdrawAllowed(amount)) {
			this.transactionsOp(usrID, -amount);
		}
	}

	@Override
	public void withdrawFromATM(int usrID, double amount) {
		this.withdraw(usrID, amount + SimpleBankAccount.ATM_TRANSACTION_FEE);
	}

}
