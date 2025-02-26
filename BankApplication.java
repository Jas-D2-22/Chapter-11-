import java.util.Date;

class Account {
    private int accountNumber;
    private double balance;
    private double annualInterestRate;
    private Date dateCreated;

    public Account(int accountNumber, double balance, double annualInterestRate) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
        this.dateCreated = new Date();
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance");
        }
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }
}

class CheckingAccount extends Account {
    private double overdraftLimit;

    public CheckingAccount(int accountNumber, double balance, double annualInterestRate, double overdraftLimit) {
        super(accountNumber, balance, annualInterestRate);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= getBalance() + overdraftLimit) {
            double newBalance = getBalance() - amount;
            if (newBalance < 0) {
                overdraftLimit += newBalance; // Reduce the overdraft limit
                newBalance = 0;
            }
            setBalance(newBalance);
        } else {
            System.out.println("Overdraft limit exceeded");
        }
    }
}

class SavingsAccount extends Account {

    public SavingsAccount(int accountNumber, double balance, double annualInterestRate) {
        super(accountNumber, balance, annualInterestRate);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= getBalance()) {
            setBalance(getBalance() - amount);
        } else {
            System.out.println("Cannot overdraw savings account");
        }
    }
}

public class BankApplication {
    public static void main(String[] args) {
        CheckingAccount checkingAccount = new CheckingAccount(12345, 1000, 0.01, 500);
        SavingsAccount savingsAccount = new SavingsAccount(67890, 2000, 0.02);

        System.out.println("Checking Account:");
        System.out.println("Account Number: " + checkingAccount.getAccountNumber());
        System.out.println("Balance: $" + checkingAccount.getBalance());
        System.out.println("Overdraft Limit: $" + 500);

        System.out.println("\nDepositing $200 into Checking Account...");
        checkingAccount.deposit(200);
        System.out.println("New Balance: $" + checkingAccount.getBalance());

        System.out.println("\nWithdrawing $1500 from Checking Account...");
        checkingAccount.withdraw(1500);
        System.out.println("New Balance: $" + checkingAccount.getBalance());

        System.out.println("\nSavings Account:");
        System.out.println("Account Number: " + savingsAccount.getAccountNumber());
        System.out.println("Balance: $" + savingsAccount.getBalance());

        System.out.println("\nWithdrawing $2500 from Savings Account...");
        savingsAccount.withdraw(2500);
        System.out.println("New Balance: $" + savingsAccount.getBalance());
    }
}
