package queries;

public class TransactionQueries {
    public static final String TRANSACTION_BY_IBAN = "Select t From Transaction t WHERE d.id_trans= :id_trans";
}
