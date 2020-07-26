package atm;

public class Transaction {

    // Amount that is withdrawn/deposited from account
    private double transactionAmount;
    // What account did the transaction occur in
    private Account whichAccount;


    // MODIFIES: this
    // EFFECTS: Creates a transaction that has an amount and tells which account the
    // transaction is associated with
    public Transaction(double transactionAmount, Account whichAccount) {
        this.transactionAmount = transactionAmount;
        this.whichAccount = whichAccount;

    }


    // Get the amount of the transaction
    public double getAmount() {
        return this.transactionAmount;
    }


}
