package it.unibo.oop.lab04.bank2;

public class ClassicBankAccount extends AbstractBankAccount {

	public ClassicBankAccount(final double balance, final int usrID) {
		super(balance, usrID);
	}

	@Override
	protected double computeFee() {
		return AbstractBankAccount.MANAGEMENT_FEE;
	}

	@Override
	protected boolean isWithdrawAllowed(final double amount) {
		return true;
	}

}
