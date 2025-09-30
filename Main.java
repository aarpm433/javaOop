public class Main {
    public static void main(String[] args) {

        // Exercise 0: Using the Example Person Class
        // Create an instance of Person
        // Use the instance to print "Hello, my name is [name] and I'm [age] years old."
        Person person = new Person("Simon", 24);
        System.out.println("Hello, my name is " + person.getName() + " and I'm " + person.getAge() + " years old.");

        // Exercise 1: Creating a Bank Account
        // Create a BankAccount instance
        // Perform deposit and withdrawal operations.
        Class BankAccount {
            private String accountNumber;
            private double balance;

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


        // Exercise 2: Creating a Savings Account
        // Create a SavingsAccount instance
        // Perform deposit operations with interest
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
        }


        // Exercise 3: Creating a Checking Account
        // Create a CheckingAccount instance
        // Perform withdrawal operations with overdraft
        class CheckingAccount extends BankAccount {
            private double overdraftLimit;

            public CheckingAccount(String accountNumber, double overdraftLimit) {
                super(accountNumber);
                this.overdraftLimit = overdraftLimit;
            }

            @Override
            public boolean withdraw(double amount) {
                if (amount > 0 && amount <= getBalance() + overdraftLimit) {
                    return super.withdraw(amount);
                }
                return false;
            }
        }

        // Exercise 4: Managing Customer's Accounts
        // Create a BankCustomer instance
        // Add multiple accounts and display total balance

        // Exercise 5: Transaction History
        // Add transactions to accounts and retrieve history

        // Exercise 6: Generate Banking Report
        // Generate and display a customer's banking report

        // Exercise 7: Update Interest Rates
        // Update the interest rate and observe calculations

        // Exercise 8: Override Withdrawal Method
        // Demonstrate overridden withdrawal method

        // Complete all exercises and print results
    }
}