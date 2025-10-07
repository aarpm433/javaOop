import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Exercise 0: Using the Example Person Class
        Person person = new Person("Simon", 24);
        System.out.println("Exercise 0:");
        System.out.println("Hello, my name is " + person.getName() +
                " and I'm " + person.getAge() + " years old.");
        System.out.println();

        // Exercise 1: Creating a Bank Account
        class BankAccount {
            private String accountNumber;
            protected double balance;

            public BankAccount(String accountNumber) {
                this.accountNumber = accountNumber;
                this.balance = 0.0;
            }

            public void deposit(double amount) {
                if (amount > 0) {
                    balance += amount;
                }
            }

            public boolean withdraw(double amount) {
                if (amount > 0 && amount <= balance) {
                    balance -= amount;
                    return true;
                }
                return false;
            }

            public double getBalance() {
                return balance;
            }

            public String getAccountNumber() {
                return accountNumber;
            }
        }

        System.out.println("Exercise 1:");
        BankAccount bankAccount = new BankAccount("ACC123");
        bankAccount.deposit(500.0);
        bankAccount.withdraw(200.0);
        System.out.println("Bank Account Balance: $" + bankAccount.getBalance());
        System.out.println();

        // Exercise 2: Creating a Savings Account
        class SavingsAccount extends BankAccount {
            private double interestRate;

            public SavingsAccount(String accountNumber, double interestRate) {
                super(accountNumber);
                this.interestRate = interestRate;
            }

            public void applyInterest() {
                double interest = getBalance() * interestRate / 100;
                deposit(interest);
            }

            public void setInterestRate(double newRate) {
                this.interestRate = newRate;
            }
        }

        System.out.println("Exercise 2:");
        SavingsAccount savings = new SavingsAccount("SAV123", 5.0);
        savings.deposit(1000);
        savings.applyInterest();
        System.out.println("Savings Balance after interest: $" + savings.getBalance());
        System.out.println();

        // Exercise 3: Creating a Checking Account
        class CheckingAccount extends BankAccount {
            private double overdraftLimit;

            public CheckingAccount(String accountNumber, double overdraftLimit) {
                super(accountNumber);
                this.overdraftLimit = overdraftLimit;
            }

            @Override
            public boolean withdraw(double amount) {
                if (amount > 0 && amount <= balance + overdraftLimit) {
                    balance -= amount;
                    return true;
                }
                return false;
            }
        }

        System.out.println("Exercise 3:");
        CheckingAccount checking = new CheckingAccount("CHK123", 500);
        checking.deposit(1000);
        checking.withdraw(1200); // within overdraft
        System.out.println("Checking Balance (after overdraft): $" + checking.getBalance());
        System.out.println();

        // Exercise 4: Managing Customer's Accounts
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

            public String getName() {
                return name;
            }

            public List<BankAccount> getAccounts() {
                return accounts;
            }

            public double getTotalBalance() {
                double total = 0;
                for (BankAccount acc : accounts) {
                    total += acc.getBalance();
                }
                return total;
            }
        }

        System.out.println("Exercise 4:");
        BankCustomer customer = new BankCustomer("Alice");
        customer.addAccount(bankAccount);
        customer.addAccount(savings);
        customer.addAccount(checking);
        System.out.println(customer.getName() + " has total balance: $" + customer.getTotalBalance());
        System.out.println();

        // Exercise 5: Transaction History
        class Transaction {
            private String type;
            private double amount;
            private String date;

            public Transaction(String type, double amount, String date) {
                this.type = type;
                this.amount = amount;
                this.date = date;
            }

            @Override
            public String toString() {
                return date + " - " + type + ": $" + amount;
            }
        }

        System.out.println("Exercise 5:");
        Transaction t1 = new Transaction("Deposit", 500, "2025-10-01");
        Transaction t2 = new Transaction("Withdrawal", 200, "2025-10-02");
        System.out.println(t1);
        System.out.println(t2);
        System.out.println();

        // Exercise 6: Generate Banking Report
        class BankingReport {
            public static void generateReport(BankCustomer customer) {
                System.out.println("Banking Report for " + customer.getName());
                for (BankAccount account : customer.getAccounts()) {
                    System.out.println("Account Number: " + account.getAccountNumber()
                            + ", Balance: $" + account.getBalance());
                }
            }
        }

        System.out.println("Exercise 6:");
        BankingReport.generateReport(customer);
        System.out.println();

        // Exercise 7: Update Interest Rates
        class InterestRateUpdater {
            public static void updateInterestRate(SavingsAccount account, double newRate) {
                account.setInterestRate(newRate);
            }
        }

        System.out.println("Exercise 7:");
        InterestRateUpdater.updateInterestRate(savings, 10.0);
        savings.applyInterest();
        System.out.println("Savings Balance after updated interest: $" + savings.getBalance());
        System.out.println();

        // Exercise 8: Override Withdrawal Method
        System.out.println("Exercise 8:");
        checking.withdraw(2000); // beyond overdraft
        System.out.println("Checking Balance after big withdrawal: $" + checking.getBalance());
    }
}
