package it.unibo.oop.lab04.bank2;

public class RestrictedBankAccount extends AbstractBankAccount {

    private static final double TRANSACTION_FEE = 0.1;

	public RestrictedBankAccount(double balance, int usrID) {
		super(balance, usrID);
	}

	@Override
	protected double computeFee() {
		return AbstractBankAccount.MANAGEMENT_FEE + (getNTransactions() * RestrictedBankAccount.TRANSACTION_FEE);
	}

	@Override
	protected boolean isWithdrawAllowed(double amount) {
		return getBalance() > amount ? true: false;
	}

}
