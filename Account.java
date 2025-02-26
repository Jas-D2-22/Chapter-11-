import java.util.Date;

class Account {
    private int accountNumber;
    private double balance;
    private double annualInterestRate;
    private Date dateCreated;

    // Constructor
    public Account(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.annualInterestRate = 0;
        this.dateCreated = new Date();
    }

    // Getter and setter methods
    public int getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public double getAnnualInterestRate() { return annualInterestRate; }
    public Date getDateCreated() { return dateCreated; }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    // Methods to deposit and withdraw
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

    // toString method
    @Override
    public String toString() {
        return "Account Number: " + accountNumber + "\nBalance: $" + balance + "\nAnnual Interest Rate: " + annualInterestRate + "%\nDate Created: " + dateCreated;
    }
}

class SavingsAccount extends Account {
    public SavingsAccount(int accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= getBalance()) {
            super.withdraw(amount);
        } else {
            System.out.println("Cannot overdraw savings account");
        }
    }
}

class CheckingAccount extends Account {
    private double overdraftLimit;

    public CheckingAccount(int accountNumber, double balance, double overdraftLimit) {
        super(accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= getBalance() + overdraftLimit) {
            super.withdraw(amount);
        } else {
            System.out.println("Overdraft limit exceeded");
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nOverdraft Limit: $" + overdraftLimit;
    }
}

