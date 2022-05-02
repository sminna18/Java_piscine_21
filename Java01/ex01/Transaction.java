import java.util.UUID;

public class Transaction{
    UUID id;
    User Recipient;
    User Sender;
    Typ balance;
    int amount;
    Transaction (User Recipient, User Sender, Typ balance, int amount) {
        if (Recipient.getBalance() > 0 && Sender.getBalance() > 0 &&
                (balance == Typ.DEBITS && Sender.getBalance() - amount >= 0
                        || balance == Typ.CREDITS && Recipient.getBalance() - amount >= 0)) {
            if (balance == Typ.DEBITS) {
                Sender.setBalance(Sender.getBalance() - amount);
                Recipient.setBalance(Recipient.getBalance() + amount);
            }
            else {
                Sender.setBalance(Sender.getBalance() + amount);
                Recipient.setBalance(Recipient.getBalance() - amount);
            }
            this.id = UUID.randomUUID();
            this.Recipient = Recipient;
            this.Sender = Sender;
            this.balance = balance;
            this.amount = amount;
            System.out.println("Transaction completed");
        }
        else
            System.out.println("Wrong balance");
    }
}