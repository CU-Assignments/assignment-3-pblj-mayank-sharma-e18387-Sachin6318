import java.util.*;

class InvalidPinException extends Exception {
    public InvalidPinException(String message) {
        super(message);
    }
}

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class ATM {
    private int pin;
    private double balance;

    public ATM(int pin, double balance) {
        this.pin = pin;
        this.balance = balance;
    }

    public void withdraw(int enteredPin, double amount) throws InvalidPinException, InsufficientBalanceException {
        if (enteredPin != pin) {
            throw new InvalidPinException("Invalid PIN entered.");
        }
        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance.");
        }
        balance -= amount;
        System.out.println("Withdrawal successful! Amount withdrawn: " + amount);
    }

    public void displayBalance() {
        System.out.println("Remaining balance: " + balance);
    }
}

public class ATMSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM(1234, 5000.0);

        try {
            System.out.print("Enter your PIN: ");
            int enteredPin = scanner.nextInt();
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            atm.withdraw(enteredPin, amount);
        } catch (InvalidPinException | InsufficientBalanceException e) {
            System.out.println("Transaction failed: " + e.getMessage());
        } finally {
            atm.displayBalance();
            scanner.close();
        }
    }
}
