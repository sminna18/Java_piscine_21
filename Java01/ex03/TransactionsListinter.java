import java.util.UUID;

interface TransactionsListinterface {
    void add_transaction(Transaction transaction);
    void delete_transaction(UUID uuid);
    public Transaction[] transform_into_array();
}

class listForTransactions {
    Transaction transaction;
    listForTransactions next;

    listForTransactions (Transaction transaction, listForTransactions next) {
        this.transaction = transaction;
        this.next = next;
    }
}


class TransactionsListinter implements  TransactionsListinterface {

    listForTransactions first;
    listForTransactions last;
    int count = 0;



    @Override
    public void add_transaction(Transaction transaction) {

        listForTransactions tmp = new listForTransactions(transaction, null);
        if (count!= 0)
            last.next = tmp;

        last = tmp;
        if (count == 0)
            first = tmp;
        count++;

    }

    @Override
    public void delete_transaction(UUID uuid) {
        listForTransactions checker;
        listForTransactions pre_checker;
        checker = first;
        pre_checker = first;
        for (int i = 0; i < count; i++) {
            if (checker.transaction.getId() == uuid) {
                if (i == 0 && count == 0)
                    first = null;
                else if(i == 0 && count != 0)
                    first = first.next;
                else {
                    pre_checker.next = checker.next;
                }
                count--;
            }
            pre_checker = checker;
            checker = checker.next;
        }
    }

    @Override
    public Transaction[] transform_into_array() {
        if (count == 0)
            return null;
        Transaction mas[] = new Transaction[count];
        listForTransactions check;
        check = first;
        mas[0] = first.transaction;
        for (int i = 1; i < count; i++)
        {
            mas[i] = check.next.transaction;
            check = check.next;
        }
        return mas;
    }
}