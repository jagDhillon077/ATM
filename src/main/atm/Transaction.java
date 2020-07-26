package atm;

public class Transaction {

    // Amount that is withdrawn/deposited from account
    private double transactionAmount;
    // What account did the transaction occur in
    private Account whichAccount;


    /*
    Creates new Transaction with transaction about and which account the transaction
    is associated with
     */
    public Transaction(double transactionAmount, Account whichAccount) {
        this.transactionAmount = transactionAmount;
        this.whichAccount = whichAccount;

    }

    /*
    Creates a summary of the transaction
     */
    public String getSummaryLine() {
        if (this.transactionAmount >= 0) {
            return String.format("$%s", this.transactionAmount);
        } else {
            return String.format("$%s were withdrawn", this.transactionAmount);
        }
    }


}
