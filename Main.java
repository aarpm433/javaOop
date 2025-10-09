import java.util.ArrayList;
import java.util.List;

// -------------------- BankAccount --------------------
class BankAccount {
    private String accountNumber;
    protected double balance;
    private List<String> transactions;

    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            recordTransaction("Deposit: $" + amount);
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            recordTransaction("Withdrawal: $" + amount);
            return true;
        }
        return false;
    }

    public void recordTransaction(String transaction) {
        transactions.add(transaction);
    }

    public List<String> getTransactionHistory() {
        return transactions;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=$" + balance +
                '}';
    }
}

// -------------------- SavingsAccount --------------------
class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String accountNumber, double interestRate) {
        super(accountNumber);
        this.interestRate = interestRate;
    }

    @Override
    public void deposit(double amount) {
        double interest = amount * interestRate / 100;
        super.deposit(amount + interest);
        recordTransaction("Interest added: $" + interest);
    }

    public void updateInterestRate(double newRate) {
        this.interestRate = newRate;
    }

    @Override
    public String toString() {
        return "SavingsAccount{" +
                "accountNumber='" + getAccountNumber() + '\'' +
                ", balance=$" + getBalance() +
                ", interestRate=" + interestRate + "%" +
                '}';
    }
}

// -------------------- CheckingAccount --------------------
class CheckingAccount extends BankAccount {
    private double overdraftLimit;

    public CheckingAccount(String accountNumber, double overdraftLimit) {
        super(accountNumber);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= getBalance() + overdraftLimit) {
            balance -= amount;
            recordTransaction("Withdrawal: $" + amount);
            return true;
        }
        return false; // blocked if exceeds overdraft
    }

    @Override
    public String toString() {
        return "CheckingAccount{" +
                "accountNumber='" + getAccountNumber() + '\'' +
                ", balance=$" + getBalance() +
                ", overdraftLimit=$" + overdraftLimit +
                '}';
    }
}

// -------------------- BankCustomer --------------------
class BankCustomer {
    private String name;
    private List<BankAccount> accounts;

    public BankCustomer(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    public double totalBalance() {
        double total = 0;
        for (BankAccount account : accounts) {
            total += account.getBalance();
        }
        return total;
    }

    public void generateReport() {
        System.out.println("Banking Report for " + name);
        for (BankAccount account : accounts) {
            System.out.println(account);
        }
        System.out.println("Total Balance: $" + totalBalance());
    }
}

// -------------------- Main --------------------
public class Main {
    public static void main(String[] args) {

        // BankAccount
        BankAccount bankAccount = new BankAccount("ACC123");
        bankAccount.deposit(500);
        bankAccount.withdraw(200);

        // SavingsAccount
        SavingsAccount savings = new SavingsAccount("SAV123", 5.0);
        savings.deposit(1000);
        savings.updateInterestRate(10.0);
        savings.deposit(500);

        // CheckingAccount
        CheckingAccount checking = new CheckingAccount("CHK123", 500);
        checking.deposit(1000);
        checking.withdraw(1200); // allowed within overdraft
        checking.withdraw(2000); // blocked

        // BankCustomer
        BankCustomer customer = new BankCustomer("Alice");
        customer.addAccount(bankAccount);
        customer.addAccount(savings);
        customer.addAccount(checking);

        // Generate report
        customer.generateReport();
    }
}
