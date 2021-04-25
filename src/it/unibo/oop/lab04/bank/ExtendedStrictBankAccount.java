package it.unibo.oop.lab04.bank;

public class ExtendedStrictBankAccount extends SimpleBankAccount {

    private static final double TRANSACTION_FEE = 0.1;

	public ExtendedStrictBankAccount(int usrID, double balance) {
		super(usrID, balance);
	}

	//Il metodo deve essere definito in quanto deve tener conto di TRANSACTION_FEE
    public void computeManagementFees(final int usrID) {
        final double feeAmount = MANAGEMENT_FEE + (getNTransactions() * ExtendedStrictBankAccount.TRANSACTION_FEE);
        if (checkUser(usrID) && isWithdrawAllowed(feeAmount)) {
            setBalance(getBalance() - feeAmount);
            resetTransactions();
        }
    }
    
    //Il metodo deve essere definito per verificare l'operazione è possibile
    public void withdraw(final int usrID, final double amount) {
        if (isWithdrawAllowed(amount)) {
            super.withdraw(usrID, amount);
        }
    }
    
    //Metodo privato usato da computeManagementFees() e withdraw()
    private boolean isWithdrawAllowed(final double amount) {
        return getBalance() > amount;
    }
    
}
